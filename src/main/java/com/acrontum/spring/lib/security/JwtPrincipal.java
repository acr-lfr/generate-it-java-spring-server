package com.acrontum.spring.lib.security;

import java.security.Principal;
import java.util.Map;
import java.util.Set;

/**
 * The interface Jwt principal.
 */
public interface JwtPrincipal extends Principal {

    /**
     * The constant CLAIM_NAME.
     */
    String CLAIM_NAME = "name";
    /**
     * The constant CLAIM_ROLES.
     */
    String CLAIM_ROLES = "roles";

    /**
     * Gets subject.
     *
     * @return the subject
     */
    String getSubject();

    /**
     * Gets roles.
     *
     * @return the roles
     */
    Set<String> getRoles();

    /**
     * Gets claims.
     *
     * @return the claims
     */
    Map<String, ?> getClaims();
}
