package com.bank.loan.LoanApplicationServiceApplication.dto;


public class CustomerProfile {
    private Long id;
    private String fullName;
    private int age;
    private double monthlyIncome;
    private int creditScore;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getMonthlyIncome() {
		return monthlyIncome;
	}
	public void setMonthlyIncome(double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	public int getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}
	@Override
	public String toString() {
		return "CustomerProfile [id=" + id + ", fullName=" + fullName + ", age=" + age + ", monthlyIncome="
				+ monthlyIncome + ", creditScore=" + creditScore + "]";
	}
    
    
}
