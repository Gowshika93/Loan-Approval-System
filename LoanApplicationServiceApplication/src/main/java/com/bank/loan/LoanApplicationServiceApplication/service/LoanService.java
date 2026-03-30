package com.bank.loan.LoanApplicationServiceApplication.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.bank.loan.LoanApplicationServiceApplication.dto.ApprovalRequest;
import com.bank.loan.LoanApplicationServiceApplication.dto.CustomerProfile;
import com.bank.loan.LoanApplicationServiceApplication.dto.EligibilityResponse;
import com.bank.loan.LoanApplicationServiceApplication.dto.LoanRequest;
import com.bank.loan.LoanApplicationServiceApplication.dto.NotificationRequest;
import com.bank.loan.LoanApplicationServiceApplication.dto.PaymentRequest;
import com.bank.loan.LoanApplicationServiceApplication.entity.Loan;
import com.bank.loan.LoanApplicationServiceApplication.feign.CustomerClient;
import com.bank.loan.LoanApplicationServiceApplication.feign.NotificationClient;
import com.bank.loan.LoanApplicationServiceApplication.repository.LoanRepository;

import jakarta.transaction.Transactional;

@Service
public class LoanService {

    private static final Logger log = LoggerFactory.getLogger(LoanService.class);

    private final LoanRepository repo;
    private final CustomerClient customerClient;
    private final NotificationClient notificationClient;
    private final EligibilityService eligibilityService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topics.loan-approved}")
    private String loanApprovedTopic;

    @Value("${kafka.topics.loan-payment}")
    private String loanPaymentTopic;

    public LoanService(LoanRepository repo, CustomerClient customerClient,
            NotificationClient notificationClient,
            EligibilityService eligibilityService,
            KafkaTemplate<String, Object> kafkaTemplate) {
        this.repo = repo;
        this.customerClient = customerClient;
        this.notificationClient = notificationClient;
        this.eligibilityService = eligibilityService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    public Loan apply(LoanRequest req) {
        CustomerProfile profile = customerClient.getCustomer(req.getCustomerId());
        EligibilityResponse eligibility = eligibilityService.checkEligibility(profile, req);
        if (!eligibility.isEligible())
            throw new IllegalArgumentException("Not eligible: " + eligibility.getReason());

        Loan loan = new Loan();
        loan.setCustomerId(req.getCustomerId());
        loan.setLoanType(req.getLoanType());
        loan.setLoanAmount(req.getLoanAmount());
        loan.setStatus("APPLIED");
        loan.setAppliedAt(LocalDateTime.now());
        repo.save(loan);

        NotificationRequest notif = new NotificationRequest();
        notif.setTo(profile.getId() + "@gmail.com");
        notif.setType("EMAIL");
        notif.setMessage("Loan application received for " + req.getLoanType());
        try {
            notificationClient.send(notif);
        } catch (Exception ex) {
            log.warn("Notification call failed (non-fatal): {}", ex.getMessage());
        }

        return loan;
    }

    public Loan getStatus(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));
    }

    @Transactional
    public Loan approve(ApprovalRequest req) {
        Loan loan = repo.findById(req.getLoanId())
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));

        if (req.getApproved()) {
            loan.setStatus("APPROVED");
            loan.setApprovedAt(LocalDateTime.now());
            repo.save(loan);
            try {
                kafkaTemplate.send(loanApprovedTopic, loan);
                log.info("Published loan.approved event for loanId={}", loan.getId());
            } catch (Exception ex) {
                log.warn("Kafka publish failed for loan.approved (non-fatal): {}", ex.getMessage());
            }
        } else {
            loan.setStatus("REJECTED");
            repo.save(loan);
        }
        return loan;
    }

    @Transactional
    public Loan disburse(PaymentRequest req) {
        Loan loan = repo.findById(req.getLoanId())
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));

        if (!"APPROVED".equals(loan.getStatus()))
            throw new IllegalArgumentException("Loan must be approved before disbursement");

        loan.setStatus("DISBURSED");
        loan.setDisbursedAt(LocalDateTime.now());
        repo.save(loan);

        try {
            kafkaTemplate.send(loanPaymentTopic, loan);
            log.info("Published loan.payment event for loanId={}", loan.getId());
        } catch (Exception ex) {
            log.warn("Kafka publish failed for loan.payment (non-fatal): {}", ex.getMessage());
        }

        return loan;
    }
}
