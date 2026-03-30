package com.bank.loan.document_notification_service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.bank.loan.document_notification_service.dto.DocumentUploadedEvent;
import com.bank.loan.document_notification_service.service.VerifierService;

@Service
public class DocumentEventListener {

    private static final Logger logger = LoggerFactory.getLogger(DocumentEventListener.class);
    private final VerifierService verifier;

    public DocumentEventListener(VerifierService verifier) {
        this.verifier = verifier;
    }

    @KafkaListener(topics = "${kafka.topics.document-uploaded}", groupId = "verifier-group")
    public void handleDocumentUploaded(DocumentUploadedEvent event) {
        logger.info("Received document.uploaded event for id={}", event.getDocumentId());
        try {
            verifier.verifyDocument(event.getDocumentId());
        } catch (Exception ex) {
            logger.error("Verifier failed for id={}: {}", event.getDocumentId(), ex.getMessage(), ex);
        }
    }
}
