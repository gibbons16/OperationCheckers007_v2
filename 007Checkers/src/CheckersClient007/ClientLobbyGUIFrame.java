package CheckersClient007;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import RMIConnection.Interfaces.CheckersClient;

public class ClientLobbyGUIFrame extends JFrame 
{

	private CheckerBoardPanel boardPanel; 
	private LoginScreen loginPanel;
	private LobbyPanel lobbyPanel;
	private ClientController controller;
	
	/**
	 * Create the frame.
	 */
	public ClientLobbyGUIFrame(ClientController client)
	{
		
		boardPanel = new CheckerBoardPanel();
		loginPanel = new LoginScreen();
		lobbyPanel = new LobbyPanel();
		this.controller = client;
		JButton connectButton = loginPanel.getConnectButton();
		connectButton.addActionListener(new ActionListener()
		{
 
            public void actionPerformed(ActionEvent e)
            {
            	controller.getClientConnection().connectToServer(loginPanel.getIpAddress(), loginPanel.getUserName());
            }
        });      
		this.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
			    int confirmed = JOptionPane.showConfirmDialog(null, 
			        "Are you sure you want to exit the program?", "Exit Program Message Box",
			        JOptionPane.YES_NO_OPTION);

			    if (confirmed == JOptionPane.YES_OPTION) {
			      ClientController.getInstance().disconnect(true);
			      dispose();
			    }
			  }
			});
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 925, 800);
		
		JMenuBar menuBar = new JMenuBar();
		//menuBar.getSize().width;
		setJMenuBar(menuBar);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenu mnPersonalStats = new JMenu("Personal Stats");
		menuBar.add(mnPersonalStats);
		
		JMenu mnConnection = new JMenu("Connection");
		menuBar.add(mnConnection);
		
		JMenu mnCustomize = new JMenu("Customize");
		menuBar.add(mnCustomize);
		JMenuItem changeColor1 = new JMenuItem("Change Board Color 1");
		mnCustomize.add(changeColor1);
		changeColor1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color c = JColorChooser.showDialog(null, "Choose a Color", null);
			      if (c != null)
			      {
			    	  Board.setColor1(c);
			      }
				
			}
			
			
		});
		JMenuItem changeColor2 = new JMenuItem("Change Board Color 2");
		changeColor2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color c = JColorChooser.showDialog(null, "Choose a Color", null);
			      if (c != null)
			      {
			    	  Board.setColor2(c);
			      }
				
			}
			
			
		});
		
		setContentPane(loginPanel);
	}
	
	public void switchToTable()
	{
		setContentPane(lobbyPanel);
		repaint();
		revalidate();
	}
	
	public void switchToCheckersBoard()
	{
		setContentPane(boardPanel);
		repaint();
		revalidate();
	}
	
	public CheckerBoardPanel getBoardPanel()
	{
		return this.boardPanel;
	}
	
	public LoginScreen getLoginScreenPanel()
	{
		return this.loginPanel;
	}
	
	public LobbyPanel getLobbyPanel()
	{
		return this.lobbyPanel;
	}
	
	public ClientController getClientController()
	{
		return this.controller;
	}
	
}

