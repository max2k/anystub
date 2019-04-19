package org.anystub.it;

import org.anystub.Base;
import org.anystub.http.StubHttpClient;
import org.anystub.jdbc.StubDataSource;
import org.anystub.mgmt.BaseManagerImpl;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.io.IOException;

/**
 *
 */
@Configuration
public class DefaultConfiguration {

    @Bean
    public String externalSystemUrl() {
        return "http://localhost:8080";
    }

    @Bean
    Base base() {
        return BaseManagerImpl.instance().getBase();
    }

    @Bean
    public SourceSystem sourceSystem(Base base) {


        return new SourceSystem("http://localhost:8080") {
            @Override
            public String get() throws IOException {
                return base.request("root");
            }

            /**
             * pay attention: the result of the function depends on
             * internal state of the object, which changed by invocation of the function
             * @param digit should has the same mean as in super class
             * @return from upstream or from
             */
            @Override
            public Integer rand(int digit) {
                return Integer.valueOf(
                        base.request("rand", Integer.toString(digit)));
            }
        };
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

    @Bean
    public DataSource dataSource() {

        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:./test3;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE");
        DataSource stubDataSource = new StubDataSource(ds);
        return stubDataSource;
    }
}
