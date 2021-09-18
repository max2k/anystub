package org.anystub.it_http;

import org.anystub.AnyStubId;
import org.anystub.Base;
import org.anystub.http.AnySettingsHttp;
import org.apache.http.client.HttpClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.anystub.mgmt.BaseManagerFactory.getStub;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AnyStubId(filename = "httpStub.yml")
public class HttpSourceSystemTest {

    @Autowired
    private HttpSourceSystem httpSourceSystem;

//    @Autowired
//    private Base httpBase;

    @Autowired(required = false)
    private RestTemplate restTemplate;

    @Test
    public void getStringsTest() {

        String r = httpSourceSystem.getStrings();

        assertEquals("{\"type\":\"success\",\"value\":{\"id\":2,\"quote\":\"With Boot you deploy everywhere you can find a JVM basically.\"}}", r);

    }

    @Test
    @AnyStubId
    @AnySettingsHttp(allHeaders = true)
    public void getWithHeaders() {

        ResponseEntity<String> forEntity = restTemplate.getForEntity("https://gturnquist-quoters.cfapps.io/api/random", String.class);
        assertEquals(200, forEntity.getStatusCodeValue());

        assertEquals(1, getStub().times());
        assertTrue(getStub().match().findFirst().get().getKey(2).startsWith("Accept:"));

    }

    @Test
    @AnyStubId
    @AnySettingsHttp(headers = "Accept")
    public void getWithHeader() {

        ResponseEntity<String> forEntity = restTemplate.getForEntity("https://gturnquist-quoters.cfapps.io/api/random", String.class);
        assertEquals(200, forEntity.getStatusCodeValue());

        assertEquals(1, getStub().times());
        assertTrue(getStub().match().findFirst().get().getKey(2).startsWith("Accept:"));

    }

    @Test
    public void postTest() {
        Assertions.assertThrows(HttpClientErrorException.class, () -> {
            restTemplate.postForEntity("https://gturnquist-quoters.cfapps.io/api/random", null, String.class);
        });
    }

    @Test
    @AnySettingsHttp(bodyTrigger = "random/xxx")
    public void postBodyTest() {
        Assertions.assertThrows(HttpClientErrorException.class, () -> {
            restTemplate.postForEntity("https://gturnquist-quoters.cfapps.io/api/random/xxx", "{test}", String.class);
        });
    }

    @Test
    @AnyStubId
    @AnySettingsHttp(bodyTrigger = "random/xxx")
    public void postMultiLineBodyTest() {
        try {
            restTemplate.postForEntity("https://gturnquist-quoters.cfapps.io/api/random/xxx", "{test:1,\ntest2:3}", String.class);
        } catch (HttpClientErrorException ex) {

        }
        try {

            restTemplate.postForEntity("https://gturnquist-quoters.cfapps.io/api/random/xxx", "{test:1,\n\ntest2:3}", String.class);
        } catch (HttpClientErrorException ex) {

        }

        assertEquals(2, getStub().times());
    }

    @Test
    @AnyStubId
    @AnySettingsHttp(bodyTrigger = "random/xxx")
    public void postBinaryBodyTest() {
        try {
            restTemplate.postForEntity("https://gturnquist-quoters.cfapps.io/api/random/xxx", new String(new byte[]{1, 2, 3, 4}), String.class);
        } catch (HttpClientErrorException ex) {

        }

        assertEquals(1, getStub().matchEx("POST", null, null, "BASE64.*").count());
    }

    @Test
    @AnyStubId
    @AnySettingsHttp(bodyTrigger = "random/xxx", bodyMask = "\\{.+\\}")
    public void postMaskedBodyTest() {
        int exceptionsNumber = 0;


        for (int i = 0; i < 2; i++) {

            try {

                restTemplate.postForEntity("https://gturnquist-quoters.cfapps.io/api/random/xxx", "some text{" + i + "} suffix", String.class);
            } catch (HttpClientErrorException ex) {
                exceptionsNumber++;
            }
        }
        assertEquals(2, exceptionsNumber);

        assertEquals(2,
                getStub()
                        .history()
                        .filter(d -> d.match_to(null, null, null, "some text... suffix"))
                        .count());

    }

    @Test
    public void getBase64Test() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("https://test", String.class);
        assertEquals("test", forEntity.getBody());
    }

}
