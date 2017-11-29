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
	private JButton btnTest;
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
	
	
	public ChatFrame() {
		super();
		Container pane = this.getContentPane();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0};
		pane.setLayout(gridBagLayout);
		
		this.setTitle("ChitChat");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.nickname = System.getProperty("user.name");
		this.signedInUsers = new String();
		this.recipient = new String();
		
		
		// POGOVOR
		// Output
		this.output = new JTextArea(20, 40);
		output.setFont(new Font("Monospaced", Font.BOLD, 12));
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
		
				// Barve
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
		
				// Velikosti
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
	

		// Ko se okno odpre
		addWindowListener(new WindowAdapter() {
		    public void windowOpened( WindowEvent e1 ){
		        try {
					Boolean success = App.logIn(nickname);
			        if (success) {
						input.requestFocus();
			        } else {
			        	input.setEditable(false);
						input.setText("Izberite si vzdevek!");
						txt_nickname.setText("");
						txt_nickname.requestFocus();
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
	
	// Posodobi vpisane uporabnike
	public void getSignedInUsers() throws ClientProtocolException, IOException {
		List<User> users = App.getUsers();
		this.signedInUsers = "";
		for (User user : users) {
			this.signedInUsers += user.getUsername() + "\n";
		}
		this.txt_signedInUsers.setText(this.signedInUsers);
	}
	
	
	private Boolean signedIn(String person) throws ClientProtocolException, IOException { 
		List<User> users = App.getUsers();
		for (User user : users) {
			if (user.getUsername().equals(person)) {
				return true;
			} 
		}
		return false;
	}

	private void addSentMessage(String person, String message) throws ClientProtocolException, IOException, URISyntaxException {
		String chat = this.output.getText();
		String sendingTo = new String();
		if (this.global.isSelected()) {
			sendingTo = "(Javno)";
		} else {
			sendingTo = "(" + this.recipient + ")";
		}
		this.output.setText(chat + person + ": " + message + " " + sendingTo + "\n") ;
		App.sendMessage(global.isSelected(), person, this.recipient, message);
	}
	
	public void addRecievedMessage() throws ClientProtocolException, URISyntaxException, IOException {
		try {	
			if (signedIn(this.nickname)) {
				List<Message> newMessages = App.recieveMessages(this.nickname);
				if (! newMessages.isEmpty()) {
					String chat = this.output.getText();
					for (Message message : newMessages) {
						if (message.getGlobal()) {
							this.output.setText(chat + message.getSender() + ": " + message.getText() + " (Javno)" + "\n");
						} else {
							this.output.setText(chat + message.getSender() + ": " + message.getText() + "\n");
						}
					}
				}
			}
		} catch (Exception HttpResponseException) {
			System.out.println("HTTP" + this.nickname);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		// Gumb za prijavo ali odjavo
		if (source == this.btnSignIn | source == this.btnSignOut) {
			Boolean userSignedIn = new Boolean(false);
			try {
				userSignedIn = signedIn(nickname);
			} catch (ClientProtocolException e2) {
				e2.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		
			if (source == this.btnSignIn) {
				if (!userSignedIn);	
					try {
						App.logIn(nickname);
						this.input.setEditable(true);
						this.input.setText("");
					} catch (ClientProtocolException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			} else {
				if (userSignedIn);
					try {
						App.logOut(nickname);
						this.input.setEditable(false);
						this.input.setText("Za pošiljanje sporočil se morate prijaviti!");
					} catch (ClientProtocolException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}
		// Gumb za izbris pogovora
		} if (source == this.btnDelete) {
			this.output.setText("");
			MenuSelectionManager.defaultManager().clearSelectedPath();
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
					this.input.requestFocus();
				}
			} else {
				this.input.setEditable(true);
				this.input.setText("");
				this.input.requestFocus();
			}
		} 
		
		// Meni za izbiro barve okna
		if (source == this.wdWhite) {
			this.output.setBackground(new Color(255,255,255));
			txt_signedInUsers.setBackground(SystemColor.menu);
		} if (source == this.wdBlue) {	
		} if (source == this.wdGreen) {
			this.output.setBackground(new Color(152,251,152));
			this.txt_signedInUsers.setBackground(new Color(143,188,143));
		} if (source == this.wdBlue) {
			this.output.setBackground(new Color(176,224,230));
			this.txt_signedInUsers.setBackground(new Color(176,196,222));
		} if (source == this.wdYellow) {
			this.output.setBackground(new Color(255,255,153));
			this.txt_signedInUsers.setBackground(new Color(255,228,181));
		} 
		// Meni za izbiro barve fonta
		if (source == this.ftBlack) {
			this.output.setForeground(new Color(0,0,0));
		} if (source == this.ftBlue) {
			this.output.setForeground(new Color(0,0,205));
		} if (source == this.ftGreen) {
			this.output.setForeground(new Color(0,100,0));
		}  
		// Meni za izbiro velikosti fonta
		if (source == this.ftSize10) {
			this.output.setFont(new Font("Monospaced", Font.BOLD, 10));
			this.txt_signedInUsers.setFont(new Font("Monospaced", Font.BOLD, 10));
			this.input.setFont(new Font("Tahoma", Font.PLAIN, 10));
			this.txt_recipient.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		} if (source == this.ftSize12) {
			this.output.setFont(new Font("Monospaced", Font.BOLD, 12));
			this.txt_signedInUsers.setFont(new Font("Monospaced", Font.BOLD, 12));
			this.input.setFont(new Font("Tahoma", Font.PLAIN, 12));
			this.txt_recipient.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		} if (source == this.ftSize14) {
			this.output.setFont(new Font("Monospaced", Font.BOLD, 14));
			this.txt_signedInUsers.setFont(new Font("Monospaced", Font.BOLD, 14));
			this.input.setFont(new Font("Tahoma", Font.PLAIN, 14));
			this.txt_recipient.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		}



		
		if (source == this.btnTest) {
			
			try {
				App.logIn("Test");
				App.sendMessage(true, "Test", "Ursa", "Pa da vidmo");
				App.sendMessage(false, "Test", "Ursa", "Pa da vidmo zasebno");
			} catch (ClientProtocolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
			
		
	}

	public void keyTyped(KeyEvent e) {
		Object source = e.getSource();
		// Vnos vzdevka
		if (source == this.txt_nickname) {
			if (e.getKeyChar() == '\n') {
				if (! this.txt_nickname.getText().equals(this.nickname)) {
					Boolean in = new Boolean(true);
					try { 
						in = signedIn(this.txt_nickname.getText());
					} catch (ClientProtocolException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					if (in) {
						this.input.setText("Izberite si drugo ime, to že obstaja!");
						this.input.setEditable(false);
					} else {
						this.previousNickname = this.nickname;
						this.nickname = this.txt_nickname.getText();
						MenuSelectionManager.defaultManager().clearSelectedPath();
						this.input.setEditable(true);
						this.input.setText("");
						this.input.requestFocus();
						try {
							if (signedIn(this.previousNickname)) { 
								App.logOut(this.previousNickname);
							}
							App.logIn(this.nickname);
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
		// Vnos prejemnika
		} if (source == this.txt_recipient) {
			if (e.getKeyChar() == '\n') {
				this.recipient = this.txt_recipient.getText();
				this.global.setSelected(false);
				this.input.setEditable(true);
				this.input.requestFocus();
				this.input.setText("");
				}
		// Vnos besedila
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


  }
