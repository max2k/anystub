package org.anystub;

import org.anystub.http.StubHttpClient;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class StubHttpClientTest {

    @Test
    public void executeGetTest() throws IOException {

        Base base = new Base("httpStub-static.yml")
                .constrain(Base.RequestMode.rmNone);

        HttpClient real = HttpClients.createDefault();
        StubHttpClient result = new StubHttpClient(base, real);

        HttpGet httpUriRequest = new HttpGet("https://gturnquist-quoters.cfapps.io:443/api/random");
        HttpResponse response = result.execute(httpUriRequest);


        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @Test
    public void executeHostUriTest() throws IOException {

        Base base = new Base("httpStub-static.yml")
                .constrain(Base.RequestMode.rmNone);

        HttpClient real = HttpClients.createDefault();
        StubHttpClient result = new StubHttpClient(base, real);

        HttpHost httpHost = new HttpHost("gturnquist-quoters.cfapps.io", 443, "https");
        HttpGet httpUriRequest = new HttpGet("/api/random");
        HttpResponse response = result.execute(httpHost, httpUriRequest);


        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @Test
    public void executeResponseHandlerTest() throws IOException {

        Base base = new Base("httpStub-static.yml")
                .constrain(Base.RequestMode.rmNone);

        HttpClient real = HttpClients.createDefault();
        StubHttpClient result = new StubHttpClient(base, real);

        HttpGet httpUriRequest = new HttpGet("https://gturnquist-quoters.cfapps.io:443/api/random");
        int response = result.execute(httpUriRequest,
                httpResponse -> httpResponse.getStatusLine().getStatusCode());


        assertEquals(200, response);

    }

}