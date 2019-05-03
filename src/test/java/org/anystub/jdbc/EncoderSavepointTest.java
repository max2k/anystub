package org.anystub.jdbc;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class EncoderSavepointTest {
    @Test
    public void encodeTest() {
        StubSavepoint stubSavepoint = new StubSavepoint(1, "2");
        Iterator<String> encode = new EncoderSavepoint().encode(stubSavepoint).iterator();
        assertEquals("Savepoint",encode.next());
        assertEquals("1",encode.next());
        assertEquals("2",encode.next());
    }

}