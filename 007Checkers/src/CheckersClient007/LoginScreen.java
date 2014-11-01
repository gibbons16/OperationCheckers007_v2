package CheckersClient007;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class LoginScreen extends JPanel {
	/**
	 * @wbp.nonvisual location=358,214
	 */
	private final ImageIcon imageIcon = new ImageIcon();
	private JButton btnConnect;
	private JTextArea userNameTextArea;
	private JTextArea ipAddress;

	/**
	 * Create the panel.
	 */
	public LoginScreen() {
		setLayout(null);
		
		 userNameTextArea = new JTextArea();
		userNameTextArea.setBounds(195, 400, 405, 32);
		add(userNameTextArea);
		
		 ipAddress = new JTextArea("130.108.28.165"); // derek's IP
		ipAddress.setBounds(195, 445, 405, 32);
		add(ipAddress);
		
		JLabel lblNewLabel = new JLabel("IP");
		lblNewLabel.setBounds(167, 459, 56, 16);
		add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(128, 413, 78, 16);
		add(lblUsername);
		
		 btnConnect = new JButton("Connect");
		btnConnect.setBounds(299, 532, 97, 25);
		add(btnConnect);

	}
	public String getUserName()
	{
		
		return userNameTextArea.getText().toString();
	}
	public String getIpAddress()
	{
		return ipAddress.getText().toString();
	}
	public JButton getConnectButton()
	{
		return btnConnect;
	}
}


