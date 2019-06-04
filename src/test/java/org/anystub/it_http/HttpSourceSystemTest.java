package org.anystub.it_http;

import org.anystub.AnyStubId;
import org.anystub.Base;
import org.apache.http.client.HttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.anystub.http.StubHttpClient.addBodyMaskRule;
import static org.anystub.http.StubHttpClient.addBodyRule;
import static org.anystub.http.StubHttpClient.addHeaderRule;
import static org.anystub.http.StubHttpClient.addHeadersRule;
import static org.anystub.mgmt.BaseManagerFactory.getStub;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AnyStubId(filename = "httpStub.yml")
public class HttpSourceSystemTest {

    @Autowired
    private HttpSourceSystem httpSourceSystem;

    @Autowired
    private Base httpBase;

    @Autowired(required = false)
    private RestTemplate restTemplate;

    @Autowired
    HttpClient httpClient;

    @Test
    public void getStringsTest() {

        String r = httpSourceSystem.getStrings();

        assertEquals("{\"type\":\"success\",\"value\":{\"id\":2,\"quote\":\"With Boot you deploy everywhere you can find a JVM basically.\"}}", r);

    }

    @Test
    @AnyStubId
    public void getWithHeaders() {

        addHeadersRule("random");
        ResponseEntity<String> forEntity = restTemplate.getForEntity("https://gturnquist-quoters.cfapps.io/api/random", String.class);
        assertEquals(200, forEntity.getStatusCodeValue());

        assertEquals(1, getStub().times());
        assertTrue(getStub().match().findFirst().get().getKey(2).startsWith("Accept:"));


        forEntity = restTemplate.getForEntity("https://gturnquist-quoters.cfapps.io/api", String.class);
        assertEquals(200, forEntity.getStatusCodeValue());
        assertEquals(1, getStub().timesEx(null, null, "https.*"));
    }

    @Test
    @AnyStubId
    public void getWithHeader() {

        addHeaderRule("Accept", "random");
        ResponseEntity<String> forEntity = restTemplate.getForEntity("https://gturnquist-quoters.cfapps.io/api/random", String.class);
        assertEquals(200, forEntity.getStatusCodeValue());

        assertEquals(1, getStub().times());
        assertTrue(getStub().match().findFirst().get().getKey(2).startsWith("Accept:"));


        forEntity = restTemplate.getForEntity("https://gturnquist-quoters.cfapps.io/api", String.class);
        assertEquals(200, forEntity.getStatusCodeValue());
        assertEquals(1, getStub().timesEx(null, null, "https.*"));
    }

    @Test(expected = HttpClientErrorException.class)
    public void postTest() {
        restTemplate.postForEntity("https://gturnquist-quoters.cfapps.io/api/random", null, String.class);
    }

    @Test(expected = HttpClientErrorException.class)
    public void postBodyTest() {
        addBodyRule("random/xxx");
        restTemplate.postForEntity("https://gturnquist-quoters.cfapps.io/api/random/xxx", "{test}", String.class);
    }

    @Test
    @AnyStubId
    public void postMultiLineBodyTest() {
        addBodyRule("random/xxx");
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
    public void postBinaryBodyTest() {
        addBodyRule("random/xxx");
        try {
            restTemplate.postForEntity("https://gturnquist-quoters.cfapps.io/api/random/xxx", new String(new byte[]{1, 2, 3, 4}), String.class);
        } catch (HttpClientErrorException ex) {

        }

        assertEquals(1, getStub().matchEx("POST", null, null, "BASE64.*").count());
    }

    @Test
    @AnyStubId
    public void postMaskedBodyTest() {
        int exceptionsNumber = 0;
        addBodyRule("random/xxx");
        addBodyMaskRule("random/xxx", "\\{.+\\}");


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
