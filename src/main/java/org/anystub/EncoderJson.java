package org.anystub;

import com.fasterxml.jackson.core.JsonProcessingException;

public class EncoderJson<R extends Object>  implements EncoderSimple<R>{
    @Override
    public String encode(R r) {
        String s;
        try {
            s = ObjectMapperFactory.get().writeValueAsString(r);
        } catch (JsonProcessingException e) {
            s = "**encoding failed**";
        }
        return s;
    }
}
