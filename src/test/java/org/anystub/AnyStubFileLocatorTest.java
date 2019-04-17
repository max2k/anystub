package org.anystub;

import org.junit.Test;

import static org.junit.Assert.*;

@AnyStubId
public class AnyStubFileLocatorTest {

    @Test
    @AnyStubId
    public void testcase() {
        assertEquals("testcase.yml", AnyStubFileLocator.discoverFile());
    }
    @Test
    @AnyStubId(filename = "special")
    public void testcase1() {
        assertEquals("special.yml", AnyStubFileLocator.discoverFile());
    }

    @Test
    public void testname() {
        assertEquals("AnyStubFileLocatorTest.yml", AnyStubFileLocator.discoverFile());

    }

}