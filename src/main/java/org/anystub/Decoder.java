package org.anystub;

/**
 * Created by Kirill on 9/10/2016.
 */
@FunctionalInterface
public interface Decoder<T extends Object>{
    T decode(String... values);
}
