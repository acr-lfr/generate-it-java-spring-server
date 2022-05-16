package com.acrontum.spring.lib.security;

import com.acrontum.spring.lib.exception.SystemException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The type Jwt utils.
 */
@Component
public class JwtUtils {
    @Value("${acrontum.jwt.secret}")
    private String jwtSecret;

    @Value("${acrontum.jwt.exp}")
    private long exp;

    /**
     * To jwt string.
     *
     * @param principal the principal
     * @return the string
     */
    public String toJwt(GenericPrincipal principal) {
        String roles = String.join(",", principal.getRoles());

        return Jwts.builder()
                .setSubject(principal.getSubject())
                .claim(JwtPrincipal.CLAIM_ROLES, roles)
                .claim(JwtPrincipal.CLAIM_NAME, principal.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }


    /**
     * Validate jwt token claims.
     *
     * @param authToken the auth token
     * @return the claims
     */
    public Claims validateJwtToken(String authToken) {
        try {
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken).getBody();
        } catch (JwtException e) {
            throw new SystemException("Invalid JWT", e);
        }
    }
}
