package CheckersClient007;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class CheckerBoardPanel extends JPanel {
	private JLabel gameStatusLbl;
	private Board board;
	ChatPanel chatPanel;
	JButton readyButton;
	private JButton btnLeaveTable;
	
	public CheckerBoardPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{177, 421, 144, 0};
		gridBagLayout.rowHeights = new int[]{33, 217, 155, 200, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		btnLeaveTable = new JButton("Leave Table");
		btnLeaveTable.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClientController.getInstance().leaveTable();
				
			}
			
		});
		GridBagConstraints gbc_btnLeaveTable = new GridBagConstraints();
		gbc_btnLeaveTable.insets = new Insets(0, 0, 5, 5);
		gbc_btnLeaveTable.gridx = 0;
		gbc_btnLeaveTable.gridy = 0;
		add(btnLeaveTable, gbc_btnLeaveTable);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		add(panel_2, gbc_panel_2);
		
		readyButton = new JButton("Press When Ready");
		readyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClientController.getInstance().ready();
				
				
			}
			
		});
		panel_2.add(readyButton);
		
		gameStatusLbl = new JLabel("Status: waiting...");
		panel_2.add(gameStatusLbl);
		
		board= new Board();
		GridBagConstraints gbc_board = new GridBagConstraints();
		gbc_board.gridheight = 2;
		gbc_board.insets = new Insets(0, 0, 5, 5);
		gbc_board.fill = GridBagConstraints.BOTH;
		gbc_board.gridx = 1;
		gbc_board.gridy = 1;
		add(board, gbc_board);
		
		chatPanel = new ChatPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		add(chatPanel, gbc_panel);


	}

	public void reset()
	{
		readyButton.setEnabled(true);
		gameStatusLbl.setText("Status: waiting...");
		board.clearAllPieces();
		repaint();
	}
	public Board getBoard()
	{
		return board;
	}
	public void updateStatus(String status)
	{
		gameStatusLbl.setText(status);
	}


	public void setChatPanel(ChatPanel chatPanel) {
		this.chatPanel = chatPanel;
	}


	public ChatPanel getChatPanel() {
		return chatPanel;
	}
	public void setReadyUp(boolean enability)
	{
		readyButton.setEnabled(enability);
	}
	public void setStatus(String status)
	{
		gameStatusLbl.setText(status);
		
	}

}

