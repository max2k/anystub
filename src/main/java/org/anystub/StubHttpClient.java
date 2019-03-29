package org.anystub;


import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

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

public class StubHttpClient implements HttpClient {

    private static final Logger LOGGER = Logger.getLogger(StubHttpClient.class.getName());

    private final Base base;
    private final HttpClient httpClient;

    // TODO: plain vs base64 selector - requests body/response body matching/ default-auto-selector(on keys, on content)

    // TODO: opt-out keys (protocol/host/port/url/headers/entity)

    public StubHttpClient(Base base, HttpClient httpClient) {
        this.base = base;
        this.httpClient = httpClient;
    }


    @Override
    public HttpParams getParams() {
        return httpClient.getParams();
    }

    @Override
    public ClientConnectionManager getConnectionManager() {
        return httpClient.getConnectionManager();
    }

    @Override
    public HttpResponse execute(HttpUriRequest httpUriRequest) throws IOException, ClientProtocolException {
        return null;
    }

    @Override
    public HttpResponse execute(HttpUriRequest httpUriRequest, HttpContext httpContext) throws IOException, ClientProtocolException {
        LOGGER.info("execute(HttpUriRequest httpUriRequest, HttpContext httpContext)");
        LOGGER.info(String.format("input parameters: %s, %s", httpUriRequest, httpContext));

        boolean plainContent = true;
//        if (httpUriRequest.getURI().getHost().startsWith("idad")) {
//            plainContent = false;
//        }


        HttpResponse execute =
                base.request2(new Supplier<HttpResponse, IOException>() {
                                  @Override
                                  public HttpResponse get() throws IOException {
                                      HttpResponse execute = httpClient.execute(httpUriRequest, httpContext);
                                      LOGGER.info("response: "+ execute);
                                      return execute;
                                  }
                              },
                        new Decoder<HttpResponse>() {
                            @Override
                            public HttpResponse decode(Iterable<String> iterable) {
                                return gen(iterable);
                            }
                        },
                        new Encoder<HttpResponse>() {
                            @Override
                            public Iterable<String> encode(HttpResponse httpResponse) {
                                ArrayList<String> keys = keys(httpResponse, true);
                                LOGGER.info("response keys "+ keys);
                                return keys;
                            }
                        },
                        keys(httpUriRequest, plainContent).toArray(new String[0]));

        return execute;
    }

    @Override
    public HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest) throws IOException, ClientProtocolException {
        return null;
    }

    @Override
    public HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws IOException, ClientProtocolException {
        return null;
    }

    @Override
    public <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        return null;
    }

    @Override
    public <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException, ClientProtocolException {
        return null;
    }

    @Override
    public <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        return null;
    }

    @Override
    public <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException, ClientProtocolException {
        return null;
    }

    private ArrayList<String> keys(HttpUriRequest httpUriRequest, boolean plainContent) {
        ArrayList<String> strings = new ArrayList<>();

        strings.add(httpUriRequest.getMethod());
        strings.add(httpUriRequest.getURI().toASCIIString());

//        for (Header h : httpUriRequest.getAllHeaders()) {
//            LOGGER.info("Header: {} - {}", h.getName(), h.getValue());
//        }

        if (httpUriRequest instanceof HttpEntityEnclosingRequest) {
            HttpEntityEnclosingRequest httpEntityEnclosingRequest = (HttpEntityEnclosingRequest) httpUriRequest;
            HttpEntity entity = httpEntityEnclosingRequest.getEntity();

            strings.addAll(keys(entity, plainContent));
        }
        return strings;
    }

    private ArrayList<String> keys(HttpResponse httpResponse, boolean plainContent) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add(httpResponse.getStatusLine().getProtocolVersion().toString());
        strings.add(String.valueOf(httpResponse.getStatusLine().getStatusCode()));
        strings.add(httpResponse.getStatusLine().getReasonPhrase());

        for (Header h : httpResponse.getAllHeaders()) {
            strings.add(String.format("%s: %s", h.getName(), h.getValue()));
        }

        strings.addAll(keys(httpResponse.getEntity(), plainContent));

        return strings;
    }


    private ArrayList<String> keys(HttpEntity entity, boolean plainContent) {
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
            }else if (entity instanceof HttpEntityWrapper) {
                HttpEntityWrapper entityWrapper = (HttpEntityWrapper) entity;
                try (ByteArrayOutputStream byteArray = new ByteArrayOutputStream()) {
                    entityWrapper.writeTo(byteArray);
                    bytes = byteArray.toByteArray();
                }
            } else {
                LOGGER.warning(String.format("content: unavailable %s %s", entity.getClass().getName(), entity));
            }

            if (bytes != null) {
                if (plainContent) {
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

    private HttpResponse gen(Iterable<String> iterable) {
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
            basicHttpResponse.setHeader(header.substring(0,i), header.substring(i+2));
        }

        if (postHeader!=null){
            BasicHttpEntity httpEntity = new BasicHttpEntity();

            if (postHeader.startsWith("TEXT ")) {
                String textEntity = postHeader.substring(5);
                httpEntity.setContentLength(textEntity.length());
                httpEntity.setContent(new ByteArrayInputStream(textEntity.getBytes()));
            }else if (postHeader.startsWith("BASE64 ")) {
                String base64Entity = postHeader.substring(7);
                byte[] decode = Base64.getDecoder().decode(base64Entity);
                httpEntity.setContentLength(decode.length);
                httpEntity.setContent(new ByteArrayInputStream(decode));
            } else {
                LOGGER.severe("Failed to recover httpEntity: "+postHeader.substring(0,5)+"...");
            }
            basicHttpResponse.setEntity(httpEntity);
        }

        return basicHttpResponse;
    }
}