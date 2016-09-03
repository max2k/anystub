package org.anystub;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;

/**
 * Created by Kirill on 9/2/2016.
 */
public class Document {
    private final List<String> keys = new ArrayList<>();
    private final List<String> values = new ArrayList<>();
    private final List<String> exception = new ArrayList<>();
    private String xxx = "___++";

//    public String getXxx() {
//        return xxx;
//    }

    public void setXxx(String xxx) {
        this.xxx = xxx;
    }

    public Document()
    {

    }

    public Document(String... keys) {
        stream(keys)
                .forEach(x -> this.keys.add(x));

    }

    public Document(Throwable ex, String... keys)
    {
        this.keys.addAll(asList(keys));

        this.exception.add(ex.getClass().getCanonicalName());
        this.exception.add(ex.getMessage());
    }

    public List<String> getKeys() {
        return keys;
    }
    public void setKeys(List<String> keys)
    {
        this.keys.clear();
        this.keys.addAll(keys);
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values)
    {
        this.values.clear();
        this.values.addAll(values);
    }

    public Document setValues(String... values)
    {
        stream(values)
                .forEach(x -> this.values.add(x));

        return this;
    }
    public List<String> getException() {
        return exception;
    }

    public void setException(List<String> exception)
    {
        this.exception.clear();
        this.exception.addAll(exception);
    }

    public String get()
    {
        if(exception.isEmpty())
        {
            if(values.isEmpty())
            {
                return null;
            }
            return values.get(0);
        }

        // @todo create exec the same exception
        throw new RuntimeException(exception.get(1));
    }

    public boolean keyEqual_to(String... keys)
    {
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
