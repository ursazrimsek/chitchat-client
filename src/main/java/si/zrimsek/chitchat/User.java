package si.zrimsek.chitchat;

import java.util.Date;

public class User {
	private String username;
	private Date lastActive;
	
	public User(String username, Date lastActive) {
		this.username = username;
		this.lastActive = lastActive;
	}

}
