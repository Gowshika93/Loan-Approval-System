package com.bank.loan.customer_service.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String phoneNumber;
    private String panNumber;
    private int age;
    private double monthlyIncome;
    private int creditScore;

    // Getters & setters
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
    public String getEmail() {
    	return email; 
    	}
    public void setEmail(String email) {
    	this.email = email; 
    	}
    public String getPhoneNumber() {
    	return phoneNumber; 
    	}
    public void setPhoneNumber(String phoneNumber) {
    	this.phoneNumber = phoneNumber; 
    	}
    public String getPanNumber() {
    	return panNumber; 
    	}
    public void setPanNumber(String panNumber) {
    	this.panNumber = panNumber; 
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
}