package CheckersClient007;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

public class CheckerBoardPanel extends JPanel {
	private JLabel gameStatusLbl;
	private Board board;
	private ChatPanel chatPanel;
	private JButton readyButton;
	private JButton btnLeaveTable;
	private BufferedImage backgroundImage;

	private final String BACKGROUND_IMAGE_FILE_LOCATION = "Images\\metal007background.jpg";
	private JPanel pipePanel;
	public CheckerBoardPanel() {
		
		try
		{
			backgroundImage = ImageIO.read(new File(BACKGROUND_IMAGE_FILE_LOCATION));
		}
		catch (IOException e)
		{
			backgroundImage = null;
			System.out.println("ERROR: Chat image background not found.");
			e.printStackTrace();
		}
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{177, 421, 144, 0};
		gridBagLayout.rowHeights = new int[]{33, 217, 155, -14, 200, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setOpaque(false);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 3;
		gbc_panel_2.insets = new Insets(0, 0, 0, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		add(panel_2, gbc_panel_2);
		
		readyButton = new JButton("Press When Ready");
		readyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClientController.getInstance().ready();
				
				
			}
			
		});
		
		btnLeaveTable = new JButton("Leave Table");
		panel_2.add(btnLeaveTable);
		btnLeaveTable.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClientController.getInstance().leaveTable();
				
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
		
		pipePanel = new PipePanel();

		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 0, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		add(pipePanel, gbc_panel);
		
		chatPanel = new ChatPanel();
		GridBagConstraints gbc_panel1 = new GridBagConstraints();
		gbc_panel1.gridwidth = 3;
		gbc_panel1.fill = GridBagConstraints.BOTH;
		gbc_panel1.gridx = 0;
		gbc_panel1.gridy = 4;
		add(chatPanel, gbc_panel1);


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
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		if(backgroundImage != null)
		{
			g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

}

