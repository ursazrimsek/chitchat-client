package si.zrimsek.chitchat;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.MenuSelectionManager;
import javax.swing.JMenuBar;
import javax.swing.JRadioButton;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.border.BevelBorder;

import org.apache.http.client.ClientProtocolException;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import java.awt.Component;
import javax.swing.JMenuItem;

public class ChatFrame extends JFrame implements ActionListener, KeyListener {
	
	private JTextArea output;
	private JTextField input;
	private String nickname;
	private String recipient;
	private String signedInUsers;
	private JTextArea txt_signedInUsers;
	private JTextField txt_recipient;
	private JRadioButton global;
	private JTextField txt_nickname;
	private JMenu mnWindow;
	private JButton btnSignIn;
	private JButton btnSignOut;
	private JButton btnDelete;
	private JButton btnTest;
	private JMenu mnFontColor;
	private JMenuItem green;
	private JMenuItem blue;
	private JMenu mnfontSize;
	private JMenuItem size12;
	private JMenuItem size10;
	private JMenu mnWindowColor;
	private JMenuItem wdGreen;
	private JMenuItem wdBlue;
	private JMenuItem wdWhite;
	private JMenuItem wdOrange;
	private JMenuItem black;
	

	public ChatFrame() {
		super();
		Container pane = this.getContentPane();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0};
		pane.setLayout(gridBagLayout);
	//	pane.setBackground(Color.BLACK);
		
		this.setTitle("ChitChat");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		
		this.nickname = System.getProperty("user.name");
		this.signedInUsers = new String();
		this.recipient = new String();
		
		// POGOVOR
		// Output
		this.output = new JTextArea(20, 40);
		this.output.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(output);
		GridBagConstraints outputConstraint = new GridBagConstraints();
		outputConstraint.gridwidth = 2;
		outputConstraint.gridheight = 3;
		outputConstraint.insets = new Insets(5, 5, 0, 5);
		outputConstraint.weighty = 1.0;
		outputConstraint.weightx = 3.0;
		outputConstraint.fill = GridBagConstraints.BOTH;
		outputConstraint.gridx = 0;
		outputConstraint.gridy = 0;
		pane.add(scrollPane, outputConstraint);
		
		// Input
		this.input = new JTextField(40);
		input.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		input.addActionListener(this);
		input.addKeyListener(this);
		GridBagConstraints inputConstraint = new GridBagConstraints();
		inputConstraint.insets = new Insets(5, 5, 5, 5);
		inputConstraint.gridwidth = 2;
		inputConstraint.fill = GridBagConstraints.HORIZONTAL;
		inputConstraint.gridx = 0;
		inputConstraint.gridy = 3;
		pane.add(input, inputConstraint);
		
		
		addWindowListener(new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		        input.requestFocus();
		    }
		});
		
		
		// Vpisani uporabniki
				// Napis
		JTextField txtSignedIn = new JTextField();
		txtSignedIn.setBorder(null);
		txtSignedIn.setHorizontalAlignment(SwingConstants.LEFT);
		txtSignedIn.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtSignedIn.setEditable(false);
		txtSignedIn.setText("Prijavljeni:");
		GridBagConstraints gbc_txtSignedIn = new GridBagConstraints();
		gbc_txtSignedIn.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSignedIn.insets = new Insets(5, 5, 5, 5);
		gbc_txtSignedIn.gridx = 2;
		gbc_txtSignedIn.gridy = 0;
		getContentPane().add(txtSignedIn, gbc_txtSignedIn);
		txtSignedIn.setColumns(10);
		
				// Seznam
		txt_signedInUsers = new JTextArea(0, 10);
		txt_signedInUsers.setToolTipText("Prijavljeni uporabniki");
		txt_signedInUsers.setForeground(new Color(0, 128, 0));
		txt_signedInUsers.setFont(new Font("Monospaced", Font.BOLD, 10));
		txt_signedInUsers.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txt_signedInUsers.setBackground(SystemColor.menu);
		txt_signedInUsers.setEditable(false);
		JScrollPane siuScroll = new JScrollPane(txt_signedInUsers);
		siuScroll.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY, null, null));
		GridBagConstraints usersConstraints = new GridBagConstraints();
		usersConstraints.gridheight = 2;
		usersConstraints.weightx = 1.0;
		usersConstraints.fill = GridBagConstraints.BOTH;
		usersConstraints.insets = new Insets(0, 0, 5, 5);
		usersConstraints.gridx = 2;
		usersConstraints.gridy = 1;
		pane.add(siuScroll, usersConstraints);
		
		btnTest = new JButton("TEST");
		btnTest.addActionListener(this);
		siuScroll.setRowHeaderView(btnTest);
		
		// Prejemnik
		txt_recipient = new JTextField("Prejemnik");
		txt_recipient.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txt_recipient.addActionListener(this);
		txt_recipient.addKeyListener(this);
		GridBagConstraints recipientConstraints = new GridBagConstraints();
		recipientConstraints.gridheight = 1;
		recipientConstraints.weightx = 1.0;
		recipientConstraints.fill = GridBagConstraints.HORIZONTAL;
		recipientConstraints.insets = new Insets(0, 0, 5, 5);
		recipientConstraints.gridx = 2;
		recipientConstraints.gridy = 3;
		txt_recipient.setHorizontalAlignment(SwingConstants.LEFT);
		txt_recipient.setToolTipText("Vnesite prejemnika");
		pane.add(txt_recipient, recipientConstraints);
		
		
		
		// MENIJI
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// Prijava/odjava		
		JMenu mnSignInOut = new JMenu("ChitChat");
		menuBar.add(mnSignInOut);
		
		btnSignIn = new JButton("Prijava");
		btnSignIn.addActionListener(this);
		
		
				// Vzdevek
		txt_nickname = new JTextField();
		txt_nickname.setBorder(new LineBorder(new Color(153, 51, 0)));
		mnSignInOut.add(txt_nickname);
		txt_nickname.addActionListener(this);
		txt_nickname.addKeyListener(this);
		txt_nickname.setHorizontalAlignment(SwingConstants.LEFT);
		txt_nickname.setToolTipText("Izberite si svoj vzdevek");
		txt_nickname.setText(this.nickname); // a je to treba?
		mnSignInOut.add(btnSignIn);
		
		btnSignOut = new JButton("Odjava");
		btnSignOut.addActionListener(this);
		mnSignInOut.add(btnSignOut);
		
		// Pisava
		JMenu mnFont = new JMenu("Pisava");
		menuBar.add(mnFont);
		
		mnFontColor = new JMenu("Barva");
		mnFont.add(mnFontColor);
		
		black = new JMenuItem("Črna");
		mnFontColor.add(black);
		
		green = new JMenuItem("Zelena");
		mnFontColor.add(green);
		
		blue = new JMenuItem("Modra");
		mnFontColor.add(blue);
		
		mnfontSize = new JMenu("Velikost");
		mnFont.add(mnfontSize);
		
		size10 = new JMenuItem("10");
		mnfontSize.add(size10);
		
		size12 = new JMenuItem("12");
		mnfontSize.add(size12);
		
		// Okno
		mnWindow = new JMenu("Okno");
		menuBar.add(mnWindow);
		
				// Pobriši
		btnDelete = new JButton("Zbriši pogovor");
		btnDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnDelete.addActionListener(this);
		
		mnWindowColor = new JMenu("Barva");
		mnWindow.add(mnWindowColor);
		
		wdWhite = new JMenuItem("Bela");
		mnWindowColor.add(wdWhite);
		
		wdGreen = new JMenuItem("Zelena");
		mnWindowColor.add(wdGreen);
		
		wdBlue = new JMenuItem("Modra");
		mnWindowColor.add(wdBlue);
		
		wdOrange = new JMenuItem("Oranžna");
		mnWindowColor.add(wdOrange);
		btnDelete.setToolTipText("Pobriše dosedanji pogovor");
		mnWindow.add(btnDelete);
		
		// Javno
		global = new JRadioButton("Javno");
		global.addActionListener(this);
		global.setHorizontalAlignment(SwingConstants.RIGHT);
		global.setSelected(true);
		menuBar.add(global);
		global.setToolTipText("Javno ali zasebno sporočilo?");
	}
	
	public String getSignedInUsers() throws ClientProtocolException, IOException {			// TODO vsako sekundo
		List<User> users = App.getUsers();
		this.signedInUsers = "";
		for (User user : users) {
			this.signedInUsers += user.getUsername() + "\n";
		}
		this.txt_signedInUsers.setText(this.signedInUsers);
		return signedInUsers;
	}
	
	private Boolean signedIn(String user) {
		if (this.signedInUsers.length() == 0) {
			return false;
		} else {
			return this.signedInUsers.contains(user);
		}
	}


	public void addSentMessage(String person, String message) throws ClientProtocolException, IOException, URISyntaxException {
		String chat = this.output.getText();
		this.output.setText(chat + person + ": " + message + "\n") ;
		App.sendMessage(global.isSelected(), person, this.recipient, message);
	}
	
	public void addRecievedMessage() throws ClientProtocolException, URISyntaxException, IOException {			// TODO vsako sekundo 
		if (signedIn(this.nickname)) {
			List<Message> newMessages = App.recieveMessages(this.nickname);
			if (! newMessages.isEmpty()) {
				String chat = this.output.getText();
				for (Message message : newMessages) {
					this.output.setText(chat + message.getSender() + ": " + message.getText() + "\n");
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == this.btnSignIn) {
			if (!signedIn(nickname));	
				try {
					App.logIn(nickname);
					getSignedInUsers(); // ko bo vsako sekundo loh dam tle vn?
				} catch (ClientProtocolException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		} if (source == this.btnSignOut) {
			if (signedIn(nickname));	
				try {
					App.logOut(nickname);
					getSignedInUsers(); // ko bo vsako sekundo loh dam tle vn?
				} catch (ClientProtocolException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		} if (source == this.btnDelete) {
			this.output.setText("");
		} if (source == this.global) {
			if (! this.global.isSelected()) {
				if (this.recipient.length() == 0) {
					this.txt_recipient.setText("");
					this.txt_recipient.requestFocus();
					this.input.setEditable(false);
					this.input.setText("Izberite prejemnika ali pošiljajte javno sporočilo!");
				} else {
					this.input.setEditable(true);
					this.input.setText("");
				}
			}
		} 
		
		if (source == this.btnTest) {
		}
			
		
	}

	public void keyTyped(KeyEvent e) {
		Object source = e.getSource();
		if (source == this.txt_nickname) {
			if (e.getKeyChar() == '\n') {
				this.nickname = this.txt_nickname.getText();
				MenuSelectionManager.defaultManager().clearSelectedPath();
				this.input.requestFocus();
			}
		} if (source == this.txt_recipient) {
			if (e.getKeyChar() == '\n') {
				this.recipient = this.txt_recipient.getText();
				this.global.setSelected(false);
				this.input.setEditable(true);
				this.input.requestFocus();
				this.input.setText("");
				}
		} if (source == this.input) {
			if (e.getKeyChar() == '\n') {
				try {
					this.addSentMessage(this.nickname, this.input.getText());
					this.input.setText("");
				} catch (ClientProtocolException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public void keyPressed(KeyEvent e) { }

	public void keyReleased(KeyEvent e) { }

	

	
//	private static void addPopup(Component component, final JPopupMenu popup) {
//		component.addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent e) {
//				if (e.isPopupTrigger()) {
//					showMenu(e);
//				}
//			}
//			public void mouseReleased(MouseEvent e) {
//				if (e.isPopupTrigger()) {
//					showMenu(e);
//				}
//			}
//			private void showMenu(MouseEvent e) {
//				popup.show(e.getComponent(), e.getX(), e.getY());
//			}
//		});
//	}
  }
