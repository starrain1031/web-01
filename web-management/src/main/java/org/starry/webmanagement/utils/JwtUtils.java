package org.starry.webmanagement.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static String secret = "starry-web-management-secret-key-123456";
    private static Long expire = 43200000L;
    private static SecretKey signKey;

    public static String generateJwt(Map<String,Object> claims){
        signKey = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.builder()
                .claims(claims)
                .signWith(signKey)
                .subject("user-login-token")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expire))
                .compact();
    }

    public static Claims parseJWT(String jwt){
        return Jwts.parser().verifyWith(signKey).build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}
