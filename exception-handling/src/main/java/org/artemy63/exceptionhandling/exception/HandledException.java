package org.artemy63.exceptionhandling.exception;

/**
 * Created by artemyev on 16.11.2017.
 */
public class HandledException extends BaseException{

    public HandledException() {
    }

    public HandledException(String message) {
        super(message);
    }

    public HandledException(String message, Throwable cause) {
        super(message, cause);
    }

    public HandledException(Throwable cause) {
        super(cause);
    }

    public HandledException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public HandledException(String message, String property) {
        super(message, property);
    }
}
