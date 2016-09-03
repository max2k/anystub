package org.anystub;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by Kirill on 9/3/2016.
 */
public class BaseTest {

//    @Test
    public void load()
    {
//        new Base().load();
    }

    @Test
    public void save() throws IOException {
        Base base = new Base("", "stubSaveTest.yml");

        base.put("123", "321", "123123");
        base.put("1231", "321", "123123");
        Assert.assertEquals("123123", base.get("123", "321"));
        base.save();

        base = new Base("", "stubSaveTest.yml");
        Optional<String> opt = base.getOpt("123", "321");
        Assert.assertFalse(opt.isPresent());

        base.load();
        Assert.assertEquals("123123", base.get("123", "321"));


    }

}