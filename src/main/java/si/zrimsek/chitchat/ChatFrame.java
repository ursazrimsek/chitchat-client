package si.zrimsek.chitchat;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.DropMode;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.SystemColor;

public class ChatFrame extends JFrame implements ActionListener, KeyListener {
	
	private JTextArea output;
	private JTextField input;
	private JTextField nickname;
	private JTextArea signedInUsers;
	private JTextField recipient;
	private JComboBox<String> windowColor;
	private JRadioButton global;

	public ChatFrame() {
		super();
		Container pane = this.getContentPane();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0};
		pane.setLayout(gridBagLayout);
		
		this.setTitle("ChitChat");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
		
		
		// POGOVOR
		
		// Output
		this.output = new JTextArea(20, 40);
		this.output.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(output);
		GridBagConstraints outputConstraint = new GridBagConstraints();
		outputConstraint.gridwidth = 2;
		outputConstraint.gridheight = 3;
		outputConstraint.insets = new Insets(0, 0, 5, 5);
		outputConstraint.weighty = 1.0;
		outputConstraint.weightx = 3.0;
		outputConstraint.fill = GridBagConstraints.BOTH;
		outputConstraint.gridx = 0;
		outputConstraint.gridy = 0;
		pane.add(scrollPane, outputConstraint);
		
		// Input
		this.input = new JTextField(40);
		GridBagConstraints inputConstraint = new GridBagConstraints();
		inputConstraint.insets = new Insets(0, 0, 0, 5);
		inputConstraint.gridwidth = 2;
		inputConstraint.fill = GridBagConstraints.HORIZONTAL;
		inputConstraint.gridx = 0;
		inputConstraint.gridy = 3;
		pane.add(input, inputConstraint);
		
		
		// Vpisani uporabniki
		JTextField txtSignedInUsers = new JTextField();
		txtSignedInUsers.setBorder(null);
		txtSignedInUsers.setHorizontalAlignment(SwingConstants.LEFT);
		txtSignedInUsers.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtSignedInUsers.setEditable(false);
		txtSignedInUsers.setText("Prijavljeni:");
		GridBagConstraints gbc_txtSignedInUsers = new GridBagConstraints();
		gbc_txtSignedInUsers.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSignedInUsers.insets = new Insets(0, 0, 5, 5);
		gbc_txtSignedInUsers.gridx = 2;
		gbc_txtSignedInUsers.gridy = 0;
		getContentPane().add(txtSignedInUsers, gbc_txtSignedInUsers);
		txtSignedInUsers.setColumns(10);
		
		signedInUsers = new JTextArea(0, 10);
		signedInUsers.setBackground(SystemColor.menu);
		signedInUsers.setEditable(false);
		JScrollPane siuScroll = new JScrollPane(signedInUsers);
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
		recipient = new JTextField();
		GridBagConstraints recipientConstraints = new GridBagConstraints();
		recipientConstraints.gridheight = 1;
		recipientConstraints.weightx = 1.0;
		recipientConstraints.fill = GridBagConstraints.BOTH;
		recipientConstraints.insets = new Insets(0, 0, 0, 5);
		recipientConstraints.gridx = 2;
		recipientConstraints.gridy = 3;
		recipient.setHorizontalAlignment(SwingConstants.LEFT);
		recipient.setText("Prejemnik");
		recipient.setToolTipText("Vnesite prejemnika");
		pane.add(recipient, recipientConstraints);
		
		
			
		
		// MENIJI
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// Prijava/odjava		
		JMenu mnSignInOut = new JMenu("ChitChat");
		menuBar.add(mnSignInOut);
		
		JButton btnSignIn = new JButton("Prijava");
		mnSignInOut.add(btnSignIn);
		
		JButton btnSignOut = new JButton("Odjava");
		mnSignInOut.add(btnSignOut);
		
		// Pisava
		JMenu mnFont = new JMenu("Pisava");
		menuBar.add(mnFont);
		
		JComboBox<String> fontColor = new JComboBox<String>();
		mnFont.add(fontColor);
		fontColor.setModel(new DefaultComboBoxModel<String>(new String[] {"Rdeča", "Rumena", "Modra", "Zelena"}));
		fontColor.setToolTipText("Barva pisave");
		
		JComboBox<Integer> fontSize = new JComboBox<Integer>();
		mnFont.add(fontSize);
		fontSize.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {10, 12, 14, 16}));
		fontSize.setToolTipText("Velikost pisave");
		
		// Okno
		JMenu mnWindow = new JMenu("Več");
		menuBar.add(mnWindow);
		
				// Vzdevek
		nickname = new JTextField();
		nickname.setDropMode(DropMode.USE_SELECTION);
		mnWindow.add(nickname);
		nickname.setHorizontalAlignment(SwingConstants.LEFT);
		nickname.setToolTipText("Izberite si svoj vzdevek");
		nickname.setText(System.getProperty("user.name"));
		

		
				// Barva okna
		windowColor = new JComboBox<String>();
		windowColor.setModel(new DefaultComboBoxModel<String>(new String[] {"Modra", "Rumena", "Zelena"}));
		windowColor.setToolTipText("Nastavite barvo okna");
		mnWindow.add(windowColor);
		
		// Javno
		global = new JRadioButton("Javno");
		global.setHorizontalAlignment(SwingConstants.RIGHT);
		global.setSelected(true);
		menuBar.add(global);
		global.setToolTipText("Javno ali zasebno sporočilo?");
	}

	/**
	 * @param person - the person sending the message
	 * @param message - the message content
	 */
	public void addMessage(String person, String message) {
		String chat = this.output.getText();
		this.output.setText(chat + person + ": " + message + "\n");
	}
	
	public void actionPerformed(ActionEvent e) {
	}

	public void keyTyped(KeyEvent e) {
		if (e.getSource() == this.input) {
			if (e.getKeyChar() == '\n') {
				this.addMessage(System.getProperty("user.name"), this.input.getText());
				this.input.setText("");
			}
		}		
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
