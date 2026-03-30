package com.bank.loan.customer_service.dto;


public class CreditScoreResponse {
    private int score;
    private String provider;
    private String status;

    public CreditScoreResponse() {}
    public CreditScoreResponse(int score, String provider, String status) {
        this.score = score;
        this.provider = provider;
        this.status = status;
    }

    public int getScore() {
    	return score; 
    	}
    public void setScore(int score) {
    	this.score = score; 
    	}
    public String getProvider() {
    	return provider; 
    	}
    public void setProvider(String provider) {
    	this.provider = provider; 
    	}
    public String getStatus() {
    	return status; 
    	}
    public void setStatus(String status) {
    	this.status = status; 
    	}
}
