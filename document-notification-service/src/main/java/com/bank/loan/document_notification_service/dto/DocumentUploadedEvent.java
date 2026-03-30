package com.bank.loan.document_notification_service.dto;


import java.time.LocalDateTime;

public class DocumentUploadedEvent {
    private Long documentId;
    private String filename;
    private String path;
    private LocalDateTime uploadedAt;

    public DocumentUploadedEvent() {}

    public DocumentUploadedEvent(Long documentId, String filename, String path, LocalDateTime uploadedAt) {
        this.documentId = documentId;
        this.filename = filename;
        this.path = path;
        this.uploadedAt = uploadedAt;
    }

    // getters/setters
    public Long getDocumentId() {
    	return documentId; 
    	}
    public void setDocumentId(Long documentId) { 
    	this.documentId = documentId; 
    	}
    public String getFilename() {
    	return filename; 
    	}
    public void setFilename(String filename) {
    	this.filename = filename; 
    	}
    public String getPath() {
    	return path; 
    	}
    public void setPath(String path) {
    	this.path = path; 
    	}
    public LocalDateTime getUploadedAt() {
    	return uploadedAt; 
    	}
    public void setUploadedAt(LocalDateTime uploadedAt) {
    	this.uploadedAt = uploadedAt;
    	}
}
