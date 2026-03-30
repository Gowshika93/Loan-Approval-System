package com.bank.loan.document_notification_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.bank.loan.document_notification_service.dto.DocumentVerifiedEvent;
import com.bank.loan.document_notification_service.entity.DocumentEntity;
import com.bank.loan.document_notification_service.repository.DocumentRepository;

@Service
public class VerifierService {

    private static final Logger logger = LoggerFactory.getLogger(VerifierService.class);

    private final DocumentRepository repo;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topics.document-verified}")
    private String docVerifiedTopic;

    public VerifierService(DocumentRepository repo, KafkaTemplate<String, Object> kafkaTemplate) {
        this.repo = repo;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void verifyDocument(Long documentId) {
        DocumentEntity d = repo.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Doc not found"));

        String ocrText = "Mock OCR text for document " + d.getFilename();
        boolean verified = true;

        d.setOcrText(ocrText);
        d.setStatus(verified ? "VERIFIED" : "REJECTED");
        repo.save(d);

        try {
            DocumentVerifiedEvent evt = new DocumentVerifiedEvent(d.getId(), verified, ocrText);
            kafkaTemplate.send(docVerifiedTopic, String.valueOf(d.getId()), evt);
            logger.info("Published document.verified event for id={}, verified={}", d.getId(), verified);
        } catch (Exception ex) {
            logger.warn("Kafka publish failed for document.verified (non-fatal): {}", ex.getMessage());
        }
    }
}
