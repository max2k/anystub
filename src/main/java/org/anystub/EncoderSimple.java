package org.anystub;

/**
 * Created by Kirill on 9/10/2016.
 */
@FunctionalInterface
public interface EncoderSimple<T extends Object> {
    String encode(T t);
}
