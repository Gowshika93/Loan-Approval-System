package com.bank.loan.auth_service.controller;


import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.loan.auth_service.dto.LoginRequest;
import com.bank.loan.auth_service.dto.LoginResponse;
import com.bank.loan.auth_service.dto.RegisterRequest;
import com.bank.loan.auth_service.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest req) {
        return ResponseEntity.ok(service.register(req));
    }
    
    

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        return ResponseEntity.ok(new LoginResponse(service.login(req)));
    }

    // Public key endpoint for Gateway and other services
    @GetMapping("/public-key")
    public ResponseEntity<String> getPublicKey() throws Exception {
        String key = Files.readString(Path.of("src/main/resources/public.pem"));
        return ResponseEntity.ok(key);
    }
}
