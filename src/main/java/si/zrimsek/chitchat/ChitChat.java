package si.zrimsek.chitchat;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;

public class ChitChat {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		ChatFrame chatFrame = new ChatFrame();
		CheckRobot check = new CheckRobot(chatFrame);
		check.activate();
		chatFrame.pack();
		chatFrame.setVisible(true);
		chatFrame.getSignedInUsers();
		try {
			App.logIn(System.getProperty("user.name"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
			
	}
	


}
