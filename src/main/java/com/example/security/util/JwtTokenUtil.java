package com.example.security.util;

import com.example.security.entity.Customer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    // Generate a SecretKey from the given string
    private SecretKey secretKey = Keys.hmacShaKeyFor("mySecretKeymySecretKeymySecretKeymySecretKey".getBytes()); // Ensure the key is at least 32 characters for HS256

    public String generateToken(Customer customer) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", customer.getRole());
        System.out.println("1" + claims + customer.getName());
        return createToken(claims, customer.getName());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10)) // Token expiration set to 10 minutes
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token, String name) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(name) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Date extractExpiration(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
