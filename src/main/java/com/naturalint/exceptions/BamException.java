package com.naturalint.exceptions;

/**
 * Created by skn on 10/9/2017.
 */
public class BamException extends RuntimeException {

    public BamException() {
    }

    public BamException(String message) {
        super(message);
    }

    public BamException(String message, Throwable cause) {
        super(message, cause);
    }

    public BamException(Throwable cause) {
        super(cause);
    }

    public BamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
