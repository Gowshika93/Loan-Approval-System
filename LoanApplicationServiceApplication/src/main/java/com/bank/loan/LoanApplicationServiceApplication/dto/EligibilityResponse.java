package com.bank.loan.LoanApplicationServiceApplication.dto;


public class EligibilityResponse {
    private boolean eligible;
    private String reason;
    private double maxEligibleAmount;
	public boolean isEligible() {
		return eligible;
	}
	public void setEligible(boolean eligible) {
		this.eligible = eligible;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public double getMaxEligibleAmount() {
		return maxEligibleAmount;
	}
	public void setMaxEligibleAmount(double maxEligibleAmount) {
		this.maxEligibleAmount = maxEligibleAmount;
	}
	@Override
	public String toString() {
		return "EligibilityResponse [eligible=" + eligible + ", reason=" + reason + ", maxEligibleAmount="
				+ maxEligibleAmount + "]";
	}

}
