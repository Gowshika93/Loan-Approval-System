package com.bank.loan.auth_service.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bank.loan.auth_service.config.JwtUtil;
import com.bank.loan.auth_service.dto.LoginRequest;
import com.bank.loan.auth_service.dto.RegisterRequest;
import com.bank.loan.auth_service.entity.Role;
import com.bank.loan.auth_service.entity.User;
import com.bank.loan.auth_service.repository.UserRepository;

@Service
public class AuthService {
    private final UserRepository repo;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    public AuthService(UserRepository repo, JwtUtil jwtUtil) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
    }

    public String register(RegisterRequest request) {
    	log.info("User trying to register {}", request.getUsername());
        if (repo.findByUsername(request.getUsername()).isPresent()) {
        
        	log.error("User registration failed: Username already exists {}", request.getUsername());
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        repo.save(user);
        
        log.info("User registered {}", request.getUsername());
        return "User registered successfully!";
    }

    public String login(LoginRequest request) {
    	log.info("User trying to login {}", request.getUsername());
        User user = repo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
        	log.error("login attempt failed: Invalid username or password ");
            throw new RuntimeException("Invalid username or password");
        }
        
        log.info("User logged in successfully {}", request.getUsername());
        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }
}
