package org.anystub.mgmt;

import org.anystub.Base;

import java.util.function.Consumer;

final public class BaseManagerFactory {
    private static BaseManager baseManager = null;

    public static BaseManager getBaseManager() {
        if (baseManager == null) {
            baseManager = BaseManagerImpl.instance();
        }
        return baseManager;
    }

    public static Base getStub() {
        return BaseManagerFactory.getBaseManager().getStub();
    }

    /**
     * sets default stub initializer
     * you can use it to define default properties for all stubs
     *
     * @param initializer
     */
    public static void setDefaultStubInitializer(Consumer<Base> initializer) {
        if (initializer == null) {
            BaseManagerFactory.baseManager = null;
            return;
        }
        BaseManagerFactory.baseManager = new BaseManagerImpl() {
            @Override
            protected void stubInitialization(Base newStub) {
                initializer.accept(newStub);
            }
        };
    }
}
