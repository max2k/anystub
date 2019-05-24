package org.anystub;

import org.anystub.mgmt.BaseManagerImpl;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class BaseMockTest {

    public interface MockSupplier {
        String get() throws Exception;
    }

    @Test
//    @Ignore("mock is not working with java 11")
    public void passThroughTest() throws Exception {
        MockSupplier upstream = mock(MockSupplier.class);


        Base base = BaseManagerImpl.instance()
                .getBase("passthrough.yml")
                .constrain(RequestMode.rmPassThrough);

        for (int i = 0; i < 4; i++) {

            base.request(new Supplier<String, Exception>() {
                @Override
                public String get() throws Exception {
                    return upstream.get();
                }
            }, "test");
        }

        verify(upstream, times(4)).get();
        long count = base.history().count();
        assertEquals(0, count);
    }
}