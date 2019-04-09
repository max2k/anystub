package org.anystub.http;

import org.anystub.Base;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.message.BasicHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class HttpResponseUtil {

    private static final Logger LOGGER = Logger.getLogger(HttpResponseUtil.class.getName());

    public static HttpResponse decode(Iterable<String> iterable) {
        BasicHttpResponse basicHttpResponse;

        Iterator<String> iterator = iterable.iterator();
        String[] protocol = iterator.next().split("[/\\.]");
        String code = iterator.next();
        String reason = iterator.next();

        basicHttpResponse = new BasicHttpResponse(new ProtocolVersion(protocol[0], parseInt(protocol[1]), parseInt(protocol[2])),
                parseInt(code),
                reason);

        String postHeader = null;
        while (iterator.hasNext()) {
            String header;
            header = iterator.next();
            if (!header.matches(".+: .+")) {
                postHeader = header;
                break;
            }

            int i = header.indexOf(": ");
            basicHttpResponse.setHeader(header.substring(0, i), header.substring(i + 2));
        }

        if (postHeader != null) {
            BasicHttpEntity httpEntity = new BasicHttpEntity();

            if (postHeader.startsWith("TEXT ")) {
                String textEntity = postHeader.substring(5);
                httpEntity.setContentLength(textEntity.length());
                httpEntity.setContent(new ByteArrayInputStream(textEntity.getBytes()));
            } else if (postHeader.startsWith("BASE64 ")) {
                String base64Entity = postHeader.substring(7);
                byte[] decode = Base64.getDecoder().decode(base64Entity);
                httpEntity.setContentLength(decode.length);
                httpEntity.setContent(new ByteArrayInputStream(decode));
            } else {
                LOGGER.severe("Failed to recover httpEntity: " + postHeader.substring(0, 5) + "...");
            }
            basicHttpResponse.setEntity(httpEntity);
        }

        return basicHttpResponse;
    }

    public static ArrayList<String> encode(HttpResponse httpResponse, boolean plainContent) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add(httpResponse.getStatusLine().getProtocolVersion().toString());
        strings.add(String.valueOf(httpResponse.getStatusLine().getStatusCode()));
        strings.add(httpResponse.getStatusLine().getReasonPhrase());

        for (Header h : httpResponse.getAllHeaders()) {
            strings.add(String.format("%s: %s", h.getName(), h.getValue()));
        }

        strings.addAll(encode(httpResponse.getEntity(), plainContent));

        return strings;
    }


    public static ArrayList<String> encode(HttpEntity entity, boolean plainContent) {
        ArrayList<String> strings = new ArrayList<>();
        if (entity == null) {
            return strings;
        }

        try {
            byte[] bytes = null;

            if (entity instanceof BasicHttpEntity) {
                BasicHttpEntity basicHttpEntity = (BasicHttpEntity) entity;

                try (ByteArrayOutputStream byteArray = new ByteArrayOutputStream()) {
                    basicHttpEntity.writeTo(byteArray);
                    bytes = byteArray.toByteArray();
                }
                ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);

                basicHttpEntity.setContent(inputStream);


            } else if (entity instanceof ByteArrayEntity) {
                ByteArrayEntity byteArrayEntity = (ByteArrayEntity) entity;
                try (ByteArrayOutputStream byteArray = new ByteArrayOutputStream()) {
                    byteArrayEntity.writeTo(byteArray);
                    bytes = byteArray.toByteArray();
                }
            } else if (entity instanceof HttpEntityWrapper) {
                HttpEntityWrapper entityWrapper = (HttpEntityWrapper) entity;
                try (ByteArrayOutputStream byteArray = new ByteArrayOutputStream()) {
                    entityWrapper.writeTo(byteArray);
                    bytes = byteArray.toByteArray();
                }
            } else {
                LOGGER.warning(() -> String.format("content: unavailable %s %s", entity.getClass().getName(), entity));
            }

            if (bytes != null) {
                if (plainContent && Base.isText(new String(bytes, StandardCharsets.UTF_8))) {
                    strings.add("TEXT " + new String(bytes, StandardCharsets.UTF_8));
                } else {
                    String encode = Base64.getEncoder().encodeToString(bytes);
                    strings.add("BASE64 " + encode);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Stringify entity failed", e);
        }
        return strings;
    }

    public static ArrayList<String> encode(HttpRequest httpRequest, HttpHost httpHost, boolean plainContent) {
        ArrayList<String> strings = new ArrayList<>();

        strings.add(httpRequest.getRequestLine().getMethod());
        strings.add(httpRequest.getRequestLine().getProtocolVersion().toString());

        String uri = httpRequest.getRequestLine().getUri();
        if (httpHost != null && !httpRequest.getRequestLine().getUri().contains(httpHost.toString())) {
            if (!uri.contains(httpHost.toString())) {
                strings.add(httpHost.toString() + uri);
            }
        } else {
            strings.add(uri);
        }

//        for (Header h : httpUriRequest.getAllHeaders()) {
//            LOGGER.info("Header: {} - {}", h.getName(), h.getValue());
//        }

        if (httpRequest instanceof HttpEntityEnclosingRequest) {
            HttpEntityEnclosingRequest httpEntityEnclosingRequest = (HttpEntityEnclosingRequest) httpRequest;
            HttpEntity entity = httpEntityEnclosingRequest.getEntity();

            strings.addAll(encode(entity, plainContent));
        }
        return strings;
    }

    public static ArrayList<String> encode(HttpRequest httpRequest) {
        ArrayList<String> strings = new ArrayList<>();

        strings.add(httpRequest.getRequestLine().getMethod());
        strings.add(httpRequest.getRequestLine().getProtocolVersion().toString());
        strings.add(httpRequest.getRequestLine().getUri());
        return strings;
    }

}
