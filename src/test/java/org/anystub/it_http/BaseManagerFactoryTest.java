package org.anystub.it_http;

import org.anystub.AnyStubId;
import org.anystub.Base;
import org.anystub.http.HttpUtil;
import org.anystub.mgmt.BaseManagerFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.function.Consumer;

import static org.anystub.mgmt.BaseManagerFactory.getStub;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BaseManagerFactoryTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    @AnyStubId(filename = "customBaseManager")
    public void testCustomGlobalHttpSettings() {

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

    @Test
    void LocateTest() {
        Base locate = BaseManagerFactory.locate();
        assertNotNull(locate);
        assertEquals("stub.yml", new File(locate.getFilePath()).getName());
    }

    @Test
    void initTest() {
        Base test1 = BaseManagerFactory.getBaseManager().getBase("test1");
        assertTrue(test1.isNew());
        test1.put("key1", "val");
        BaseManagerFactory.setDefaultStubInitializer(null);
    }
}