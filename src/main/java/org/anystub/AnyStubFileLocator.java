package org.anystub;

import java.lang.reflect.Method;

public class AnyStubFileLocator {

    private AnyStubFileLocator() {
    }

    public static String discoverFile() {
        String res = null;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement s : stackTrace) {
            try {
                Class<?> aClass = Class.forName(s.getClassName());
                AnyStubId classAnnotation = aClass.getDeclaredAnnotation(AnyStubId.class);
                if (classAnnotation != null) {
                    res = classAnnotation.filename().isEmpty() ?
                            aClass.getSimpleName() :
                            classAnnotation.filename();
                }
                Method method = aClass.getMethod(s.getMethodName());
                AnyStubId methodAnnotation = method.getAnnotation(AnyStubId.class);
                if (methodAnnotation != null) {
                    res = methodAnnotation.filename().isEmpty() ?
                            s.getMethodName() :
                            methodAnnotation.filename();
                }
            } catch (ClassNotFoundException | NoSuchMethodException ignored) {
                // it's acceptable that some class/method is not found
                // need to investigate when that happens
            }
            if (res != null && !res.isEmpty()) {
                break;
            }
        }
        if (res == null || res.endsWith(".yml")) {
            return res;
        }
        return res + ".yml";
    }

}
