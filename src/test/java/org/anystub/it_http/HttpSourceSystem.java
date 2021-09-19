package org.anystub.it_http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpSourceSystem {

    @Autowired
    private RestTemplate restTemplate;

    public String getStrings() {
        return restTemplate.getForEntity("https://gturnquist-quoters.cfapps.io/api/random", String.class).getBody();
    }
}
