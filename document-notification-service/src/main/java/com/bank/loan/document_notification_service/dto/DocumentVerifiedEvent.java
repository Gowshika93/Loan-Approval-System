package com.bank.loan.document_notification_service.dto;


public class DocumentVerifiedEvent {
    private Long documentId;
    private boolean verified;
    private String ocrText;

    public DocumentVerifiedEvent() {}

    public DocumentVerifiedEvent(Long documentId, boolean verified, String ocrText) {
        this.documentId = documentId;
        this.verified = verified;
        this.ocrText = ocrText;
    }

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public String getOcrText() {
		return ocrText;
	}

	public void setOcrText(String ocrText) {
		this.ocrText = ocrText;
	}

	@Override
	public String toString() {
		return "DocumentVerifiedEvent [documentId=" + documentId + ", verified=" + verified + ", ocrText=" + ocrText
				+ "]";
	}

  
}
