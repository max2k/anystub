package org.anystub.http;

import java.lang.reflect.Method;

public class AnySettingsHttpExtractor {

    private AnySettingsHttpExtractor() {

    }

    public static AnySettingsHttp discoverSettings() {
        AnySettingsHttp id ;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement s : stackTrace) {
            if (s.getMethodName().startsWith("lambda$")) {
                continue;
            }
            Class<?> aClass;
            try {
                aClass = Class.forName(s.getClassName());
            } catch (ClassNotFoundException ignored) {
                aClass = null;
            }
            if (aClass == null) {
                continue;
            }
            try {
                Method method;
                method = aClass.getDeclaredMethod(s.getMethodName());
                id = method.getAnnotation(AnySettingsHttp.class);
            } catch (NoSuchMethodException ignored) {
                id = null;
            }
            if (id != null) {
                break;
            }
            id = aClass.getDeclaredAnnotation(AnySettingsHttp.class);
            if (id != null) {
                break;
            }

        }
        return id;
    }

}
