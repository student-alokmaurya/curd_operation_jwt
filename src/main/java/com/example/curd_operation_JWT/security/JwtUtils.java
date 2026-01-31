package com.example.curd_operation_JWT.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private static final long EXPIRATION_TIME = 1000 * 60 * 24 * 7; // FOR 7 DAYS

    private static final String SECRET =
            "myjwtsecretkeymyjwtsecretkeymyjwtsecretkey";

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Generate JWT
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract Username
    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key)          // ✅ NEW
                .build()
                .parseSignedClaims(token) // ✅ NEW
                .getPayload()
                .getSubject();
    }

    // Validate Token
    public boolean validateToken(String token) {
        try {
            extractUsername(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }




}
