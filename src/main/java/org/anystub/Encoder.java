package org.anystub;


/**
 * Created by Kirill on 9/10/2016.
 * serialize objects - from domain to strings
 * it won't process nulls
 */
@FunctionalInterface
public interface Encoder<T extends Object> {
    Iterable<String> encode(T t);
}
