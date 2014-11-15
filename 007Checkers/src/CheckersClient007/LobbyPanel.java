package CheckersClient007;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JScrollPane;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


public class LobbyPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private JLabel TableLbl;
	private JLabel playerListlbl;
	private JList<Integer> tableList;
	private JTextArea playerListTextArea;
	private JLabel gameDescriptionLabel;
	private ChatPanel chatPanel;
	private JPanel panel;
	private JButton btnNewButton;
	private JButton btnJoinTable;
	private JButton btnCreateTable;
	
	public LobbyPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{201, 288, 305, 0};
		gridBagLayout.rowHeights = new int[]{0, 60, 102, 0, 64, 315, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		TableLbl = new JLabel("Table");
		GridBagConstraints gbc_TableLbl = new GridBagConstraints();
		gbc_TableLbl.insets = new Insets(0, 0, 5, 5);
		gbc_TableLbl.gridx = 1;
		gbc_TableLbl.gridy = 0;
		add(TableLbl, gbc_TableLbl);
		
		playerListlbl = new JLabel("Players in Lobby");
		GridBagConstraints gbc_playerListlbl = new GridBagConstraints();
		gbc_playerListlbl.insets = new Insets(0, 0, 5, 0);
		gbc_playerListlbl.gridx = 2;
		gbc_playerListlbl.gridy = 0;
		add(playerListlbl, gbc_playerListlbl);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.WEST;
		gbc_panel.gridheight = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		btnCreateTable = new JButton("Create Table");
		btnCreateTable.addActionListener( new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				 ClientController.getInstance().makeTable();
				
			}
			
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 0;
		panel.add(btnCreateTable, gbc_btnNewButton_2);
		
		btnJoinTable = new JButton("Join Table");
		btnJoinTable.setEnabled(false);
		btnJoinTable.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
					ClientController.getInstance().joinTable(tableList.getSelectedValue());
	
				
			}
			
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 1;
		panel.add(btnJoinTable, gbc_btnNewButton_1);
		
		btnNewButton = new JButton("Observe Table");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 2;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JScrollPane tableListScrollPane = new JScrollPane();
		tableListScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_tableListScrollPane = new GridBagConstraints();
		gbc_tableListScrollPane.gridheight = 4;
		gbc_tableListScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_tableListScrollPane.fill = GridBagConstraints.BOTH;
		gbc_tableListScrollPane.gridx = 1;
		gbc_tableListScrollPane.gridy = 1;
		add(tableListScrollPane, gbc_tableListScrollPane);
		
		tableList = new JList();
		tableList.addListSelectionListener(new ListSelectionListener()
		{

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				btnJoinTable.setEnabled(false);
				try {
					ClientController.getInstance().getTableStatus(tableList.getSelectedValue());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		tableListScrollPane.setViewportView(tableList);
		
		JScrollPane playerListScrollPane = new JScrollPane();
		playerListScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_playerListScrollPane = new GridBagConstraints();
		gbc_playerListScrollPane.gridheight = 4;
		gbc_playerListScrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_playerListScrollPane.fill = GridBagConstraints.BOTH;
		gbc_playerListScrollPane.gridx = 2;
		gbc_playerListScrollPane.gridy = 1;
		add(playerListScrollPane, gbc_playerListScrollPane);
		
		playerListTextArea = new JTextArea();
		Font font = new Font("Verdana", Font.BOLD, 12);
		playerListTextArea.setFont(font);
		playerListTextArea.setForeground(Color.YELLOW);
		playerListTextArea.setEnabled(false);
		playerListScrollPane.setViewportView(playerListTextArea);
		
		gameDescriptionLabel = new JLabel("Game Description:\n(no game selected)");
		GridBagConstraints gbc_gameDescriptionLabel = new GridBagConstraints();
		gbc_gameDescriptionLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_gameDescriptionLabel.gridheight = 2;
		gbc_gameDescriptionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_gameDescriptionLabel.gridx = 0;
		gbc_gameDescriptionLabel.gridy = 3;
		add(this.gameDescriptionLabel, gbc_gameDescriptionLabel);
		
		
		chatPanel =new ChatPanel();
		GridBagConstraints gbc_panel1 = new GridBagConstraints();
		gbc_panel1.gridwidth = 3;
		gbc_panel1.fill = GridBagConstraints.BOTH;
		gbc_panel1.gridx = 0;
		gbc_panel1.gridy = 5;
		add(chatPanel, gbc_panel1);

	}
	
	public void setPlayerList(String[] players)
	{

		playerListTextArea.setText("");
		for(String s: players)
		{
			playerListTextArea.append(s + "\n");
		}
	
	}
	
	public void makeTableJoinable(boolean isJoinable)
	{
		if(isJoinable)
		{
			btnJoinTable.setEnabled(true);
		}
		else
		{
			btnJoinTable.setEnabled(false);
		}
	}
	
	public void setTableList(int[] tables)
	{
		DefaultListModel model = new DefaultListModel<Integer>();
		
        for(int i = 0; i < tables.length; i++)
        {
        	model.addElement(tables[i]);
        	
        }
        tableList.setModel(model);
	}
	
	public void addNewTable(int tableId)
	{
		ListModel oldModel = tableList.getModel();
		DefaultListModel newModel = new DefaultListModel<Integer>();
		
		for(int i = 0; i < oldModel.getSize(); i++) // adds each of the old tables
		{
			newModel.addElement(oldModel.getElementAt(i));
		}
		newModel.addElement(tableId); // adds the new table
		
		tableList.setModel(newModel);
	}
	
	public void removeTable(int tableRemoveId)
	{
		ListModel oldModel = tableList.getModel();
		DefaultListModel newModel = new DefaultListModel<Integer>();
		
		for(int i = 0; i < oldModel.getSize(); i++)
		{
			if(!oldModel.getElementAt(i).equals(tableRemoveId)) // add every table that isn't the target remove id
			{
				newModel.addElement(oldModel.getElementAt(i));
			}
		}
		
		tableList.setModel(newModel);
	}
	
	public void addNewPlayer(String player)
	{
		playerListTextArea.append(player);
	}
	
	public void removePlayer(String targetRemove)
	{
		targetRemove = targetRemove.concat("\n");
		String playerListText = playerListTextArea.getText();
		
		System.out.println("playerList before remove: \n"+playerListText);
		
		playerListText = playerListText.replace(targetRemove, "");
		
		System.out.println("playerList after remove: \n"+playerListText);
		
		playerListTextArea.setText(playerListText);
	}
	
	public void updateGameDescription(String gameStats)
	{
		this.gameDescriptionLabel.setText("Game Description".concat("\n").concat(gameStats));
	}

	public ChatPanel getChatPanel() {
		return chatPanel;
	}

	public void setChatPanel(ChatPanel chatPanel) {
		this.chatPanel = chatPanel;
	}

}
