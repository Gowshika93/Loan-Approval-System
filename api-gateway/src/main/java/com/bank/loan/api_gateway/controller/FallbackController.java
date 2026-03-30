package com.bank.loan.api_gateway.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/loans")
    public ResponseEntity<?> loanFallback() {
        return ResponseEntity.ok("Loan Service is temporarily unavailable. Please try again later.");
    }

    @GetMapping("/customers")
    public ResponseEntity<?> customerFallback() {
        return ResponseEntity.ok("Customer Service is currently down. Please try again later.");
    }

    @GetMapping("/notifications")
    public ResponseEntity<?> notificationFallback() {
        return ResponseEntity.ok("Notification Service is not reachable right now.");
    }
}
