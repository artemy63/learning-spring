package org.artemy63.exceptionhandling.exception;

/**
 * Created by artemyev on 16.11.2017.
 */
public class BaseException extends RuntimeException {

    private String property;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BaseException(String message, String property) {
        super(message);
        this.property = property;
    }

    public String getProperty() {
        return property;
    }
}
