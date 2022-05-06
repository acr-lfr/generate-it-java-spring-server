package com.acrontum.spring.lib.security;

import io.jsonwebtoken.Claims;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * The type Auth token filter.
 */
@Component
@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null) {
                Claims claims = jwtUtils.validateJwtToken(jwt);
                JwtPrincipal jwtPrincipal = toJwtPrincipal(claims);
                SecurityContextHolder.getContext()
                        .setAuthentication(toSpringAuthentication(request, jwt, jwtPrincipal));
            }
        } catch (Exception e) {
            logger.error("Cannot set {{ camel_case_operation }} authentication", e);
        }
        filterChain.doFilter(request, response);
    }

    private JwtPrincipal toJwtPrincipal(Claims source) {
        return GenericPrincipal.builder()
                .subject(source.getSubject())
                .name((String) source.getOrDefault(JwtPrincipal.CLAIM_NAME, source.getSubject()))
                .claims(Map.copyOf(source))
                .roles(toRoles(source))
                .build();
    }

    private Set<String> toRoles(Claims source) {
        return Optional.ofNullable(source.get(JwtPrincipal.CLAIM_ROLES, String.class))
                .map(roleInString -> roleInString.split(","))
                .map(Set::of)
                .orElse(Set.of());
    }

    private String parseJwt(HttpServletRequest request) {
        return Optional.of(request)
                .map(r -> r.getHeader("Authorization"))
                .filter(auth -> auth.startsWith("Bearer "))
                .map(auth -> auth.replaceFirst("Bearer ", ""))
                .orElse(null);
    }

    private Authentication toSpringAuthentication(
            HttpServletRequest request, String jwt, JwtPrincipal genericPrincipal) {
        List<SimpleGrantedAuthority> grantedAuthorities =
                genericPrincipal.getRoles().stream().map(SimpleGrantedAuthority::new).toList();
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(genericPrincipal, jwt, grantedAuthorities);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authentication;
    }
}