package com.bank.loan.LoanApplicationServiceApplication.dto;


import jakarta.validation.constraints.NotNull;

public class ApprovalRequest {
    @NotNull private Long loanId;
    @NotNull private Boolean approved;
	public Long getLoanId() {
		return loanId;
	}
	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}
	public Boolean getApproved() {
		return approved;
	}
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
	@Override
	public String toString() {
		return "ApprovalRequest [loanId=" + loanId + ", approved=" + approved + "]";
	}
    
}
