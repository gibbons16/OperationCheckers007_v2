package CheckersClient007;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginScreen extends JPanel {

	private  ImageIcon loginLogo;
	private JButton btnConnect;
	private JTextField userNameTestField;
	private JTextField ipAddressTestField;
	private JLabel imageLabel;

	/**
	 * Create the panel.
	 */
	public LoginScreen() {
		setBackground(SystemColor.windowText);
		loginLogo = new ImageIcon("Images/bondlogo007.jpg");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
	   imageLabel = new JLabel(loginLogo);
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 4;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(imageLabel, gbc_panel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 6;
		add(lblUsername, gbc_lblUsername);
		
		userNameTestField = new JTextField();
		userNameTestField.setCaretColor(Color.GREEN);
		userNameTestField.setForeground(Color.GREEN);
		userNameTestField.setBackground(Color.BLACK);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 6;
		add(userNameTestField, gbc_textField_1);
		userNameTestField.setColumns(10);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblIp.anchor = GridBagConstraints.EAST;
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 7;
		add(lblIp, gbc_lblIp);
		
		ipAddressTestField = new JTextField("130.108.28.165");
		ipAddressTestField.setCaretColor(Color.GREEN);
		ipAddressTestField.setBackground(Color.BLACK);
		ipAddressTestField.setForeground(Color.GREEN);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 7;
		add(ipAddressTestField, gbc_textField);
		ipAddressTestField.setColumns(10);
		
		 btnConnect = new JButton("Connect");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 8;
		add(btnConnect, gbc_btnNewButton);

		userNameTestField.addKeyListener(
	            new KeyListener(){
	            	
					@Override
					public void keyPressed(KeyEvent arg0)
					{
						if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
							if(userNameTestField.getText().length() > 0 && ipAddressTestField.getText().length() > 0)
							{
								ClientController.getInstance().getClientConnection().connectToServer(getIpAddress(), getUserName());
				            	ClientController.getInstance().setUserName(getUserName());
							}
	                    }   
					}

					@Override
					public void keyReleased(KeyEvent arg0)
					{
					}

					@Override
					public void keyTyped(KeyEvent arg0)
					{
					}
	            }
	        );
		ipAddressTestField.addKeyListener(
	            new KeyListener(){
	            	
					@Override
					public void keyPressed(KeyEvent arg0)
					{
						if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
							if(userNameTestField.getText().length() > 0 && ipAddressTestField.getText().length() > 0)
							{
								ClientController.getInstance().getClientConnection().connectToServer(getIpAddress(), getUserName());
				            	ClientController.getInstance().setUserName(getUserName());
							}
	                    }   
					}

					@Override
					public void keyReleased(KeyEvent arg0)
					{
					}

					@Override
					public void keyTyped(KeyEvent arg0)
					{
					}
	            }
	        );
	}
	public String getUserName()
	{
		
		return userNameTestField.getText().toString();
	}
	public String getIpAddress()
	{
		return ipAddressTestField.getText().toString();
	}
	public JButton getConnectButton()
	{
		return btnConnect;
	}
}


