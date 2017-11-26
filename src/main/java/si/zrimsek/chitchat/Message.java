package si.zrimsek.chitchat;

import java.util.Date;

public class Message {
	private Boolean global;
	private String recipient;
	private String sender;
	private String text;
	private Date sentAt;
	
	public Message(Boolean global, String recipient, String sender, String text, Date sentAt) {
		this.global = global;
		this.recipient = recipient;
		this.sender = sender;
		this.text = text;
		this.sentAt = sentAt;
	}
	
	
	
}
