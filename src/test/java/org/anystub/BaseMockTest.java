package org.anystub;

import org.anystub.mgmt.BaseManagerImpl;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class BaseMockTest {


    @Test
    @Ignore("mock is not working with java 11")
    public void passThroughTest() throws Exception {
        DecoderSimple upstream = mock(DecoderSimple.class);

        Base base = BaseManagerImpl.instance()
                .getBase("passthrough.yml")
                .constrain(RequestMode.rmPassThrough);

        for (int i = 0; i < 4; i++) {

            base.request(new Supplier<String, Exception>() {
                @Override
                public String get() throws Exception {
                    return (String) upstream.decode("test");
                }
            }, "test");
        }

        verify(upstream, times(4)).decode(anyString());
        long count = base.history().count();
        assertEquals(0, count);
    }
}