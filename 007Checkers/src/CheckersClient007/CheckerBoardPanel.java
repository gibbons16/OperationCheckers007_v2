package CheckersClient007;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class CheckerBoardPanel extends JPanel {
	private JLabel gameStatusLbl;
	private Board board;
	public CheckerBoardPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 43, 854, 0};
		gridBagLayout.rowHeights = new int[]{59, 405, 210, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 2;
		gbc_panel_2.gridy = 0;
		add(panel_2, gbc_panel_2);
		
		JButton readyButton = new JButton("Press When Ready");
		panel_2.add(readyButton);
		
		gameStatusLbl = new JLabel("Status: waiting...");
		panel_2.add(gameStatusLbl);
		
		board= new Board();
		GridBagConstraints gbc_board = new GridBagConstraints();
		gbc_board.insets = new Insets(0, 0, 5, 0);
		gbc_board.fill = GridBagConstraints.BOTH;
		gbc_board.gridx = 2;
		gbc_board.gridy = 1;
		add(board, gbc_board);
		
		ChatPanel panel = ChatPanel.getInstance();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);

	}

	
	public Board getBoard()
	{
		return board;
	}
	public void updateStatus(String status)
	{
		gameStatusLbl.setText(status);
	}
	

}
