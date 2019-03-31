package org.anystub.it;

import org.anystub.Base;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkerTest {



    @Autowired
    private Worker worker;

    @Autowired
    private SourceSystem sourceSystem;

    @Autowired
    private Base base;

    @Test
    public void xTest() throws IOException {
        assertEquals("fixed", sourceSystem.get());
    }

    @Test
    public void xxTest() throws IOException {
        base.clear();

        assertEquals("fixed", worker.get());

        assertEquals(1, base.times());
    }

    @Test
    public void randTest() throws IOException {
        base.clear();
        assertEquals("1", -1594594225, worker.rand());
        assertEquals("2", -1594594225, worker.rand());
        assertEquals(2L, base.times());

    }


    @TestConfiguration
    static class Conf {

        @Bean
        Base base() {
            return new Base();
        }

        @Bean
        public SourceSystem sourceSystem(Base base) {


            return new SourceSystem("http://localhost:8080") {
                @Override
                public String get() throws IOException {
                    return base.request("root");
                }

                /**
                 * pay attention: the result of the function depends on
                 * internal state of the object, which changed by invocation of the function
                 * @param digit should has the same mean as in super class
                 * @return from upstream or from
                 */
                @Override
                public Integer rand(int digit) {
                    return Integer.valueOf(
                            base.request("rand", Integer.toString(digit)));
                }
            };
        }
    }

}
