package org.anystub.mgmt;

import org.anystub.AnyStubFileLocator;
import org.anystub.AnyStubId;
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
    public static Base getStub(String suffix) {
        return BaseManagerFactory.getBaseManager().getStub(suffix);
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

    public static Base locate() {
        return BaseManagerFactory.locate(null);
    }

    public static Base locate(String fallback) {
        AnyStubId s = AnyStubFileLocator.discoverFile();
        if (s != null) {
            return BaseManagerFactory
                    .getBaseManager()
                    .getBase(s.filename())
                    .constrain(s.requestMode());
        }

        return BaseManagerFactory
                .getBaseManager()
                .getBase(fallback);
    }
}
