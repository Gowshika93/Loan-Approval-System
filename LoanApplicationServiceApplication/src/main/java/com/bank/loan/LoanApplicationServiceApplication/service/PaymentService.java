package com.bank.loan.LoanApplicationServiceApplication.service;


import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.bank.loan.LoanApplicationServiceApplication.dto.PaymentRequest;
import com.bank.loan.LoanApplicationServiceApplication.entity.Loan;
import com.bank.loan.LoanApplicationServiceApplication.repository.LoanRepository;

@Service
public class PaymentService {

    private final LoanRepository repo;

    public PaymentService(LoanRepository repo) {
        this.repo = repo;
    }

    public Loan disburse(PaymentRequest req) {
        Loan loan = repo.findById(req.getLoanId())
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        if (!"APPROVED".equals(loan.getStatus()))
            throw new RuntimeException("Loan must be approved before disbursement");

        loan.setStatus("DISBURSED");
        loan.setDisbursedAt(LocalDateTime.now());
        return repo.save(loan);
    }
}
