package org.anystub;

import org.junit.Test;

import java.util.NoSuchElementException;

import static java.util.Arrays.asList;
import static org.anystub.Document.ars;
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
        assertFalse(new Document("123", "321").keyEqual_to("123"));
    }

    @Test
    public void match_toTest(){
        assertTrue(new Document("qwe").setValues("321").match_to());
        assertTrue(new Document("qwe").setValues("321").match_to(ars(null)));
        assertTrue(new Document("qwe").setValues("321").match_to("qwe"));
        assertFalse(new Document("qwe").setValues("321").match_to("q"));
        assertFalse(new Document("qwe").setValues("321").match_to("qwe", null));
    }

    @Test
    public void matchEx_toTest(){
        assertTrue(new Document("qwe").setValues("321").matchEx_to(ars()));
        assertTrue(new Document("qwe").setValues("321").matchEx_to());
        assertTrue(new Document("qwe").setValues("321").matchEx_to("qwe"));
        assertTrue(new Document("qwe").setValues("321").matchEx_to("q.*"));
        assertTrue(new Document("qwe").setValues("321").matchEx_to(".*w.*"));
        assertTrue(new Document("qwe").setValues("321").matchEx_to(".*e$"));
        assertTrue(new Document("qwe").setValues("321").matchEx_to("^q.*"));
        assertTrue(new Document("qwe", "321").setValues("321").matchEx_to("qwe"));
        assertTrue(new Document("qwe", "321").setValues("321").matchEx_to("qwe", null));
        assertTrue(new Document("qwe").setValues("321").matchEx_to(ars("qwe"), ars("3.*")));
        assertTrue(new Document("qwe").setValues("321").matchEx_to(ars("qwe"), ars(".2.")));
        assertFalse(new Document("qwe").setValues("321").matchEx_to(ars("qwe"), ars("^2.*")));
        assertFalse(new Document("qwe").setValues("321").matchEx_to("f"));
        assertFalse(new Document("qwe").setValues("321").matchEx_to(ars(), ars("qwe", null)));
    }

    @Test(expected = NoSuchElementException.class)
    public void exceptionTest(){
        new Document(new NoSuchElementException("aaaa"), "123").getVals();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void exceptionWMessageTest(){
        new Document(new IndexOutOfBoundsException("aaaa"), "123").getVals();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void exceptionWoMessageTest(){
        new Document(new IndexOutOfBoundsException(), "123").getVals();
    }

    @Test(expected = RuntimeException.class)
    public void exceptionNotFountTest(){
        Document document = new Document("123");
        document.setException(asList("nonexistentException", "msg"));
        document.getVals();
    }
}