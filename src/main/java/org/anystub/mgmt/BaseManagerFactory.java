package org.anystub.mgmt;

import org.anystub.Base;

import java.util.function.Consumer;

public final class BaseManagerFactory {
    private static BaseManager baseManager = null;

    private BaseManagerFactory() {
    }

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
     * sets default stub initializer.
     * You can use it to define default properties for all stubs.
     *
     * When you define new initializer new BaseManager is created. hence all stubs switch to new state (with no history).
     * To recover default initializer pass null. It will recover all stubs with their statuses and call history
     *
     * @param initializer
     */
    synchronized public static void setDefaultStubInitializer(Consumer<Base> initializer) {
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
