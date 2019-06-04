package org.anystub.mgmt;

import org.anystub.AnyStubId;
import org.anystub.http.StubHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.anystub.mgmt.BaseManagerFactory.getStub;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseManagerFactoryTest {

    @Autowired(required = false)
    private RestTemplate restTemplate;

    @Test
    @AnyStubId
    public void customBaseManager() {

        BaseManagerFactory.setDefaultStubInitializer(base -> StubHttpClient.addHeadersRule(base, "http"));


        ResponseEntity<String> forEntity;

        forEntity = restTemplate.getForEntity("https://gturnquist-quoters.cfapps.io/api/random", String.class);
        assertEquals(200, forEntity.getStatusCodeValue());
        assertEquals(1, getStub().times());
        assertTrue(getStub().match().findFirst().get().getKey(2).startsWith("Accept:"));

        BaseManagerFactory.setDefaultStubInitializer(null);

        forEntity = restTemplate.getForEntity("https://gturnquist-quoters.cfapps.io/api/random", String.class);
        assertEquals(200, forEntity.getStatusCodeValue());
        assertEquals(1, getStub().times());
        assertEquals(1, getStub().timesEx(null, null, "https.*"));

        BaseManagerFactory.setDefaultStubInitializer(base -> {});

        forEntity = restTemplate.getForEntity("https://gturnquist-quoters.cfapps.io/api/random", String.class);
        assertEquals(200, forEntity.getStatusCodeValue());
        assertEquals(1, getStub().times());
        assertEquals(1, getStub().timesEx(null, null, "https.*"));

    }
}