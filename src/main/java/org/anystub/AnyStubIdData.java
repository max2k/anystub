package org.anystub;

import java.lang.annotation.Annotation;

public class AnyStubIdData implements AnyStubId {

    private final String filename;
    private final RequestMode requestMode;

    public AnyStubIdData(String filename, RequestMode requestMode) {
        this.filename = filename;
        this.requestMode = requestMode;
    }

    @Override
    public String filename() {
        return filename;
    }

    @Override
    public RequestMode requestMode() {
        return requestMode;
    }

    public Class<? extends Annotation> annotationType() {
        return AnyStubIdData.class;
    }
}
