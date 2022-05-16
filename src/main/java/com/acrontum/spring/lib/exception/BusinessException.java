package com.acrontum.spring.lib.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Business exception.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessException extends LocalizedException {
    /**
     * Instantiates a new Business exception.
     */
    public BusinessException() {
    }

    /**
     * Instantiates a new Business exception.
     *
     * @param message the message
     * @param args    the args
     */
    public BusinessException(String message, String... args) {
        super(message, args);
    }

    /**
     * Instantiates a new Business exception.
     *
     * @param message the message
     * @param cause   the cause
     * @param args    the args
     */
    public BusinessException(String message, Throwable cause, String... args) {
        super(message, cause, args);
    }

    /**
     * Instantiates a new Business exception.
     *
     * @param cause the cause
     */
    public BusinessException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Business exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public BusinessException(String message, Throwable cause, boolean enableSuppression,
                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
