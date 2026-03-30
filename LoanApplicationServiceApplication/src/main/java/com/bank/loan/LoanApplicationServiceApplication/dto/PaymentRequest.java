package com.bank.loan.LoanApplicationServiceApplication.dto;


import jakarta.validation.constraints.NotNull;

public class PaymentRequest {
    @NotNull 
    private Long loanId;
    @NotNull 
    private Double amount;
	public Long getLoanId() {
		return loanId;
	}
	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "PaymentRequest [loanId=" + loanId + ", amount=" + amount + "]";
	}
    
    
    
}
