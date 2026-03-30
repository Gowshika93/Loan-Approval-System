package com.bank.loan.LoanApplicationServiceApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.loan.LoanApplicationServiceApplication.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> { }
