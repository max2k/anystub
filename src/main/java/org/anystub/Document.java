package org.anystub;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;

/**
 * Created by Kirill on 9/2/2016.
 */
public class Document {

    private static Logger logger = Logger.getLogger(Document.class.getName());
    private final List<String> keys = new ArrayList<>();
    private final List<String> values = new ArrayList<>();
    private final List<String> exception = new ArrayList<>();

    public Document() {

    }

    public Document(String... keys) {
        stream(keys)
                .forEach(x -> this.keys.add(x));

    }

    public Document(Throwable ex, String... keys) {
        this.keys.addAll(asList(keys));

        this.exception.add(ex.getClass().getCanonicalName());
        this.exception.add(ex.getMessage());
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys.clear();
        this.keys.addAll(keys);
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values.clear();

        // regardless explicit: type List<String> values it could contain byte[] due to
        // using reflection in Yaml-snake
        for (int i = 0; i < values.size(); i++) {
            Object v = values.get(i);
            if (v instanceof String) {
                this.values.add((String) v);
            } else if (v.getClass().getName() == "[B") {
                byte[] x1 = (byte[]) v;
                logger.finest(()->
                        {
                            StringBuilder sb = new StringBuilder();
                            sb.append("assign binary data:");
                            for (int k = 0; k < x1.length; k++) {
                                sb.append(String.format(" x%02x", x1[k]));
                            }
                            return sb.toString();
                        });
                this.values.add(new String(x1, Charset.forName("UTF-8")));
            } else {
                throw new RuntimeException("Unexpected dataType");
            }
        }
    }

    public Document setValues(String... values) {
        this.values.clear();
        stream(values)
                .forEach(x -> this.values.add(x));

        return this;
    }
    public Document setValues(Iterable<String> values) {
        this.values.clear();
        values
                .forEach(x -> this.values.add(x));

        return this;
    }

    public List<String> getException() {
        return exception;
    }

    public void setException(List<String> exception) {
        this.exception.clear();
        this.exception.addAll(exception);
    }

    public String get() {
        return getVals().next();
    }

    public Iterator<String> getVals() {
        if (exception.isEmpty()) {
            if (values.isEmpty()) {
                return null;
            }
            return values.iterator();
        }

        // @todo create exec the same exception
        throw new RuntimeException(exception.get(1));

    }

    public boolean keyEqual_to(String... keys) {
        return this.keys.equals(asList(keys));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Document that = (Document) other;

        return keys.equals(that.keys);
    }


}
