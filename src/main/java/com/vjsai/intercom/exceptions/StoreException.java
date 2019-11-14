package com.vjsai.intercom.exceptions;

public class StoreException extends Exception {
    public StoreException() {
    }

    public StoreException(String s) {
        super(s);
    }

    public StoreException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public StoreException(Throwable throwable) {
        super(throwable);
    }

    public StoreException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
