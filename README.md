*MOVED TO [https://github.com/anystub/anystub](https://github.com/anystub/anystub)*

# anystub   [![Build Status](https://travis-ci.org/anystub/anystub.svg?branch=master)](https://travis-ci.org/anystub/anystub)
record input/output for tests in java. inspired by vcr for rails

Install from Maven Central 
===

``` xml
    <dependency>
      <groupId>org.anystub</groupId>
      <artifactId>anystub</artifactId>
      <version>0.1.3</version>
    </dependency>
```


Example
===
you can find an example of using in tests folder of the repository

you have a `class SourceSystem` which access to external resources.
``` java
public class SourceSystem {

    public String get() throws IOException {
        URL myURL = new URL("http://localhost:8080/");
        URLConnection myURLConnection = myURL.openConnection();
        myURLConnection.connect();


        InputStream inputStream = myURLConnection.getInputStream();
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .collect(Collectors.joining());
    }


}
```

When you check the class enough you whant to go futher and tests classes that use the source system

``` java 
public class Worker {

    private SourceSystem sourceSystem;

    public Worker(SourceSystem sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String get() throws IOException {
        return sourceSystem.get();
    }
}
```

to make sure you tests will run everywhere you need to create stub for the SourceSystem and record data that is necasserily for the tests

``` java
public class WorkerEasyTest {

    SourceSystem sourceSystem;
    Base base;

    @Before
    public void createStub()
    {
        base = new Base();
        sourceSystem = new SourceSystem("http://localhost:8080") {
            @Override
            public String get() throws IOException {
                return base.request(() -> super.get(), "root");
            }
        };
    }

    @Test
    public void easyTest() throws IOException {

        Worker worker = new Worker(sourceSystem);
        assertEquals("fixed", worker.get());
        assertEquals(1, base.times());
    }

}
```

By default your data is kept in src/test/resources/anystub/stub.yml. base.request(Supplier<>, String keys..) looks for stored data in the file. In case the search is successful the found result is returned to the client. In other way your supplier would be invoced, produced value will be keep in the file the it will be provided for clients.

**Recap**

your workflow scenario:
- extend your sourceSystem
- create stub on your local system and add it to the repository
- get benefits from automatic regression testing
