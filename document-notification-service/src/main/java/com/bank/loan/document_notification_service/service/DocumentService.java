package com.bank.loan.document_notification_service.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bank.loan.document_notification_service.dto.DocumentUploadedEvent;
import com.bank.loan.document_notification_service.entity.DocumentEntity;
import com.bank.loan.document_notification_service.repository.DocumentRepository;

@Service
public class DocumentService {

    private static final Logger logger = LoggerFactory.getLogger(DocumentService.class);

    private final DocumentRepository repo;
    private final StorageService storage;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topics.document-uploaded}")
    private String docUploadedTopic;

    public DocumentService(DocumentRepository repo, StorageService storage,
            KafkaTemplate<String, Object> kafkaTemplate) {
        this.repo = repo;
        this.storage = storage;
        this.kafkaTemplate = kafkaTemplate;
    }

    public DocumentEntity upload(MultipartFile file) throws Exception {
        DocumentEntity d = new DocumentEntity();
        d.setFilename(file.getOriginalFilename());
        d.setStatus("UPLOADED");
        d.setUploadedAt(LocalDateTime.now());
        d = repo.save(d);

        String path = storage.store(file, d.getId());
        d.setPath(path);
        repo.save(d);

        try {
            DocumentUploadedEvent event = new DocumentUploadedEvent(
                    d.getId(), d.getFilename(), d.getPath(), d.getUploadedAt());
            kafkaTemplate.send(docUploadedTopic, String.valueOf(d.getId()), event);
            logger.info("Published document.uploaded event for id={}", d.getId());
        } catch (Exception ex) {
            logger.warn("Kafka publish failed for document.uploaded (non-fatal): {}", ex.getMessage());
        }

        return d;
    }

    public void triggerVerify(Long documentId) {
        DocumentEntity d = repo.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Doc not found"));
        try {
            DocumentUploadedEvent event = new DocumentUploadedEvent(
                    d.getId(), d.getFilename(), d.getPath(), d.getUploadedAt());
            kafkaTemplate.send(docUploadedTopic, String.valueOf(d.getId()), event);
        } catch (Exception ex) {
            logger.warn("Kafka publish failed for triggerVerify (non-fatal): {}", ex.getMessage());
        }
    }

    public void markVerified(Long documentId, boolean ok, String ocrText) {
        DocumentEntity d = repo.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Doc not found"));
        d.setStatus(ok ? "VERIFIED" : "REJECTED");
        d.setOcrText(ocrText);
        repo.save(d);
    }
}
