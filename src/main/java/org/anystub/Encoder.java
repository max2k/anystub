package org.anystub;

/**
 * Created by Kirill on 9/10/2016.
 */
@FunctionalInterface
public interface Encoder<T extends Object> {
    Iterable<String> encode(T t);
}
