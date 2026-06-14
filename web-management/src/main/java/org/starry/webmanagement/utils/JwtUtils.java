package org.starry.webmanagement.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

/**
 * Utility methods for generating and parsing JWT authentication tokens.
 */
public class JwtUtils {

    private static final String secret = "starry-web-management-secret-key-123456";
    private static final Long expire = 43200000L;

    private static final SecretKey signKey = Keys.hmacShaKeyFor(secret.getBytes());

    /**
     * Generates a signed JWT token from the supplied claims.
     */
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .claims(claims)
                .signWith(signKey)
                .subject("user-login-token")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expire))
                .compact();
    }

    /**
     * Parses and validates a signed JWT token.
     */
    public static Claims parseJWT(String jwt) {
        return Jwts.parser()
                .verifyWith(signKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}