package si.zrimsek.chitchat;

import java.util.Date;

public class Message {
	private Boolean global;
	private String recipient;
	private String sender;
	private String text;
	private Date sent_at;
	

	public Message() {}
	
	public Message(String sender, String text) {
		this.global = true;
		this.sender = sender;
		this.text = text;
	}
	
	public Message(String recipient, String sender, String text) {
		this.global = false;
		this.recipient = recipient;
		this.sender = sender;
		this.text = text;
	}
	
	public Message(Boolean global, String recipient, String sender, String text) {
		this.global = global;
		this.recipient = recipient;
		this.sender = sender;
		this.text = text;
	}
	
	public Message(String sender, String text, Date sentAt) {
		this.global = true;
		this.sender = sender;
		this.text = text;
		this.sent_at = sentAt;
	}
	
	public Message(String recipient, String sender, String text, Date sentAt) {
		this.global = false;
		this.recipient = recipient;
		this.sender = sender;
		this.text = text;
		this.sent_at = sentAt;
	}
	
	public Message(Boolean global, String recipient, String sender, String text, Date sentAt) {
		this.global = global;
		this.recipient = recipient;
		this.sender = sender;
		this.text = text;
		this.sent_at = sentAt;
	}

	public Boolean getGlobal() {
		return global;
	}

	public String getRecipient() {
		return recipient;
	}

	public String getSender() {
		return sender;
	}

	public String getText() {
		return text;
	}

	public Date getSent_at() {
		return sent_at;
	}

	
	
	
}
