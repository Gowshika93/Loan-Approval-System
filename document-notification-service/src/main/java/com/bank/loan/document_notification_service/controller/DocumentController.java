package com.bank.loan.document_notification_service.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bank.loan.document_notification_service.dto.NotificationRequest;
import com.bank.loan.document_notification_service.entity.DocumentEntity;
import com.bank.loan.document_notification_service.service.DocumentService;
import com.bank.loan.document_notification_service.service.NotificationService;

@RestController
@RequestMapping("/api")
public class DocumentController {

    private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);

    private final DocumentService documentService;
    private final NotificationService notificationService;

    public DocumentController(DocumentService documentService, NotificationService notificationService) {
        this.documentService = documentService;
        this.notificationService = notificationService;
    }

    @PostMapping("/documents/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        try {
            DocumentEntity d = documentService.upload(file);
            return ResponseEntity.ok(d);
        } catch (Exception e) {
            logger.error("Upload failed", e);
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }

    @PostMapping("/documents/verify")
    public ResponseEntity<?> manualVerify(@RequestBody Map<String, Long> body) {
        Long id = body.get("documentId");
        if (id == null) return ResponseEntity.badRequest().body("documentId required");
        documentService.triggerVerify(id);
        return ResponseEntity.ok(Map.of("status", "verification-triggered", "documentId", id));
    }

    @PostMapping("/notifications/send")
    public ResponseEntity<?> sendNotification(@RequestBody NotificationRequest req) {
        notificationService.send(req);
        return ResponseEntity.ok(Map.of("status", "sent"));
    }
}
