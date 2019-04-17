package org.anystub.mgmt;

public class StubFileAlreadyCreatedException extends RuntimeException {

    final String filePath;

    public StubFileAlreadyCreatedException(String filePath) {
        this.filePath = filePath;
    }

    public StubFileAlreadyCreatedException(String s, String filePath) {
        super(s);
        this.filePath = filePath;
    }

    public StubFileAlreadyCreatedException(String s, Throwable throwable, String filePath) {
        super(s, throwable);
        this.filePath = filePath;
    }

    public StubFileAlreadyCreatedException(Throwable throwable, String filePath) {
        super(throwable);
        this.filePath = filePath;
    }

    public StubFileAlreadyCreatedException(String s, Throwable throwable, boolean b, boolean b1, String filePath) {
        super(s, throwable, b, b1);
        this.filePath = filePath;
    }
}
