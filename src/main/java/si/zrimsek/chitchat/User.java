package si.zrimsek.chitchat;

import java.util.Date;

public class User {
	private String username;
	private Date last_active;
	
	public User(String username, Date lastActive) {
		this.username = username;
		this.last_active = lastActive;
	}

	public String getUsername() {
		return username;
	}

	public Date getLastActive() {
		return last_active;
	}

}
