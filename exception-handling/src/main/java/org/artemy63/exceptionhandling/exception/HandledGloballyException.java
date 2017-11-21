package org.artemy63.exceptionhandling.exception;

public class HandledGloballyException extends BaseException {

    public HandledGloballyException() {
    }

    public HandledGloballyException(String message) {
        super(message);
    }

    public HandledGloballyException(String message, Throwable cause) {
        super(message, cause);
    }

    public HandledGloballyException(Throwable cause) {
        super(cause);
    }

    public HandledGloballyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public HandledGloballyException(String message, String property) {
        super(message, property);
    }
}
