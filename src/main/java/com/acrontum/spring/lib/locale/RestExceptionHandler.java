package com.acrontum.spring.lib.locale;

import com.acrontum.spring.lib.AcrontumComponent;
import com.acrontum.spring.lib.exception.LocalizedException;
import java.time.Instant;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The type Rest exception handler.
 */
@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler extends AcrontumComponent {
    private final MessageSource messageSource;

    /**
     * Handle localized exception response entity.
     *
     * @param ex     the localized exception
     * @param locale the locale
     * @return the response entity
     */
    @ExceptionHandler(LocalizedException.class)
    public ResponseEntity<?> handleLocalizedException(LocalizedException ex,
                                                      Locale locale) {
        String localizedMessage = messageSource.getMessage(ex.getMessage(), ex.getArgs(), ex.getMessage(), locale);
        HttpStatus httpStatus = Optional.ofNullable(ex.getClass().getAnnotation(ResponseStatus.class))
                .map(ResponseStatus::value)
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);

        var response = Map.of("message", localizedMessage,
                "timestamp", Instant.now(),
                "status", httpStatus.value(),
                "error", httpStatus.getReasonPhrase());

        logger.error(localizedMessage, ex);
        return new ResponseEntity<>(response, httpStatus);
    }

}
