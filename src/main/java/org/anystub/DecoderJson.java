package org.anystub;

import com.fasterxml.jackson.core.JsonProcessingException;

public class DecoderJson<R extends Object> implements DecoderSimple<R>{
    private final Class<R> responseClass;

    public DecoderJson(Class<R> responseClass) {
        this.responseClass = responseClass;
    }

    @Override
    public R decode(String values) {
        R r = null;
        try {
            r = ObjectMapperFactory.get().readValue(values, responseClass);
        } catch (JsonProcessingException e) {
            throw new TypeNotPresentException(responseClass.getName(), e);
        }
        return r;
    }
}
