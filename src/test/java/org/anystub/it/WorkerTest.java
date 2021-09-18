package org.anystub.it;

import org.anystub.Base;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Created
 */

@SpringBootTest
public class WorkerTest {

    @Autowired
    private Worker worker;

    @Autowired
    private SourceSystem sourceSystem;

    @Autowired
    private Base base;

    @Test
    public void testXTest() throws IOException {
        assertEquals("fixed", sourceSystem.get());
    }

    @Test
    public void testXxTest() throws IOException {
        base.clear();

        assertEquals("fixed", worker.get());

        assertEquals(1, base.times());
    }

    @Test
    public void testRandTest() throws IOException {
        base.clear();
        assertEquals(-1594594225, worker.rand());
        assertEquals(-1594594225, worker.rand());
        assertEquals(2L, base.times());

    }


}
