package com.bank.loan.auth_service.dto;

import com.bank.loan.auth_service.entity.Role;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
}
