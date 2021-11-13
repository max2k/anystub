package org.anystub;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperFactory {
    public static  ObjectMapper objectMapper = null;

    public static  ObjectMapper get() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
        }
        return objectMapper;
    }

}
