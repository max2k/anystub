package org.anystub;

import com.sun.istack.internal.NotNull;

/**
 * Created by Kirill on 9/10/2016.
 * serialize objects - from domain to strings
 * null must be presented as list of nulls. ex. "asList(null)"
 */
@FunctionalInterface
public interface Encoder<T extends Object> {
    @NotNull
    Iterable<String> encode(T t);
}
