package org.anystub.http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface AnySettingsHttp {

    /**
     * override global settings for headers, bodyTrigger, bodyMask
     * ex. global settings define headers: "Context-Type" which requires to include it in every request
     *   in local test you can suppress adding it into stub
     * allHeaders - always overrides global, if not defined in class/method level then it is false
     * if true - overrider global settings
     * if false - enrich global settings
     * @return
     */
    boolean overrideGlobal() default false;

    /**
     * includes all headers in key
     * @return
     */
    boolean allHeaders() default false;

    /**
     * to define headers which will be included in a request key,
     * if not empty then allHeaders ignored
     * @return
     */
    String[] headers() default {};

    /**
     * to define URLs which trigger inclusions request body in a key
     * @return
     */
    String[] bodyTrigger() default {};

    /**
     * to define regex expressions which will be replaced by elapses "..." in request body
     * @return
     */
    String[] bodyMask() default {};


}
