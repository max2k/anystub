package org.anystub.it_http;

import org.anystub.Base;
import org.anystub.http.StubHttpClient;
import org.anystub.jdbc.StubDataSource;
import org.anystub.mgmt.BaseManagerFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

/**
 *
 */
@Configuration
public class DefaultConfiguration {

    @Bean
    public HttpClient createHttpClient() {
        Base httpBase = BaseManagerFactory
                .getBaseManager()
                .getBase();
        HttpClient real = HttpClientBuilder.create().build();
        return StubHttpClient.stub(real).setFallbackBase(httpBase);

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
