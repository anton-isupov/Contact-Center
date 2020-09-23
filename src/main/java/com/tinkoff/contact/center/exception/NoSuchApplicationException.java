package com.tinkoff.contact.center.exception;

public class NoSuchApplicationException extends Exception {

    public NoSuchApplicationException() {
    }

    public NoSuchApplicationException(String message) {
        super(message);
    }

    public NoSuchApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchApplicationException(Throwable cause) {
        super(cause);
    }

    public NoSuchApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
