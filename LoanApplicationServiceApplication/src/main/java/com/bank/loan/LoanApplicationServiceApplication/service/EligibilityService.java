package com.bank.loan.LoanApplicationServiceApplication.service;


import org.springframework.stereotype.Service;

import com.bank.loan.LoanApplicationServiceApplication.dto.CustomerProfile;
import com.bank.loan.LoanApplicationServiceApplication.dto.EligibilityResponse;
import com.bank.loan.LoanApplicationServiceApplication.dto.LoanRequest;

@Service
public class EligibilityService {
    public EligibilityResponse checkEligibility(CustomerProfile c, LoanRequest req) {
        EligibilityResponse resp = new EligibilityResponse();
        if (c.getCreditScore() < 650) {
            resp.setEligible(false);
            resp.setReason("Low credit score");
            return resp;
        }
        if (c.getMonthlyIncome() < req.getLoanAmount() / 20) {
            resp.setEligible(false);
            resp.setReason("Insufficient income");
            return resp;
        }
        resp.setEligible(true);
        resp.setReason("Eligible");
        resp.setMaxEligibleAmount(c.getMonthlyIncome() * 20);
        return resp;
    }
}