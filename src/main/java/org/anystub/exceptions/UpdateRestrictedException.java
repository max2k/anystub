package org.anystub.exceptions;

/**
 * Created by Kirill on 9/2/2016.
 */
public class UpdateRestrictedException extends RuntimeException {
    public UpdateRestrictedException() {
    }

    public UpdateRestrictedException(String s) {
        super(s);
    }

    public UpdateRestrictedException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public UpdateRestrictedException(Throwable throwable) {
        super(throwable);
    }

    public UpdateRestrictedException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
