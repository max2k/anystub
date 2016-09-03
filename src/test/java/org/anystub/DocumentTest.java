package org.anystub;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kirill on 9/2/2016.
 */
public class DocumentTest {

    @Test
    public void equalTest()
    {
        assertEquals(new Document("qwe", "ewwq", "123"), new Document("qwe", "ewwq", "123"));
        assertEquals(new Document(), new Document());
        assertNotEquals(new Document("qwe", "ewwq", "1234"), new Document("qwe", "ewwq", "123"));
        assertNotEquals(new Document("qwe", "ewwq", "1234"), new Document("qwe", "ewwq"));
        assertNotEquals(new Document("qwe", "ewwq"), new Document("qwe", "ewwq", "123"));
        assertNotEquals(new Document("qwe", "ewwq"), new Document("qwe", null, "123"));
    }

    @Test
    public void equalToTest()
    {
        assertTrue(new Document("123", "321").keyEqual_to("123", "321"));
        assertTrue(new Document("123", "321", "asd").keyEqual_to("123", "321", "asd"));
//        assertTrue(new Document("123", "321").setValues("qweqwe", "ewqewq").keyEqual_to("123", "321"));
        assertFalse(new Document("123", "321").keyEqual_to("123"));
    }
}