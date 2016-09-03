package org.anystub;

/**
 * Created by Kirill on 9/3/2016.
 */
@FunctionalInterface
public interface SupplierX<T, E extends Exception> {
    T get() throws E;
}
