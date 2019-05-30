package org.anystub;

import org.junit.Test;

import static org.anystub.mgmt.BaseManagerImpl.getStub;
import static org.junit.Assert.*;

@AnyStubId(filename = "AnyStubFileLocatorTest3-x")
public class AnyStubFileLocatorTest3 {

    {
        Base stub = getStub();
        stub.put("aaa", "bbb");
    }

    static {
        Base stub = getStub();
        stub.put("111", "222");
    }

    @Test
    public void discoverFile() {
        String aaa = getStub("AnyStubFileLocatorTest3-x.yml").get("aaa");
        assertEquals("bbb", aaa);

        String ones = getStub("AnyStubFileLocatorTest3-x.yml").get("111");
        assertEquals("222", ones);


    }
}