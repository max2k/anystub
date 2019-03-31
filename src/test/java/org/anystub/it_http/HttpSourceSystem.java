package org.anystub.it_http;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpSourceSystem {

    final private RestTemplate restTemplate;

    public HttpSourceSystem(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getStrings() {
        return restTemplate.getForEntity("https://gturnquist-quoters.cfapps.io/api/random", String.class).getBody();
    }
}
