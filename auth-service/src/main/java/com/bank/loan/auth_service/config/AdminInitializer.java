package com.bank.loan.auth_service.config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bank.loan.auth_service.entity.Role;
import com.bank.loan.auth_service.entity.User;
import com.bank.loan.auth_service.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminInitializer {

    @Bean
    CommandLineRunner createAdmin(UserRepository repo, PasswordEncoder encoder) {

        return args -> {

            if(repo.findByUsername("admin@bank.com").isEmpty()) {

                User admin = new User();

                admin.setUsername("admin@bank.com");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole(Role.ADMIN);

                repo.save(admin);
            }

        };
    }
}
