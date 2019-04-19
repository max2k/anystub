package org.anystub;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void isTextTest() {
        assertTrue(Util.isText("thisistextline"));
        assertTrue(Util.isText("{\"this is\": \'text' }; line"));
        assertFalse(Util.isText("thisistextline" + (char) 0x03));
    }


    @Test
    public void isText1() {

        assertTrue(Util.isText("thisistextline".getBytes()));
        assertTrue(Util.isText("{\"this is\": \'text' }; line".getBytes()));
        assertFalse(Util.isText(("thisistextline" + (char) 0x03).getBytes()));

    }

    @Test
    public void toCharacterStringTest() {
        String s;
        s = Util.toCharacterString("thisistextline".getBytes());
        assertEquals("thisistextline", s);
        s = Util.toCharacterString("BASE".getBytes());
        assertEquals("TEXT BASE", s);
        s = Util.toCharacterString(("thisistextline" + (char) 0x03).getBytes());
        assertTrue( s.startsWith("BASE64 "));
    }
}