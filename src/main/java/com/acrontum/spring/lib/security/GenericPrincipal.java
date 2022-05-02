package com.acrontum.spring.lib.security;

import java.util.Map;
import java.util.Set;
import lombok.Builder;
import lombok.Value;

/**
 * The type Generic principal.
 */
@Value
@Builder
public class GenericPrincipal implements JwtPrincipal {

    String subject;
    String name;
    Set<String> roles;
    Map<String, ?> claims;

    @Override
    public String getName() {
        return name;
    }

}
