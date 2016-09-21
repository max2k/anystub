package org.anystub;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

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

        int val =
                base.request2((Supplier<Integer, NoSuchElementException>) () -> {
                            throw new NoSuchElementException();
                        },
                        values -> Integer.parseInt(values.iterator().next()),
                        integer -> asList(integer.toString()),
                        "rand", "1002"
                );

        assertEquals(-1594594225, val);

        val =
                base.request((Supplier<Integer, NoSuchElementException>) () -> {
                            throw new NoSuchElementException();
                        },
                        values -> Integer.parseInt(values),
                        integer -> integer.toString(),
                        "rand", "1002"
                );

        assertEquals(-1594594225, val);
    }

    @Test
    public void binaryDataTest() {
        Base base = new Base("", "stubBin.yml");

        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(0, 256).forEach(x -> stringBuilder.append((char) x));
        assertEquals(256, stringBuilder.toString().length());
        base.request(() -> stringBuilder.toString(), "binaryData");

        base = new Base("", "stubBin.yml");

        String binaryData = base.request("binaryData");

//        binaryData.
//        Arrays.stream(binaryData.getBytes())
//                .ma
        assertArrayEquals(stringBuilder.toString().getBytes(), binaryData.getBytes());

    }

    @Test(expected = NoSuchElementException.class)
    public void restrictionTest() {
        Base base = new Base();
        base.constrain(Base.RequestMode.rmNone);

        base.request("restrictionTest");
    }


    static class Human {
        Integer id;
        Integer height;
        Integer age;
        Integer weight;
        String name;

        public Human(int id, int height, int age, int weight, String name) {
            this.height = height;
            this.age = age;
            this.weight = weight;
            this.name = name;
            this.id = id;
        }

        public Human() {
        }

        public List<String> toList() {
            ArrayList<String> res = new ArrayList<>();
            res.add(id.toString());
            res.add(height.toString());
            res.add(age.toString());
            res.add(weight.toString());
            res.add(name);
            return res;
        }
    }

    @Test
    public void requestComplexObject() {
        Human h = new Human(13, 180, 30, 60, "i'm");

        Base base = new Base("", "complexObject.yml");

        Human human = base.request2(() -> h,
                values -> {
                    Iterator<String> v = values.iterator();
                    return new Human(Integer.parseInt(v.next()),
                            Integer.parseInt(v.next()),
                            Integer.parseInt(v.next()),
                            Integer.parseInt(v.next()),
                            v.next());
                },
                human1 -> human1.toList()

                ,
                "13"
        );

        assertEquals(180, (int) human.height);
        assertEquals(30, (int) human.age);
        assertEquals(60, (int) human.weight);
        assertEquals("i'm", human.name);
        assertEquals(13, (int) human.id);


        base = new Base("", "complexObject.yml");

        human = base.request2(() -> {
                    throw new NoSuchElementException();
                },
                values -> {
                    Iterator<String> v = values.iterator();
                    return new Human(Integer.parseInt(v.next()),
                            Integer.parseInt(v.next()),
                            Integer.parseInt(v.next()),
                            Integer.parseInt(v.next()),
                            v.next());
                },
                human1 -> human1.toList(),
                "13"
        );

        assertEquals(180, (int) human.height);
        assertEquals(30, (int) human.age);
        assertEquals(60, (int) human.weight);
        assertEquals("i'm", human.name);
        assertEquals(13, (int) human.id);
    }

}