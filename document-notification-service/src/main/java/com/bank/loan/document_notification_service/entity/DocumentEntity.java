package com.bank.loan.document_notification_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String path;
    private String status; // UPLOADED, VERIFIED, REJECTED
    private String ocrText;
    private LocalDateTime uploadedAt;

    public DocumentEntity() {}

    // getters & setters
    public Long getId() {
    	return id; 
    	}
    public void setId(Long id) {
    	this.id = id; 
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
    public String getStatus() {
    	return status; 
    	}
    public void setStatus(String status) { 
    	this.status = status; 
    	}
    public String getOcrText() {
    	return ocrText; 
    	}
    public void setOcrText(String ocrText) {
    	this.ocrText = ocrText; 
    	}
    public LocalDateTime getUploadedAt() {
    	return uploadedAt; 
    	}
    public void setUploadedAt(LocalDateTime uploadedAt) {
    	this.uploadedAt = uploadedAt; 
    	}
}
