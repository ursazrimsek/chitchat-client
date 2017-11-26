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
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class ChatFrame extends JFrame implements ActionListener, KeyListener {
	
	private JTextArea output;
	private JTextField input;

	public ChatFrame() {
		super();
		Container pane = this.getContentPane();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0};
		pane.setLayout(gridBagLayout);
		this.setTitle("ChitChat");
		
		
		this.output = new JTextArea(20, 40);
		this.output.setEditable(false);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(output);
		GridBagConstraints outputConstraint = new GridBagConstraints();
		outputConstraint.gridwidth = 2;
		outputConstraint.gridheight = 2;
		outputConstraint.insets = new Insets(0, 0, 5, 5);
		outputConstraint.weighty = 1.0;
		outputConstraint.weightx = 3.0;
		outputConstraint.fill = GridBagConstraints.BOTH;
		outputConstraint.gridx = 0;
		outputConstraint.gridy = 0;
		pane.add(scrollPane, outputConstraint);

		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setToolTipText("Prijavljeni uporabniki");
		GridBagConstraints gbc_verticalBox = new GridBagConstraints();
		gbc_verticalBox.weightx = 1.0;
		gbc_verticalBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_verticalBox.gridheight = 3;
		gbc_verticalBox.insets = new Insets(0, 0, 5, 0);
		gbc_verticalBox.gridx = 3;
		gbc_verticalBox.gridy = 0;
		getContentPane().add(verticalBox, gbc_verticalBox);
		
		this.input = new JTextField(40);
		GridBagConstraints inputConstraint = new GridBagConstraints();
		inputConstraint.insets = new Insets(0, 0, 5, 5);
		inputConstraint.gridwidth = 2;
		inputConstraint.fill = GridBagConstraints.HORIZONTAL;
		inputConstraint.gridx = 0;
		inputConstraint.gridy = 2;
		pane.add(input, inputConstraint);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnPrijavaodjava = new JMenu("ChitChat");
		menuBar.add(mnPrijavaodjava);
		
		JButton btnPrijava = new JButton("Prijava");
		mnPrijavaodjava.add(btnPrijava);
		
		JButton btnOdjava = new JButton("Odjava");
		mnPrijavaodjava.add(btnOdjava);
		
		JComboBox<String> colors = new JComboBox<String>();
		colors.setModel(new DefaultComboBoxModel<String>(new String[] {"Rdeča", "Rumena", "Modra", "Zelena"}));
		colors.setToolTipText("Barva pisave");
		menuBar.add(colors);
		
		JComboBox<Integer> fontSize = new JComboBox<Integer>();
		fontSize.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {10, 12, 14, 16}));
		fontSize.setToolTipText("Velikost pisave");
		menuBar.add(fontSize);
		
		JFormattedTextField nickname = new JFormattedTextField();
		nickname.setFocusLostBehavior(JFormattedTextField.REVERT);
		nickname.setHorizontalAlignment(SwingConstants.RIGHT);
		nickname.setToolTipText("Izberite si svoj vzdevek");
		nickname.setText("Vzdevek");
		menuBar.add(nickname);
		
		JRadioButton rdbtnJavno = new JRadioButton("Javno");
		rdbtnJavno.setToolTipText("Javno ali zasebno sporočilo?");
		menuBar.add(rdbtnJavno);
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
