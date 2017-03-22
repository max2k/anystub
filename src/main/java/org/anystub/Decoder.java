package org.anystub;

/**
 * Created by Kirill on 9/10/2016.
 * deserialize objects - from strings to object
 */
@FunctionalInterface
public interface Decoder<T extends Object>{
    T decode(Iterable<String> values);
}
