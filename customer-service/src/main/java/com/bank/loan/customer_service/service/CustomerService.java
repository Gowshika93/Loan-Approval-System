package com.bank.loan.customer_service.service;



import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bank.loan.customer_service.entity.Customer;
import com.bank.loan.customer_service.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public Customer register(Customer customer) {
        return repo.save(customer);
    }

    public Optional<Customer> getById(Long id) {
        return repo.findById(id);
    }
}

