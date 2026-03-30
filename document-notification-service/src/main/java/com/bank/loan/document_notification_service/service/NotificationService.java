package com.bank.loan.document_notification_service.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bank.loan.document_notification_service.dto.NotificationRequest;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    // In production, inject JavaMailSender and Twilio client; here we mock.
    public void send(NotificationRequest req) {
        if ("EMAIL".equalsIgnoreCase(req.getType())) {
            // mock sending email
            logger.info("Sending EMAIL to {}: {}", req.getTo(), req.getMessage());
        } else if ("SMS".equalsIgnoreCase(req.getType())) {
            logger.info("Sending SMS to {}: {}", req.getTo(), req.getMessage());
        } else {
            logger.info("Unknown notification type {}, message: {}", req.getType(), req.getMessage());
        }
    }
}
