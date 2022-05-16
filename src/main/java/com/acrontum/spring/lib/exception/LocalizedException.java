package com.acrontum.spring.lib.exception;

/**
 * The type Localized exception.
 */
public class LocalizedException extends RuntimeException {

    private String[] args = new String[] {};

    /**
     * Instantiates a new Localized exception.
     */
    public LocalizedException() {
    }

    /**
     * Instantiates a new Localized exception.
     *
     * @param message the message
     * @param args    the args
     */
    public LocalizedException(String message, String... args) {
        super(message);
        this.args = args;
    }

    /**
     * Instantiates a new Localized exception.
     *
     * @param message the message
     * @param cause   the cause
     * @param args    the args
     */
    public LocalizedException(String message, Throwable cause, String... args) {
        super(message, cause);
        this.args = args;
    }

    /**
     * Instantiates a new Localized exception.
     *
     * @param cause the cause
     */
    public LocalizedException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Localized exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public LocalizedException(String message, Throwable cause, boolean enableSuppression,
                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Get args string [ ].
     *
     * @return the string [ ]
     */
    public String[] getArgs() {
        return this.args;
    }

}
