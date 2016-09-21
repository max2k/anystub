package org.anystub;

/**
 * Created by Kirill on 9/10/2016.
 */
@FunctionalInterface
public interface DecoderSimple<T extends Object>{
    T decode(String values);
}
