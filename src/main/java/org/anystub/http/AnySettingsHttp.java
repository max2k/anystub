package org.anystub.http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface AnySettingsHttp {

    /**
     * includes all headers in key
     * @return
     */
    boolean allHeaders() default false;

    /**
     * includes selected headers in a key,
     * if not empty then allHeaders ignored
     * @return
     */
    String[] headers() default {};

    /**
     * defines strings which trigger inclusions request body in a key if the string is a part of url
     * @return
     */
    String[] bodyTrigger() default {};

    /**
     * convertion for key, ex. to remove time
     * @return
     */
//    Function<String, String> keyProcessor() default null;
}
