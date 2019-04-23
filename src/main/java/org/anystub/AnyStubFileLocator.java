package org.anystub;

import java.lang.reflect.Method;

public class AnyStubFileLocator {

    private AnyStubFileLocator() {
    }

    public static AnyStubId discoverFile() {
        String filename = null;
        AnyStubId id = null;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement s : stackTrace) {
            try {
                Class<?> aClass = Class.forName(s.getClassName());
                Method method = aClass.getMethod(s.getMethodName());
                id = method.getAnnotation(AnyStubId.class);

                if (id != null) {
                    filename = id.filename().isEmpty() ?
                            s.getMethodName() :
                            id.filename();
                    break;
                } else {
                    id = aClass.getDeclaredAnnotation(AnyStubId.class);
                    if (id != null) {
                        filename = id.filename().isEmpty() ?
                                aClass.getSimpleName() :
                                id.filename();
                        break;
                    }
                }
            } catch (ClassNotFoundException | NoSuchMethodException ignored) {
                // it's acceptable that some class/method is not found
                // need to investigate when that happens
            }
        }
        if (id == null) {
            return null;
        }
        if (!filename.endsWith(".yml")) {
            filename+=".yml";
        }

        return new AnyStubIdData(filename,
                id.requestMode());
    }

    public static AnyStubId discoverFile(String stubSuffix) {
        AnyStubId s = discoverFile();

        if (s == null || stubSuffix == null) {
            return s;
        }
        String filename = s.filename();
        if (filename.endsWith(".yml")) {
            filename = String.format("%s-%s.yml", filename.substring(0, filename.length() - 4), stubSuffix);
        } else {
            filename = String.format("%s-%s", s, stubSuffix);
        }
        return new AnyStubIdData(filename, s.requestMode());
    }
}
