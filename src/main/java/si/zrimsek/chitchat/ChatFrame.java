package si.zrimsek.chitchat;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

@SuppressWarnings("serial")
public class ChatFrame extends JFrame implements ActionListener, KeyListener {
	
	private JTextArea output;
	private JTextField input;
	private String nickname;
	private String previousNickname;
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
	private JMenu mnFontColor;
	private JMenuItem ftBlack;
	private JMenuItem ftGreen;
	private JMenuItem ftBlue;
	private JMenu mnfontSize;
	private JMenuItem ftSize12;
	private JMenuItem ftSize10;
	private JMenuItem ftSize14;
	private JMenu mnWindowColor;
	private JMenuItem wdGreen;
	private JMenuItem wdBlue;
	private JMenuItem wdWhite;
	private JMenuItem wdYellow;
	private JRadioButton parrot;
	
	
	public ChatFrame() {
		super();
		Container pane = this.getContentPane();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0};
		pane.setLayout(gridBagLayout);
		
		this.setTitle("ChitChat");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		nickname = System.getProperty("user.name");
		signedInUsers = new String("");
		recipient = new String("");
		
		
		// POGOVOR
		// Output
		output = new JTextArea(20, 40);
		output.setFont(new Font("Monospaced", Font.BOLD, 12));
		output.setEditable(false);
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
		input = new JTextField(40);
		input.setFont(new Font("Tahoma", Font.PLAIN, 12));
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
		
			
		// Vpisani uporabniki
				// Napis
		JTextField txtSignedIn = new JTextField();
		txtSignedIn.setBorder(null);
		txtSignedIn.setHorizontalAlignment(SwingConstants.LEFT);
		txtSignedIn.setFont(new Font("Arial Black", Font.PLAIN, 13));
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
		txt_signedInUsers.setFont(new Font("Monospaced", Font.BOLD, 12));
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
		
		
		// Prejemnik
		txt_recipient = new JTextField("Prejemnik");
		txt_recipient.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
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
		
		txt_recipient.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                txt_recipient.setText("");
                recipient = "";
                if (! global.isSelected()) {
    				if (recipient.length() == 0) {
    					input.setEditable(false);
    					input.setText("Izberite prejemnika ali pošiljajte javno sporočilo!");
    				}
                }
            }
        });
		
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
		txt_nickname.setText(this.nickname);
		mnSignInOut.add(btnSignIn);
		
		btnSignOut = new JButton("Odjava");
		btnSignOut.addActionListener(this);
		mnSignInOut.add(btnSignOut);
		
		// Pisava
		JMenu mnFont = new JMenu("Pisava");
		menuBar.add(mnFont);
		
				// Barve pisave
		mnFontColor = new JMenu("Barva");
		mnFont.add(mnFontColor);
		
		ftBlack = new JMenuItem("Črna");
		ftBlack.addActionListener(this);
		ftBlack.setForeground(new Color(0,0,0));
		mnFontColor.add(ftBlack);
		
		ftGreen = new JMenuItem("Zelena");
		ftGreen.addActionListener(this);
		ftGreen.setForeground(new Color(0,100,0));
		mnFontColor.add(ftGreen);
		
		ftBlue = new JMenuItem("Modra");
		ftBlue.addActionListener(this);
		ftBlue.setForeground(new Color(0,0,205));
		mnFontColor.add(ftBlue);
		
				// Velikosti pisave
		mnfontSize = new JMenu("Velikost");
		mnFont.add(mnfontSize);
		

		ftSize10 = new JMenuItem("10");
		ftSize10.addActionListener(this);
		mnfontSize.add(ftSize10);
		
		ftSize12 = new JMenuItem("12");
		ftSize12.addActionListener(this);
		mnfontSize.add(ftSize12);
		
		ftSize14 = new JMenuItem("14");
		ftSize14.addActionListener(this);
		mnfontSize.add(ftSize14);
		
		
		// Okno
		mnWindow = new JMenu("Okno");
		menuBar.add(mnWindow);
		
				// Barva okna
		mnWindowColor = new JMenu("Barva");
		mnWindow.add(mnWindowColor);
		
		wdWhite = new JMenuItem("Bela");
		wdWhite.addActionListener(this);
		wdWhite.setBackground(new Color(255,255,255));
		mnWindowColor.add(wdWhite);
		
		wdGreen = new JMenuItem("Zelena");
		wdGreen.addActionListener(this);
		wdGreen.setBackground(new Color(152,251,152));
		mnWindowColor.add(wdGreen);
		
		wdBlue = new JMenuItem("Modra");
		wdBlue.addActionListener(this);
		wdBlue.setBackground(new Color(176,224,230));
		mnWindowColor.add(wdBlue);
		
		wdYellow = new JMenuItem("Rumena");
		wdYellow.addActionListener(this);
		wdYellow.setBackground(new Color(255,255,153));
		mnWindowColor.add(wdYellow);

				// Pobriši
		btnDelete = new JButton("Zbriši pogovor");
		btnDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnDelete.addActionListener(this);
		btnDelete.setToolTipText("Pobriše dosedanji pogovor");
		mnWindow.add(btnDelete);
		
		
		// Javno
		global = new JRadioButton("Javno");
		global.addActionListener(this);
		global.setHorizontalAlignment(SwingConstants.RIGHT);
		global.setSelected(true);
		menuBar.add(global);
		global.setToolTipText("Javno ali zasebno sporočilo?");
		
		parrot = new JRadioButton("Papiga");
		parrot.addActionListener(this);
		menuBar.add(parrot);
		parrot.setToolTipText("Se želite pogovarjati s papigo?");
	

		// Ko se okno odpre
		addWindowListener(new WindowAdapter() {
		    public void windowOpened( WindowEvent e1 ){
		        try {
					Boolean success = App.logIn(nickname);
			        if (success) {
						input.requestFocus();
			        } else {
			        	input.setEditable(false);
						input.setText("V meniju ChitChat si izberite vzdevek!");
						txt_nickname.setText("");
						nickname = "";
			        }
				} catch (URISyntaxException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		});
		// Ko se okno zapre
		addWindowListener(new WindowAdapter() {
		    public void windowClosing( WindowEvent e ) {
		    	try {
					if (signedIn(nickname)) {
						App.logOut(nickname);;
					}
				} catch (ClientProtocolException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
		    }
		});
	
	}
	
	// Posodobitev vpisanih uporabnikov
	public void getSignedInUsers() throws ClientProtocolException, IOException {
		List<User> users = App.getUsers();
		signedInUsers = "";
		for (User user : users) {
			signedInUsers += user.getUsername() + "\n";
		}
		txt_signedInUsers.setText(signedInUsers);
	}
	
	// Vpis uporabnika
	private Boolean signedIn(String person) throws ClientProtocolException, IOException { 
		List<User> users = App.getUsers();
		for (User user : users) {
			if (user.getUsername().equals(person)) {
				return true;
			} 
		}
		return false;
	}
	
	// Pošiljanje sporočila
	private void addSentMessage(String person, String message) throws ClientProtocolException, IOException, URISyntaxException {
		String chat = output.getText();
		String sendingTo = new String();
		String parrotTalk = new String();
		if (global.isSelected()) {
			sendingTo = "(Javno pošiljanje)";
		} else {
			sendingTo = "(Prejemnik: " + recipient + ")";
		}
		if (parrot.isSelected()) {
			parrotTalk = "Papiga: " + message + "!!\n";
			if (message.length() < 50) {
				parrotTalk = "Papiga: " + message + ", " + message.toUpperCase()+ "!!\n";
			}
		}
		output.setText(chat + person + ": " + message + " " + sendingTo + "\n" + parrotTalk) ;
		App.sendMessage(global.isSelected(), person, recipient, message);
	}
	
	// Prejem sporočila
	public void addRecievedMessage() throws ClientProtocolException, URISyntaxException, IOException {
		try {	
			if (signedIn(nickname)) {
				List<Message> newMessages = App.recieveMessages(nickname);
				if (! newMessages.isEmpty()) {
					String chat = output.getText();
					for (Message message : newMessages) {
						if (message.getGlobal()) {
							output.setText(chat + message.getSender() + ": " + message.getText() + " (Javno)" + "\n");
						} else {
							output.setText(chat + message.getSender() + ": " + message.getText() + "\n");
						}
					}
				}
			}
		} catch (Exception HttpResponseException) { }
	}
	
	// Funkcija za zamenjavo vzdevka
	public void changeNickname() {
		if (! (txt_nickname.getText().equals(nickname) | txt_nickname.getText().length() == 0)) {
			Boolean in = new Boolean(true);
			try { 
				in = signedIn(txt_nickname.getText());
			} catch (ClientProtocolException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if (in) {
				input.setText("Izberite si drugo ime, to že obstaja!");
				input.setEditable(false);
			} else {
				previousNickname = nickname;
				nickname = txt_nickname.getText();
				MenuSelectionManager.defaultManager().clearSelectedPath();
				input.setEditable(true);
				input.setText("");
				input.requestFocus();
				try {
					if (signedIn(previousNickname)) { 
						App.logOut(previousNickname);
					}
					App.logIn(nickname);
				} catch (ClientProtocolException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	// Pritiski na gumbe
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == btnSignOut) { // Gumb za odjavo
			Boolean userSignedIn = new Boolean(false);
			try {
				userSignedIn = signedIn(nickname);
			} catch (ClientProtocolException e2) {
				e2.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			if (userSignedIn) { 
				try {
					App.logOut(nickname);
					nickname = "";
					input.setEditable(false);
					input.setText("Za pošiljanje sporočil se morate prijaviti!");
				} catch (ClientProtocolException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		if (source == btnSignIn) { // Gumb za prijavo
			changeNickname();
		}
		if (source == btnDelete) { // Gumb za izbris pogovora
			output.setText("");
			MenuSelectionManager.defaultManager().clearSelectedPath();
		} if (source == global) {
			if (! global.isSelected()) {
				if (recipient.length() == 0) {
					txt_recipient.setText("");
					txt_recipient.requestFocus();
					input.setEditable(false);
					input.setText("Izberite prejemnika ali pošiljajte javno sporočilo!");
				} else {
					input.setEditable(true);
					input.setText("");
					input.requestFocus();
				}
			} else {
				input.setEditable(true);
				input.setText("");
				input.requestFocus();
			}
		} 
		// Meni za izbiro barve okna
		if (source == wdWhite) {
			output.setBackground(new Color(255,255,255));
			txt_signedInUsers.setBackground(SystemColor.menu);	
		} if (source == wdGreen) {
			output.setBackground(new Color(152,251,152));
			txt_signedInUsers.setBackground(new Color(143,188,143));
		} if (source == wdBlue) {
			output.setBackground(new Color(176,224,230));
			txt_signedInUsers.setBackground(new Color(176,196,222));
		} if (source == wdYellow) {
			output.setBackground(new Color(255,255,153));
			txt_signedInUsers.setBackground(new Color(255,228,181));
		} 
		// Meni za izbiro barve fonta
		if (source == ftBlack) {
			output.setForeground(new Color(0,0,0));
		} if (source == ftBlue) {
			output.setForeground(new Color(0,0,205));
		} if (source == ftGreen) {
			output.setForeground(new Color(0,100,0));
		}  
		// Meni za izbiro velikosti fonta
		if (source == ftSize10) {
			output.setFont(new Font("Monospaced", Font.BOLD, 10));
			txt_signedInUsers.setFont(new Font("Monospaced", Font.BOLD, 10));
			input.setFont(new Font("Tahoma", Font.PLAIN, 10));
			txt_recipient.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		} if (source == ftSize12) {
			output.setFont(new Font("Monospaced", Font.BOLD, 12));
			txt_signedInUsers.setFont(new Font("Monospaced", Font.BOLD, 12));
			input.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txt_recipient.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		} if (source == ftSize14) {
			output.setFont(new Font("Monospaced", Font.BOLD, 14));
			txt_signedInUsers.setFont(new Font("Monospaced", Font.BOLD, 14));
			input.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txt_recipient.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		}
	}
	
	// Vnosi besedila
	public void keyTyped(KeyEvent e) {
		Object source = e.getSource();
		
		if (source == txt_nickname) {		// Vnos vzdevka
			if (e.getKeyChar() == '\n') {
				changeNickname();
			}
		} 
		if (source == txt_recipient) {		// Vnos prejemnika
			if (e.getKeyChar() == '\n') {
				if (txt_recipient.getText().length() != 0) {
					recipient = txt_recipient.getText();
					global.setSelected(false);
					input.setEditable(true);
					input.requestFocus();
					input.setText("");
				}
			}
		}
		if (source == input) {		// Vnos sporočil
			if (e.getKeyChar() == '\n') {
				try {
					addSentMessage(this.nickname, input.getText());
					input.setText("");
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

	
	// Za nas nepotrebni funkciji
	public void keyPressed(KeyEvent e) { }

	public void keyReleased(KeyEvent e) { }


  }
