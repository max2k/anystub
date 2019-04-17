package org.anystub.mgmt;

import org.anystub.Base;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BaseManagerImplTest {

    @Test
    public void scenario() {

        BaseManagerImpl.instance().getBase("test");

        boolean expectedError;
        try {
            new Base("test");
            expectedError = false;
        }catch (StubFileAlreadyCreatedException e) {
            expectedError = true;
        }

        assertTrue(expectedError);
        BaseManagerImpl.instance().getBase("test");
        BaseManagerImpl.instance().getBase("test");
        BaseManagerImpl.instance().getBase("test1");

        new Base("test2");
        BaseManagerImpl.instance().getBase("test2");

    }

}