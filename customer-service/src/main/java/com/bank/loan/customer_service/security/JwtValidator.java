package com.bank.loan.customer_service.security;


import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.security.interfaces.RSAPublicKey;

@Component
public class JwtValidator {
    private final RSAPublicKey publicKey;
    public JwtValidator(RSAPublicKey publicKey) {
    	this.publicKey = publicKey; 
    	}

    public Jws<Claims> validate(String token) {
        return Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(token);
    }
}
