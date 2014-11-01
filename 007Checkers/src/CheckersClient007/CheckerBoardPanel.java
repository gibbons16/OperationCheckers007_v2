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

public class CheckerBoardPanel extends JPanel {

	/**
	 * Create the panel.
	 */

	private JLabel gameStatusLbl;
	private Board board;

	public CheckerBoardPanel() {
		setLayout(null);
		
//		JTextArea textArea = new JTextArea();
//		textArea.setBounds(150, 559, 671, 28);
//		add(textArea);
//		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSend.setBounds(26, 559, 97, 25);
		add(btnSend);
		

//		JTextArea textArea_1 = new JTextArea();
//		textArea_1.setBounds(150, 451, 652, 95);
//		add(textArea_1);
//		
		 board = new Board(300,300);
		board.setBounds(230, 30, 394, 394);


		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(150, 451, 652, 95);
		add(textArea_1);
		
		Board boardPanel = new Board(300,300);
		boardPanel.setBounds(230, 30, 394, 394);

		//boardPanel.add(new Board(boardPanel.getWidth(), boardPanel.getHeight()));
		add(boardPanel);

		
		add(board);
		
//		JRadioButton rdbtnGlobal = new JRadioButton("Global");
//		rdbtnGlobal.setBounds(150, 422, 127, 25);
//		add(rdbtnGlobal);
//		
//		JRadioButton rdbtnPm = new JRadioButton("PM");
//		rdbtnPm.setBounds(546, 422, 53, 25);
//		add(rdbtnPm);
		
//		JComboBox comboBox = new JComboBox();
//		comboBox.setBounds(607, 426, 31, 22);
//		add(comboBox);
		
		 gameStatusLbl = new JLabel("");
		gameStatusLbl.setBounds(423, 13, 113, 16);
		add(gameStatusLbl);
		
		JButton btnReady = new JButton("Ready");
		btnReady.setBounds(283, 9, 97, 25);
		add(btnReady);

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
