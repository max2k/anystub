package org.anystub.mgmt;

import org.anystub.AnyStubId;
import org.anystub.http.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.anystub.mgmt.BaseManagerFactory.getStub;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseManagerFactoryTest {

    @Autowired(required = false)
    private RestTemplate restTemplate;

    @Test
    @AnyStubId(filename = "customBaseManager")
    public void customGlobalHttpSettings() {

        ResponseEntity<String> forEntity;

        forEntity = restTemplate.getForEntity("https://gturnquist-quoters.cfapps.io/api/random", String.class);
        assertEquals(200, forEntity.getStatusCodeValue());
        assertEquals(1, getStub().times());
        assertEquals(1, getStub().timesEx(null, null, "https.*"));

        HttpUtil.globalAllHeaders = true;

        forEntity = restTemplate.getForEntity("https://gturnquist-quoters.cfapps.io/api/random", String.class);
        assertEquals(200, forEntity.getStatusCodeValue());
        assertEquals(2, getStub().times());
        assertEquals(1, getStub().timesEx(null, null, "Accept:.*"));


        HttpUtil.globalAllHeaders = false;

        forEntity = restTemplate.getForEntity("https://gturnquist-quoters.cfapps.io/api/random", String.class);
        assertEquals(200, forEntity.getStatusCodeValue());
        assertEquals(3, getStub().times());
        assertEquals(2, getStub().timesEx(null, null, "https.*"));

    }
}