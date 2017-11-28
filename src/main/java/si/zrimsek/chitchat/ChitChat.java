package si.zrimsek.chitchat;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class ChitChat {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		ChatFrame chatFrame = new ChatFrame();
		PrimeRobot robot = new PrimeRobot(chatFrame);
		robot.activate();
		chatFrame.pack();
		chatFrame.setVisible(true);
		chatFrame.getSignedInUsers();

		
			
	}

}
