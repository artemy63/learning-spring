package org.artemy63.exceptionhandling.exception;

public class UnHandledException extends BaseException {

    public UnHandledException() {
    }

    public UnHandledException(String message) {
        super(message);
    }

    public UnHandledException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnHandledException(Throwable cause) {
        super(cause);
    }

    public UnHandledException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UnHandledException(String message, String property) {
        super(message, property);
    }
}
