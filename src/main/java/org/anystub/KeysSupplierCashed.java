package org.anystub;

public class KeysSupplierCashed implements KeysSupplier {
    private String[] keys = null;

    private final KeysSupplier keysSupplier;

    public KeysSupplierCashed(KeysSupplier keysSupplier) {
        this.keysSupplier = keysSupplier;
    }

    @Override
    public String[] get() {
        if (keys == null) {
            keys = keysSupplier.get();
        }
        return keys;
    }
}
