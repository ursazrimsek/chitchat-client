package si.zrimsek.chitchat;

import java.util.Date;

public class User {
	private final String username;
	private final Date lastActive;
	
	public User(String username, Date lastActive) {
		this.username = username;
		this.lastActive = lastActive;
	}

}
