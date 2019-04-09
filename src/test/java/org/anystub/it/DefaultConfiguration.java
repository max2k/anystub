package org.anystub.it;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 */
@Configuration
public class DefaultConfiguration {

    @Bean
    public String externalSystemUrl() {
        return "http://localhost:8080";
    }
}
