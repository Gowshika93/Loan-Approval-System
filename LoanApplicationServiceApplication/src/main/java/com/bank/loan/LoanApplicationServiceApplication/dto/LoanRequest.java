package com.bank.loan.LoanApplicationServiceApplication.dto;


import jakarta.validation.constraints.*;

public class LoanRequest {
    @NotNull private Long customerId;
    @NotBlank private String loanType;
    @Positive private double loanAmount;
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
	@Override
	public String toString() {
		return "LoanRequest [customerId=" + customerId + ", loanType=" + loanType + ", loanAmount=" + loanAmount + "]";
	}
    
    

}
