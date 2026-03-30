package com.bank.loan.LoanApplicationServiceApplication.security;

import java.security.interfaces.RSAPublicKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {
    private final RSAPublicKey publicKey;
    public JwtValidator(RSAPublicKey publicKey) { this.publicKey = publicKey; }
    public Jws<Claims> validate(String token) { return Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(token); }
}