package org.anystub.http;

import java.lang.reflect.Method;

public class AnySettingsHttpExtractor {

    public static AnySettingsHttp discoverSettings() {
        AnySettingsHttp id = null;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement s : stackTrace) {
            Class<?> aClass = null;
            try {
                aClass = Class.forName(s.getClassName());
            } catch (ClassNotFoundException ignored) {

            }
            if (aClass == null) {
                continue;
            }
            try {
                Method method;
                method = aClass.getMethod(s.getMethodName());
                id = method.getAnnotation(AnySettingsHttp.class);
            } catch (NoSuchMethodException ignored) {
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
