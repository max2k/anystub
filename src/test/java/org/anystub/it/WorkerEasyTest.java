package org.anystub.it;

import org.anystub.Base;
import org.anystub.mgmt.BaseManagerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 *
 */
public class WorkerEasyTest {

    SourceSystem sourceSystem;

    @BeforeEach
    public void createStub() {
        Base base = BaseManagerFactory.getBaseManager().getBase("WorkerEasyTest.yml");
        sourceSystem = new SourceSystem("http://localhost:8080") {
            @Override
            public String get() throws IOException {
                return base.request(() -> super.get(),
                        getPath());
            }

            @Override
            public String getPath() {
                try {
                    return super.getPath();
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        };
    }

    @Test
    public void xTest() throws IOException {

        Worker worker = new Worker(sourceSystem);
        assertEquals("fixed", worker.get());
    }

}
