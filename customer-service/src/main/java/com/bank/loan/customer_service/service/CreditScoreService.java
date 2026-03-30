package com.bank.loan.customer_service.service;


import java.util.Random;

import org.springframework.stereotype.Service;

import com.bank.loan.customer_service.dto.CreditScoreResponse;
import com.bank.loan.customer_service.entity.Customer;
import com.bank.loan.customer_service.repository.CustomerRepository;

@Service
public class CreditScoreService {

    private final CustomerRepository repo;

    public CreditScoreService(CustomerRepository repo) {
        this.repo = repo;
    }

    public CreditScoreResponse fetchCreditScore(Long customerId) {
        Customer customer = repo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        int score = new Random().nextInt(300) + 550; // 550-850 mock
        customer.setCreditScore(score);
        repo.save(customer);
        return new CreditScoreResponse(score, "MockCIBIL", "SUCCESS");
    }
}
