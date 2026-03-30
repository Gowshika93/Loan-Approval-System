package com.bank.loan.auth_service.config;


import org.springframework.context.annotation.*;
import java.nio.file.*;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;

@Configuration
public class RSAKeyConfig {

    @Bean
    public KeyPair keyPair() throws Exception {
        String privatePem = Files.readString(Path.of("src/main/resources/private.pem"));
        String publicPem = Files.readString(Path.of("src/main/resources/public.pem"));

        String privateKeyPEM = privatePem.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "").replaceAll("\\s+", "");
        String publicKeyPEM = publicPem.replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "").replaceAll("\\s+", "");

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] privateBytes = Base64.getDecoder().decode(privateKeyPEM);
        byte[] publicBytes = Base64.getDecoder().decode(publicKeyPEM);

        PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateBytes));
        PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicBytes));

        return new KeyPair(publicKey, privateKey);
    }
}
