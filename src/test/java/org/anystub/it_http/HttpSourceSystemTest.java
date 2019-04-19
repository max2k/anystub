package org.anystub.it_http;

import org.anystub.Base;
import org.anystub.http.StubHttpClient;
import org.anystub.mgmt.BaseManagerImpl;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class HttpSourceSystemTest {

    @Autowired
    private HttpSourceSystem httpSourceSystem;

    @Autowired
    private Base httpBase;

    @Autowired(required = false)
    private RestTemplate restTemplate;

    @Test
    public void getStringsTest() {

        String r = httpSourceSystem.getStrings();

        assertEquals("{\"type\":\"success\",\"value\":{\"id\":2,\"quote\":\"With Boot you deploy everywhere you can find a JVM basically.\"}}", r);

    }

    @Test(expected = HttpClientErrorException.class)
    public void postTest() {
        restTemplate.postForEntity("https://gturnquist-quoters.cfapps.io/api/random", null, String.class);
    }

    @Test(expected = HttpClientErrorException.class)
    public void postBodyTest() {
        restTemplate.postForEntity("https://gturnquist-quoters.cfapps.io/api/random/xxx", "{test}", String.class);
    }

    @Test
    public void getBase64Test() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("https://test", String.class);
        assertEquals("test", forEntity.getBody());
    }


    @TestConfiguration
    static class Conf {

        @Bean
        Base httpBase() {
            return BaseManagerImpl.instance().getBase("httpStub.yml");
        }

        @Bean
        public HttpClient createHttpClient(Base httpBase) {

            HttpClient real = HttpClientBuilder.create().build();
            StubHttpClient result = new StubHttpClient(real).setFallbackBase(httpBase);

            return result.addBodyToKeyRules("random/xxx");

        }

        @Bean
        public ClientHttpRequestFactory requestFactory(HttpClient httpClient) {
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
            requestFactory.setHttpClient(httpClient);
            return requestFactory;
        }

        @Bean
        public RestTemplate restTemplate(ClientHttpRequestFactory requestFactory) {

            return new RestTemplate(requestFactory);
        }
    }
}
