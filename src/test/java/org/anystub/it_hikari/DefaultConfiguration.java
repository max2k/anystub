package org.anystub.it_hikari;

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
    DataSource dataSource() {

        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:./test5;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE");

        return new StubDataSource(ds)
                .setStubSuffix("stub-rs")
                .setStubResultSetMode(true);
    }




}
