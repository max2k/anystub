package org.anystub.http;

import org.anystub.Util;
import org.anystub.mgmt.BaseManagerImpl;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.message.BasicHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class HttpUtil {

    private static final Logger LOGGER = Logger.getLogger(HttpUtil.class.getName());
    public static final String HTTP_PROPERTY = "http";
    public static final String HTTP_PROPERTY_All_HEADERS = "allHeader";
    public static final String HTTP_PROPERTY_HEADER = "header";
    public static final String HTTP_PROPERTY_BODY = "body";

    private HttpUtil() {
    }

    public static HttpResponse decode(Iterable<String> iterable) {
        BasicHttpResponse basicHttpResponse;

        Iterator<String> iterator = iterable.iterator();
        String[] protocol = iterator.next().split("[/.]");
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

            byte[] bytes = Util.recoverBinaryData(postHeader);
            httpEntity.setContentLength(bytes.length);
            httpEntity.setContent(new ByteArrayInputStream(bytes));
            basicHttpResponse.setEntity(httpEntity);
        }

        return basicHttpResponse;
    }

    public static List<String> encode(HttpResponse httpResponse) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add(httpResponse.getStatusLine().getProtocolVersion().toString());
        strings.add(String.valueOf(httpResponse.getStatusLine().getStatusCode()));
        strings.add(httpResponse.getStatusLine().getReasonPhrase());

        for (Header h : httpResponse.getAllHeaders()) {
            strings.add(String.format("%s: %s", h.getName(), h.getValue()));
        }

        extractEntity(httpResponse.getEntity())
                .ifPresent(strings::add);

        return strings;
    }


    public static Optional<String> extractEntity(HttpRequest httpRequest) {

        if (httpRequest instanceof HttpEntityEnclosingRequest) {
            HttpEntityEnclosingRequest request = (HttpEntityEnclosingRequest) httpRequest;
            return extractEntity(request.getEntity());
        }
        return Optional.empty();
    }

    public static Optional<String> extractEntity(HttpEntity entity) {
        if (entity == null) {
            return Optional.empty();
        }
        String result = null;

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
                // comes from org.springframework.web.client.RestTemplate
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
                result = Util.toCharacterString(bytes);

            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Stringify entity failed", e);
        }
        return result == null || result.isEmpty() ? Optional.empty() : Optional.of(result);
    }

    public static List<String> encode(HttpRequest httpRequest, HttpHost httpHost, String[] addBodyRules) {
        ArrayList<String> strings = new ArrayList<>();

        strings.add(httpRequest.getRequestLine().getMethod());
        strings.add(httpRequest.getRequestLine().getProtocolVersion().toString());

        String fullUrl = httpRequest.getRequestLine().getUri();
        encodeHeaders(httpRequest, fullUrl);

        if (httpHost != null && !httpRequest.getRequestLine().getUri().contains(httpHost.toString())) {
            if (!fullUrl.contains(httpHost.toString())) {
                fullUrl = httpHost.toString() + fullUrl;
                strings.add(fullUrl);
            }
        } else {
            strings.add(fullUrl);
        }

        matchBodyRule(fullUrl, addBodyRules)
                .ifPresent(x -> extractEntity(httpRequest)
                        .ifPresent(strings::add));

        return strings;
    }

    public static List<String> encode(HttpRequest httpRequest, String[] addBodyRules) {
        ArrayList<String> strings = new ArrayList<>();

        strings.add(httpRequest.getRequestLine().getMethod());
        strings.add(httpRequest.getRequestLine().getProtocolVersion().toString());
        String fullUrl = httpRequest.getRequestLine().getUri();

        strings.addAll(encodeHeaders(httpRequest, fullUrl));

        strings.add(fullUrl);


        matchBodyRule(fullUrl, addBodyRules)
                .ifPresent(x -> extractEntity(httpRequest)
                        .ifPresent(strings::add));

        return strings;
    }

    public static List<String> encodeHeaders(HttpRequest httpRequest, String fullUrl) {
        ArrayList<String> strings = new ArrayList<>();

        Header[] allHeaders = httpRequest.getAllHeaders();
        Arrays.sort(allHeaders, Comparator.comparing(NameValuePair::getName));

        boolean matchAll = BaseManagerImpl.getStub()
                .getProperty(HTTP_PROPERTY, HTTP_PROPERTY_All_HEADERS)
                .anyMatch(d -> fullUrl.contains(d.get()));

        if (matchAll) {
            for (Header h : allHeaders) {
                strings.add(String.format("%s: %s", h.getName(), h.getValue()));
            }
            return strings;
        }

        Set<String> headersToAdd = BaseManagerImpl.getStub()
                .getProperty(HTTP_PROPERTY, HTTP_PROPERTY_HEADER)
                .filter(d -> fullUrl.contains(d.get()))
                .map(d -> d.getKey(2))
                .collect(Collectors.toSet());

        if (!headersToAdd.isEmpty()) {
            for (Header h : allHeaders) {
                if(headersToAdd.contains(h.getName())) {
                    strings.add(String.format("%s: %s", h.getName(), h.getValue()));
                }
            }
        }

        return strings;
    }

    private static Optional<String> matchBodyRule(String url, String[] addBodyRules) {
//        BaseManagerImpl.getStub()
//                .getProperty(HTTP_PROPERTY, HTTP_PROPERTY_BODY)
//                .anyMatch(d->url.contains(d.get()));

        if (addBodyRules == null) {
            return Optional.empty();
        }
        return Arrays.stream(addBodyRules)
                .filter(url::contains)
                .findFirst();
    }

}
