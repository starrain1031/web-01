package org.starry.webmanagement;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    public void testGenerateJwt() throws Exception {
//        Map<String, Object> dataMap = new HashMap<>();
//        dataMap.put("username", "admin");
//        dataMap.put("password", "123456");

        String secret = "starry-web-management-secret-key-123456";
        SecretKey skey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        String jwt = Jwts.builder()
                .subject("user-login-token")
                .claim("id", 1)
                .claim("username", "admin")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(skey)
                .compact();
        System.out.println(jwt);
    }

    @Test
    public void testParseJwt() throws Exception {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyLWxvZ2luLXRva2VuIiwiaWQiOjEsInVzZXJuYW1lIjoiYWRtaW4iLCJpYXQiOjE3NzgzMjcxNDIsImV4cCI6MTc3ODMzMDc0Mn0.t3UlcKGlsOcRmG7jB1k87dGdnsnJpkVWY6adibGcYFc";
        String secret = "starry-web-management-secret-key-123456";
        SecretKey skey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parser().verifyWith(skey).build()
                .parseSignedClaims(jwt)
                .getPayload();
        System.out.println(claims);
    }
}

