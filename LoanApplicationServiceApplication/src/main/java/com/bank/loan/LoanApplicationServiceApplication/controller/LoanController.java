package com.bank.loan.LoanApplicationServiceApplication.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.loan.LoanApplicationServiceApplication.dto.ApprovalRequest;
import com.bank.loan.LoanApplicationServiceApplication.dto.LoanRequest;
import com.bank.loan.LoanApplicationServiceApplication.dto.PaymentRequest;
import com.bank.loan.LoanApplicationServiceApplication.entity.Loan;
import com.bank.loan.LoanApplicationServiceApplication.service.LoanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService service;
    public LoanController(LoanService service){ this.service = service; }

    @PostMapping("/apply")
    public ResponseEntity<Loan> apply(@Valid @RequestBody LoanRequest req) {
        return ResponseEntity.ok(service.apply(req));
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<Loan> status(@PathVariable Long id) {
        return ResponseEntity.ok(service.getStatus(id));
    }

    @PostMapping("/approval/submit")
    public ResponseEntity<Loan> approve(@RequestBody ApprovalRequest req) {
        return ResponseEntity.ok(service.approve(req));
    }

    @PostMapping("/payment/disburse")
    public ResponseEntity<Loan> disburse(@RequestBody PaymentRequest req) {
        return ResponseEntity.ok(service.disburse(req));
    }
}