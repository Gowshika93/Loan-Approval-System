package com.bank.loan.auth_service.config;


import java.security.KeyPair;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bank.loan.auth_service.entity.Role;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
    private final KeyPair keyPair;

    @Value("${jwt.expiration-ms}")
    private long jwtExpirationMs;

    public JwtUtil(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public String generateToken(String username, Role role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(keyPair.getPrivate(), SignatureAlgorithm.RS256)
                .compact();
       
    }
}
