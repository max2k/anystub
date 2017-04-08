package org.anystub;

import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;
import static java.util.Collections.emptyList;
import static org.anystub.Document.ars;
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
        assertEquals("123123", base.get("123", "321"));
        base.save();

        base = new Base("", "stubSaveTest.yml");
        Optional<String> opt = base.getOpt("123", "321");
        assertFalse(opt.isPresent());

        String request = base.request("123", "321");
        assertEquals("123123", request);
        opt = base.getOpt("123", "321");
        assertTrue(opt.isPresent());

        base = new Base("", "stubSaveTest.yml")
                .constrain(Base.RequestMode.rmNone);
        opt = base.getOpt("123", "321");
        assertTrue(opt.isPresent());
    }


    @Test
    public void stringRequest() {
        Base base = new Base();
        String request = base.request(() -> "xxx", "qwe", "stringkey");

        assertEquals("xxx", request);
    }

    @Test
    public void request() {
        Base base = new Base();
        assertTrue(base.isNew());

        String rand = base.request("rand", "1002");

        assertEquals("-1594594225", rand);

        assertFalse(base.isNew());

        String[] rands = base.requestArray(Base::throwNSE, "rand", "1002");

        assertEquals("-1594594225", rands[0]);
        assertEquals("asdqwe", rands[1]);

        int val = base.request2(Base::throwNSE,
                values -> parseInt(values.iterator().next()),
                new Encoder<Integer>() {
                    @Override
                    public Iterable<String> encode(Integer integer) {
                        return Collections.singletonList(integer.toString());
                    }
                },
                "rand", "1002"
        );

        assertEquals(-1594594225, val);

        val = base.request(Integer::parseInt,
                "rand", "1002"
        );

        assertEquals(-1594594225, val);
    }

    @Test(expected = NoSuchElementException.class)
    public void requestException() {
        Base base = new Base();
        assertTrue(base.isNew());

        base.request("rand", "1002", "notakey");
    }

    @Test
    public void binaryDataTest() {
        Base base = new Base("", "stubBin.yml");

        byte[] arr = new byte[256];
        IntStream.range(0, 256).forEach(x -> arr[x] = (byte) (x));
        base.request(() -> arr,
                new DecoderSimple<byte[]>() {
                    @Override
                    public byte[] decode(String values) {
                        return
                                Base64.getDecoder().decode(values);
                    }
                },
                new EncoderSimple<byte[]>() {
                    @Override
                    public String encode(byte[] values) {
                        return
                                Base64.getEncoder().encodeToString(values);
                    }
                }
                ,
                "binaryDataB64");


        base = new Base("", "stubBin.yml");
        byte[] arr1 = base.request(Base::throwNSE,
                s -> Base64.getDecoder().decode(s),
                Base::throwNSE,
                "binaryDataB64");


        assertArrayEquals(arr, arr1);
        arr1 = base.request(s -> Base64.getDecoder().decode(s),
                "binaryDataB64");


        assertArrayEquals(arr, arr1);

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
    public void requestNull() {

        Base base = new Base("", "NullObj.yml");
        Human human = base.request2(() -> null,
                values -> null,
                x -> emptyList(),
                "13"
        );
        assertNull(human);
    }

    @Test
    public void requestComplexObject() {
        Human h = new Human(13, 180, 30, 60, "i'm");

        Base base = new Base("", "complexObject.yml");
        Human human = base.request2(() -> h,
                values -> {
                    Iterator<String> v = values.iterator();
                    return new Human(parseInt(v.next()),
                            parseInt(v.next()),
                            parseInt(v.next()),
                            parseInt(v.next()),
                            v.next());
                },
                Human::toList

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
                    return new Human(parseInt(v.next()),
                            parseInt(v.next()),
                            parseInt(v.next()),
                            parseInt(v.next()),
                            v.next());
                },
                Human::toList,
                "13"
        );

        assertEquals(180, (int) human.height);
        assertEquals(30, (int) human.age);
        assertEquals(60, (int) human.weight);
        assertEquals("i'm", human.name);
        assertEquals(13, (int) human.id);
    }

    @Test
    public void historyCheck() {
        Base base = new Base("", "historyCheck.yml");

        assertEquals(0L, base.times());

        base.request(() -> "okok", "2", "3", "3");
        base.request(() -> "okok", "2", "3", "4");
        base.request(() -> "okok", "2", "3", "4");
        base.request(() -> "okok", "5", "3", "4");
        base.request(() -> "okok", "5");

        assertEquals(5L, base.times());
        assertEquals(5L, base.history().count());
        assertEquals(1L, base.history("5").count());
        assertEquals(2L, base.times("2", "3", "4"));
        assertEquals(1L, base.times("5", "3", "4"));
        assertEquals(3L, base.match("2").count());
        assertEquals(3L, base.times("2"));
        assertEquals(4L, base.times(null, null));
        assertEquals(3L, base.times(null, null, "4"));
    }

    @Test
    public void nullMatching() {
        Base base = new Base("", "historyCheck.yml")
                .constrain(Base.RequestMode.rmNew);

        assertEquals(0L, base.times());

        base.request(() -> "okok", "", "3", "3");
        base.request(() -> "okok", null, "3", "4");

        assertEquals(2, base.times());
        assertEquals(1, base.times(""));
        assertEquals(2, base.times(null, null));
    }

    @Test
    public void regexpMatching() {
        Base base = new Base("", "historyCheck.yml");

        base.request(() -> "okok", "2222", "3", "3");
        base.request(() -> "okok", "2321", "3345", "4");
        base.request(() -> "okok", "532", "3", "4");
        base.request(() -> "okok", "5456456");

        assertEquals(4, base.matchEx(ars(), ars(".*ko.*")).count());
        assertEquals(4, base.matchEx().count());
        assertEquals(4, base.timesEx());
        assertEquals(3, base.matchEx(null, "3.*").count());
        assertEquals(1, base.matchEx(".*56.*").count());
        assertEquals(1, base.timesEx(".*56.*"));
        assertEquals(4, base.timesEx(ars(), ars(".ko.")));

        assertEquals(4, base.history().count());

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void exceptionTest() {
        Base base = new Base("./exceptionStub.yml");
        boolean exceptionCaught = false;
        try {
            base.request(() -> {
                throw new IndexOutOfBoundsException("for test");
            }, "key");
        } catch (IndexOutOfBoundsException ex) {
            exceptionCaught = true;
        }

        assertTrue(exceptionCaught);

        base = new Base("./exceptionStub.yml");
        exceptionCaught = false;
        try {
            base.request(() -> {
                throw new IndexOutOfBoundsException("for test");
            }, "key");
        } catch (IndexOutOfBoundsException ex) {
            exceptionCaught = true;
        }

        assertTrue(exceptionCaught);
        base.request(() -> "okok", "key");
    }

    @Test
    public void nullReturning() {
        Base base = new Base("./nullReturning.yml");

        String[] emptyResult = base.requestArray(() -> null,
                "nullKey");

        assertNull(emptyResult);

        emptyResult = base.requestArray(() -> {
                    throw new NoSuchElementException();
                },
                "nullKey");
        assertNull(emptyResult);

        assertNull(base.request("nullKey"));
    }

    @Test
    public void request_oneway_object() throws IOException {
        Base base = new Base("./streams.yml").constrain(Base.RequestMode.rmAll);
        base.clear();
        base.save();


        BufferedReader v1 = base.request(
                (Supplier<BufferedReader, IOException>) () -> new BufferedReader(new StringReader("test")),
                values -> new BufferedReader(new StringReader(values)),
                bufferedReader -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException e) {
                        throw new RuntimeException("", e);
                    }
                },
                "21");

        assertEquals("test", v1.readLine());

    }

    static class AAA implements Serializable {
        int aaa = 1;
        Integer s = 15;
    }

    @Test
    public void requestSerializableTest() throws IOException {
        Base base = new Base("./serialize.yml");

        AAA aaa = base.requestSerializable(() -> new AAA(), "123");
        assertEquals(1, aaa.aaa);
        assertEquals(Integer.valueOf(15), aaa.s);
        aaa = base.requestSerializable(() -> null, "123");
        assertEquals(1, aaa.aaa);
        assertEquals(Integer.valueOf(15), aaa.s);
    }

}