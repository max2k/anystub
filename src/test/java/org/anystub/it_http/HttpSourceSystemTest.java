package org.anystub.it_http;

import org.anystub.Base;
import org.anystub.http.StubHttpClient;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class HttpSourceSystemTest {

    @Autowired
    private HttpSourceSystem httpSourceSystem;

    @Autowired
    private Base httpBase;

    @Test
    public void getStringsTest() {

        String r = httpSourceSystem.getStrings();

        assertEquals("{\"type\":\"success\",\"value\":{\"id\":2,\"quote\":\"With Boot you deploy everywhere you can find a JVM basically.\"}}", r);

    }


    @TestConfiguration
    static class Conf {

        @Bean
        Base HttpBase() {
            return new Base("httpStub.yml");
        }

        @Bean
        public HttpClient createHttpClient(Base httpBase) {

            HttpClient real = HttpClientBuilder.create().build();
            StubHttpClient result = new StubHttpClient(httpBase, real);

            return result;

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
