package com.acrontum.spring.lib.exception;

/**
 * The type System exception.
 */
public class SystemException extends LocalizedException {

    /**
     * Instantiates a new System exception.
     */
    public SystemException() {
    }

    /**
     * Instantiates a new System exception.
     *
     * @param message the message
     * @param args    the args
     */
    public SystemException(String message, String... args) {
        super(message, args);
    }

    /**
     * Instantiates a new System exception.
     *
     * @param message the message
     * @param cause   the cause
     * @param args    the args
     */
    public SystemException(String message, Throwable cause, String... args) {
        super(message, cause, args);
    }

    /**
     * Instantiates a new System exception.
     *
     * @param cause the cause
     */
    public SystemException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new System exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public SystemException(String message, Throwable cause, boolean enableSuppression,
                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
