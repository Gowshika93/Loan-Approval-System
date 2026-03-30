package com.bank.loan.document_notification_service.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.bank.loan.document_notification_service.dto.NotificationRequest;
import com.bank.loan.document_notification_service.service.NotificationService;

@Service
public class LoanEventListener {

    private static final Logger logger = LoggerFactory.getLogger(LoanEventListener.class);
    private final NotificationService notificationService;

    public LoanEventListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Listen to "loan.approved.topic"
    @KafkaListener(topics = "${kafka.topics.loan-approved}", groupId = "notification-group")
    public void handleLoanApproved(Object event) {
        logger.info("Received loan.approved event: {}", event);

        NotificationRequest request = new NotificationRequest();
        request.setType("EMAIL");
        request.setTo("customer@example.com"); // Replace with dynamic lookup if available
        request.setMessage("Your loan has been approved! Please check your account for details.");

        notificationService.send(request);
    }

    // Listen to "loan.payment.topic"
    @KafkaListener(topics = "${kafka.topics.loan-payment}", groupId = "notification-group")
    public void handleLoanPayment(Object event) {
        logger.info("Received loan.payment event: {}", event);

        NotificationRequest request = new NotificationRequest();
        request.setType("EMAIL");
        request.setTo("customer@example.com"); // Replace with dynamic lookup if available
        request.setMessage("Your loan amount has been disbursed successfully!");

        notificationService.send(request);
    }
}
