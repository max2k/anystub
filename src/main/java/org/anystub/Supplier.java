package org.anystub;

/**
 * Created by Kirill on 9/3/2016.
 */
@FunctionalInterface
public interface Supplier<T extends Object, E extends Throwable> {
    T get() throws E;

}
