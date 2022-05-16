package com.acrontum.spring.lib;

import com.acrontum.spring.lib.security.JwtPrincipal;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * The type Acrontum component.
 */
public class AcrontumComponent {

    /**
     * The Logger.
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Gets principal.
     *
     * @return the principal
     */
    protected JwtPrincipal getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (JwtPrincipal) authentication.getPrincipal();
    }

    /**
     * Gets locale.
     *
     * @return the locale
     */
    protected Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

}
