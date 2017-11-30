package si.zrimsek.chitchat;

import java.awt.Dimension;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;

public class ChitChat {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		ChatFrame chatFrame = new ChatFrame();
		CheckRobot check = new CheckRobot(chatFrame);
		check.activate();
		chatFrame.pack();
		chatFrame.setVisible(true);
		chatFrame.setMinimumSize(new Dimension(275, 200));
		chatFrame.getSignedInUsers();
		
			
	}
	


}
