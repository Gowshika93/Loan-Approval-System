package com.bank.loan.document_notification_service.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.bank.loan.document_notification_service.dto.DocumentVerifiedEvent;
import com.bank.loan.document_notification_service.dto.NotificationRequest;
import com.bank.loan.document_notification_service.service.NotificationService;

@Service
public class NotificationEventListener {

    private static final Logger logger = LoggerFactory.getLogger(NotificationEventListener.class);
    private final NotificationService notificationService;

    public NotificationEventListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "${kafka.topics.document-verified}", groupId = "notification-group")
    public void handleDocumentVerified(DocumentVerifiedEvent event) {
        logger.info("Received document.verified for id={}, verified={}", event.getDocumentId(), event.isVerified());
        String msg = event.isVerified()
                ? "Your document (id=" + event.getDocumentId() + ") has been verified."
                : "Your document (id=" + event.getDocumentId() + ") was rejected.";

        // For the demo, we assume an email address derived from documentId (in real system link to user)
        NotificationRequest nr = new NotificationRequest("user+" + event.getDocumentId() + "@example.com", "EMAIL", msg);
        notificationService.send(nr);
    }

    // Also listen to generic notification events if produced elsewhere
    @KafkaListener(topics = "${kafka.topics.notification-events}", groupId = "notification-group")
    public void handleNotificationEvent(NotificationRequest req) {
        logger.info("Received notification event: to={}, type={}", req.getTo(), req.getType());
        notificationService.send(req);
    }
}