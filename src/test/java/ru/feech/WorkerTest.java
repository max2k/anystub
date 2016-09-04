package ru.feech;

import org.anystub.Base;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Kirill on 9/3/2016.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkerTest {


    @Test
    public void xTest() throws IOException {
        SourceSystem mock = Mockito.mock(SourceSystem.class);
        Worker worker = new Worker(mock);
        Mockito.when(mock.get()).thenReturn("mocked answer");
        assertEquals("mocked answer", worker.get());
    }

    @Autowired
    Worker worker;

    @Test
    public void xxTest() throws IOException {
        assertEquals("fixed", worker.get());
    }

    @Test
    public void randTest() throws IOException {
        assertTrue("1", -1594594225 == worker.rand());
        assertTrue("2", -1594594225 == worker.rand());
    }


    @TestConfiguration
    static class Conf {


        @Bean
        public SourceSystem sourceSystem() {

            Base base = new Base();
            base.init();

            return new SourceSystem("http://localhost:8080") {
                @Override
                public String get() throws IOException {
                    return base.request(() -> super.get(), "root");
                }

                /**
                 * pay attention: the result of the function depends on
                 * internal state of the object, which changed by invocation of the function
                 * @param digit
                 * @return
                 */
                @Override
                public Integer rand(int digit) {
                    return Integer.valueOf(
                            base.request(() -> Integer.toString(super.rand(digit)),
                                    "rand", Integer.toString(digit)));
                }
            };
        }
    }

}
