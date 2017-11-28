package si.zrimsek.chitchat;

import java.util.Date;

public class User {
	private String username;
	private Date last_active;
	
	public User() { }
	
	public User(String username, Date last_active) {
		this.username = username;
		this.last_active = last_active;
	}

	public String getUsername() {
		return username;
	}

	public Date getLast_active() {
		return last_active;
	}

}
