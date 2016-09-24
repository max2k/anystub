package org.anystub;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;

/**
 * Document for keeping requests/response
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

    /**
     * for internal use
     * @return keys
     */
    public List<String> getKeys() {
        return keys;
    }

    /**
     * for internal use
     * @param keys list of keys
     */
    public void setKeys(List<String> keys) {
        this.keys.clear();
        this.keys.addAll(keys);
    }

    /**
     * for internal use
     * @return list of values
     */
    public List<String> getValues() {
        return values;
    }

    /**
     * for internal use
     * @param values list of values
     */
    public void setValues(List<String> values) {
        this.values.clear();

        // don't touch here
        // regardless explicit: type List<String> values it could contain byte[] due to
        // using reflection in Yaml-snake
        for (String value : values) {
            Object v = value;
            if (v instanceof String) {
                this.values.add((String) v);
            } else if (v.getClass().getName() == "[B") {
                byte[] x1 = (byte[]) v;
                logger.finest(() ->
                {
                    StringBuilder sb = new StringBuilder();
                    sb.append("assign binary data:");
                    for (byte aX1 : x1) {
                        sb.append(String.format(" x%02x", aX1));
                    }
                    return sb.toString();
                });
                this.values.add(new String(x1, Charset.forName("UTF-8")));
            } else {
                throw new RuntimeException("Unexpected dataType");
            }
        }
    }

    /**
     * for internal use
     * @return description of Exception
     */
    public List<String> getException() {
        return exception;
    }

    /**
     * for internal use
     * @param exception exception's description. the description consist of
     *                  one or two elements, the 1st is type of the exception,
     *                  the 2nd is message of the exception
     */
    public void setException(List<String> exception) {
        this.exception.clear();
        this.exception.addAll(exception);
    }

    public Document setValues(String... values) {
        this.values.clear();
        stream(values)
                .forEach(this.values::add);

        return this;
    }

    public Document setValues(Iterable<String> values) {
        this.values.clear();
        values
                .forEach(this.values::add);

        return this;
    }

    public String get() {
        return getVals().next();
    }

    public <E extends Throwable> Iterator<String> getVals() throws E{
        if (exception.isEmpty()) {
            if (values.isEmpty()) {
                return null;
            }
            return values.iterator();
        }

        try {
            Class<?> aClass = Class.forName(exception.get(0));

            Constructor<?> constructor = aClass.getConstructor(String.class);
            Object o = constructor.newInstance(exception.get(1));
            throw (E) o;
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            String msg = String.format("exception class %s not found", exception.get(0));
            logger.warning(()->msg);
            throw new RuntimeException(exception.get(1));
        }

    }

    public boolean keyEqual_to(String... keys) {
         return this.keys.equals(asList(keys));
    }

    /**
     * exact matching
     * @param keys keys for matching
     * @return true if document is matched
     */
    public boolean match_to(String... keys){
        if(this.keys.size()<keys.length)
        {
            return false;
        }
        for(int i=0; i<keys.length; i++){
            if(keys[i]==null){
                continue;
            }
            if(!keys[i].equals(this.keys.get(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * match documents using keys
     * matching uses regexp
     * @param keys keys for matching
     * @return true id document is matched
     */
    public boolean matchEx_to(String... keys) {
        if(keys==null || keys.length==0){
            return true;
        }
        if (this.keys.size() < keys.length ) {
            return false;
        }
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] == null) {
                continue;
            }
            if (!Pattern.matches(keys[i], this.keys.get(i))) {
                return false;
            }
        }
        return true;
    }
    /**
     * match documents using keys and values
     * matching uses regexp
     * @param keys values for matching by keys
     * @param values values for matching by values
     * @return true if document is matched
     */
    public boolean matchEx_to(String []keys, String[] values){
        if( this.values.size()<values.length)
        {
            return false;
        }
        if(!matchEx_to(keys)){
            return false;
        }
        for(int i=0; i<values.length; i++){
            if(values[i]==null){
                continue;
            }
            if(!Pattern.matches(values[i], this.values.get(i)))
            {
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Document that = (Document) other;

        if(keys.isEmpty() && that.keys.isEmpty()){
            return true;
        }
        return keys.equals(that.keys);
    }


    /**
     * return an array of strings
     * @param keys values of array
     * @return array of string
     */
    public static String[] ars(String... keys){
        if(keys==null || keys.length==0){
            return new String[0];
        }
        return keys;
    }


}
