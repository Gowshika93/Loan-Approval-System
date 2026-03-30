package com.bank.loan.customer_service.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.loan.customer_service.dto.CreditScoreResponse;
import com.bank.loan.customer_service.entity.Customer;
import com.bank.loan.customer_service.service.CreditScoreService;
import com.bank.loan.customer_service.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer Management", description = "APIs for managing customer details and credit scores")
public class CustomerController {

    private final CustomerService customerService;
    private final CreditScoreService creditScoreService;

    public CustomerController(CustomerService customerService, CreditScoreService creditScoreService) {
        this.customerService = customerService;
        this.creditScoreService = creditScoreService;
    }

    @Operation(summary = "Register a new customer", description = "Registers a new customer with KYC information")
    @PostMapping("/register")
    public ResponseEntity<Customer> register(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.register(customer));
    }

    @Operation(summary = "Get customer by ID", description = "Fetch details of a customer by ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getById(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get credit score", description = "Fetches credit score for the given customer ID")
    @GetMapping("/{id}/creditscore")
    public ResponseEntity<CreditScoreResponse> getCreditScore(@PathVariable Long id) {
        return ResponseEntity.ok(creditScoreService.fetchCreditScore(id));
    }
}