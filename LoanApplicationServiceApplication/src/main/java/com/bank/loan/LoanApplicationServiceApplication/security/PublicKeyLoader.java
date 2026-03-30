package com.bank.loan.LoanApplicationServiceApplication.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class PublicKeyLoader {

    @Value("${auth.public-key-url}")
    private String publicKeyUrl;

    @Bean
    public RSAPublicKey authPublicKey() throws Exception {
        RestTemplate rest = new RestTemplate();
        String pem = rest.getForObject(publicKeyUrl, String.class);
        if (pem == null) throw new IllegalStateException("Public key not found at " + publicKeyUrl);
        String base64 = pem.replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");
        byte[] decoded = Base64.getDecoder().decode(base64);
        return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
    }
}