package com.bank.loan.document_notification_service.dto;

public class NotificationRequest {
    private String to;      // email or phone depending on type
    private String type;    // EMAIL or SMS
    private String message;

    public NotificationRequest() {}
    public NotificationRequest(String to, String type, String message) {
        this.to = to; this.type = type; this.message = message;
    }
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "NotificationRequest [to=" + to + ", type=" + type + ", message=" + message + "]";
	}

   
}
