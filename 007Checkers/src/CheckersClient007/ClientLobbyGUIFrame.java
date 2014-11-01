package CheckersClient007;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JList;
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

public class ClientLobbyGUIFrame extends JFrame {


	private CheckerBoardPanel boardPanel; 
	private LoginScreen loginPanel;
	private TablePanel tablePanel;
	private ClientController controller;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ClientLobbyGUIFrame frame = new ClientLobbyGUIFrame(null);
//					frame.setVisible(true);
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ClientLobbyGUIFrame(ClientController client){
		
		boardPanel = new CheckerBoardPanel();
		loginPanel = new LoginScreen();
		tablePanel = new TablePanel();
		this.controller = client;
		JButton connectButton = loginPanel.getConnectButton();
		connectButton.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
            	controller.getClientConnection().connectToServer(loginPanel.getIpAddress(), loginPanel.getUserName());
            }
        });      
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 650);
		
		JMenuBar menuBar = new JMenuBar();
		//menuBar.getSize().width;
		setJMenuBar(menuBar);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenu mnPersonalStats = new JMenu("Personal Stats");
		menuBar.add(mnPersonalStats);
		
		JMenu mnConnection = new JMenu("Connection");
		menuBar.add(mnConnection);
		
		setContentPane(loginPanel);
	}
	
	public void switchToTable()
	{
		setContentPane(tablePanel);
		repaint();
		revalidate();
	}
	public void switchToCheckersBoard()
	{
		setContentPane(boardPanel);
		repaint();
		revalidate();
	}
	public CheckersClient getContoller()
	{
		return this.controller;
	}
	public CheckerBoardPanel getCheckersBoardContainter()
	
	{
		return boardPanel;
	}
	public Board getBoard()
	{
		return boardPanel.getBoard();
	}
	
}

