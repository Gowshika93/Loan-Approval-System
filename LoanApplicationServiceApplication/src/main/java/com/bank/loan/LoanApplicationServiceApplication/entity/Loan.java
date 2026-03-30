package com.bank.loan.LoanApplicationServiceApplication.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private String loanType;
    private double loanAmount;
    private String status; // APPLIED, APPROVED, REJECTED, DISBURSED
    private double interestRate;
    private LocalDateTime appliedAt;
    private LocalDateTime approvedAt;
    private LocalDateTime disbursedAt;
    
 
	public Loan(Long id, Long customerId, String loanType, double loanAmount, String status, double interestRate,
			LocalDateTime appliedAt, LocalDateTime approvedAt, LocalDateTime disbursedAt) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.loanType = loanType;
		this.loanAmount = loanAmount;
		this.status = status;
		this.interestRate = interestRate;
		this.appliedAt = appliedAt;
		this.approvedAt = approvedAt;
		this.disbursedAt = disbursedAt;
	}
	public Loan() {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public LocalDateTime getAppliedAt() {
		return appliedAt;
	}
	public void setAppliedAt(LocalDateTime appliedAt) {
		this.appliedAt = appliedAt;
	}
	public LocalDateTime getApprovedAt() {
		return approvedAt;
	}
	public void setApprovedAt(LocalDateTime approvedAt) {
		this.approvedAt = approvedAt;
	}
	public LocalDateTime getDisbursedAt() {
		return disbursedAt;
	}
	public void setDisbursedAt(LocalDateTime disbursedAt) {
		this.disbursedAt = disbursedAt;
	}
	@Override
	public String toString() {
		return "Loan [id=" + id + ", customerId=" + customerId + ", loanType=" + loanType + ", loanAmount=" + loanAmount
				+ ", status=" + status + ", interestRate=" + interestRate + ", appliedAt=" + appliedAt + ", approvedAt="
				+ approvedAt + ", disbursedAt=" + disbursedAt + "]";
	}

    
    
}
