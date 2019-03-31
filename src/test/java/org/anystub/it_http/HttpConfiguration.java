package org.anystub.it_http;


import org.apache.http.client.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@Configuration
public class HttpConfiguration {


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
