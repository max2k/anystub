package org.anystub.http;


import org.anystub.Base;
import org.anystub.Supplier;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

import static org.anystub.http.HttpUtil.encode;


/**
 * implementation of HttpClient to stub http-traffic
 */
public class StubHttpClient implements HttpClient {

    private static final Logger LOGGER = Logger.getLogger(StubHttpClient.class.getName());

    private final Base base;
    private final HttpClient httpClient;
    private String[] addBodyRules ={};

    // TODO: plain vs base64 encoding selector - requests body/response body matching/ default-auto-selector(on keys)

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
        return base.request2(new Supplier<HttpResponse, IOException>() {
                                 @Override
                                 public HttpResponse get() throws IOException {
                                     HttpResponse execute1 = httpClient.execute(httpUriRequest);
                                     LOGGER.finest("response: " + execute1);
                                     return execute1;
                                 }
                             },
                new DecoderHttpResponse(),
                new EncoderHttpResponse(),
                keys(httpUriRequest));
    }

    @Override
    public HttpResponse execute(HttpUriRequest httpUriRequest, HttpContext httpContext) throws IOException, ClientProtocolException {
        return base.request2(new Supplier<HttpResponse, IOException>() {
                                 @Override
                                 public HttpResponse get() throws IOException {
                                     return httpClient.execute(httpUriRequest, httpContext);
                                 }
                             },
                new DecoderHttpResponse(),
                new EncoderHttpResponse(),
                keys(httpUriRequest));
    }

    @Override
    public HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest) throws IOException, ClientProtocolException {
        return base.request2(new Supplier<HttpResponse, IOException>() {
                                 @Override
                                 public HttpResponse get() throws IOException {
                                     return httpClient.execute(httpHost, httpRequest);
                                 }
                             },
                new DecoderHttpResponse(),
                new EncoderHttpResponse(),
                HttpUtil.encode(httpRequest, httpHost, addBodyRules).toArray(new String[0]));
    }

    @Override
    public HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws IOException, ClientProtocolException {
        LOGGER.info("execute(HttpUriRequest httpUriRequest, HttpContext httpContext)");
        LOGGER.info(() -> String.format("input parameters: %s, %s", httpRequest, httpContext));

        return base.request2(new Supplier<HttpResponse, IOException>() {
                                 @Override
                                 public HttpResponse get() throws IOException {
                                     return httpClient.execute(httpHost, httpRequest, httpContext);
                                 }
                             },
                new DecoderHttpResponse(),
                new EncoderHttpResponse(),
                keys(httpRequest));
    }

    @Override
    public <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        return responseHandler.handleResponse(execute(httpUriRequest));
    }

    @Override
    public <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException, ClientProtocolException {
        return responseHandler.handleResponse(execute(httpUriRequest, httpContext));
    }

    @Override
    public <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        return responseHandler.handleResponse(execute(httpHost, httpRequest));
    }

    @Override
    public <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException, ClientProtocolException {
        HttpResponse execute = execute(httpHost, httpRequest, httpContext);
        return responseHandler.handleResponse(execute);
    }

    private String[] keys(HttpRequest httpRequest) {
        return encode(httpRequest, addBodyRules).toArray(new String[0]);
    }

    public StubHttpClient addBodyToKeyRules(String partOfUrl) {
        this.addBodyRules = Arrays.copyOf(this.addBodyRules, addBodyRules.length+1);
        this.addBodyRules[this.addBodyRules.length-1] = partOfUrl;
        return this;
    }
}
