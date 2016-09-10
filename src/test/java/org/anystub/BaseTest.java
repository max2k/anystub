package org.anystub;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Kirill on 9/3/2016.
 */
public class BaseTest {

    @Test
    public void save() throws IOException {
        Base base = new Base("", "stubSaveTest.yml");

        base.put("123", "321", "123123");
        base.put("1231", "321", "123123");
        Assert.assertEquals("123123", base.get("123", "321"));
        base.save();

        base = new Base("", "stubSaveTest.yml");
        Optional<String> opt = base.getOpt("123", "321");
        assertFalse(opt.isPresent());

        base.load();
        Assert.assertEquals("123123", base.get("123", "321"));

    }

    @Test
    public void saveMulti() throws IOException {
        Base base = new Base();

        base.put(new Document("keyv1", "keyv2", "keyv3").setValues("value2", "value3"))
                .put(new Document("keyv1.1", "keyv2", "keyv3").setValues("value2", "value3"))
                .put(new Document("keyv1.2", "keyv2", "keyv3").setValues("value2", "value3"))
                .put(new Document("keyv1.3", "keyv2", "keyv3").setValues("value2", "value4"));


        Iterator<String> r = base.getVals("keyv1.3", "keyv2", "keyv3");
        Assert.assertEquals("value2", r.next());
        Assert.assertEquals("value4", r.next());

        String[] r1 = base.requestArray("keyv1.3", "keyv2", "keyv3");
        Assert.assertEquals("value2", r1[0]);
        Assert.assertEquals("value4", r1[1]);

    }


    @Test
    public void request() {
        Base base = new Base();
        assertTrue(base.isNew());

        String rand = base.request(() -> {
            throw new RuntimeException();
        }, "rand", "1002");

        assertEquals("-1594594225", rand);

        assertFalse(base.isNew());

        String[] rands = base.requestArray(() -> {
            throw new RuntimeException();
        }, "rand", "1002");

        assertEquals("-1594594225", rands[0]);
        assertEquals("asdqwe", rands[1]);


        int val = base.request(() -> {
                    throw new RuntimeException();
                },
                (x) -> Integer.parseInt(x[0]),
                integer -> new String[]{integer.toString()},
                "rand", "1002");

        assertEquals(-1594594225, val);
    }

    @Test
    public void binaryDataTest() {
        Base base = new Base("", "stubBin.yml");

        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(0, 256).forEach(x -> stringBuilder.append((char) x));
        base.request(() -> stringBuilder.toString(), "binaryData");

        base = new Base("", "stubBin.yml");

        String binaryData = base.request("binaryData");

        assertEquals(stringBuilder.toString(), binaryData);

    }

    @Test(expected = NoSuchElementException.class)
    public void restrictionTest() {
        Base base = new Base();
        base.constrain(Base.RequestMode.rmNone);

        base.request("restrictionTest");
    }


}