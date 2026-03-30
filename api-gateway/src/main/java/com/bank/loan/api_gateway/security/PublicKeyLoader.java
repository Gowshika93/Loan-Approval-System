package com.bank.loan.api_gateway.security;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PublicKeyLoader {
    @Value("${auth.public-key-url}")
    private String publicKeyUrl;

    @Bean
    public RSAPublicKey publicKey() throws Exception {
        String pem = new RestTemplate().getForObject(publicKeyUrl, String.class);
        String base64 = pem.replace("-----BEGIN PUBLIC KEY-----", "")
                           .replace("-----END PUBLIC KEY-----", "")
                           .replaceAll("\\s+", "");
        byte[] decoded = Base64.getDecoder().decode(base64);
        return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
    }
}
