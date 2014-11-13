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
	JLabel TableLbl;
	JLabel playerListlbl;
	JButton createTableBtn;
	JList<Integer> tableList;
	JTextArea playerListTextArea;
	JButton btnJoinTable;
	JButton btnObserveTable ;
	ChatPanel chatPanel;
	
	public LobbyPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 139, 102, 64, 315, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		TableLbl = new JLabel("Table");
		GridBagConstraints gbc_TableLbl = new GridBagConstraints();
		gbc_TableLbl.insets = new Insets(0, 0, 5, 5);
		gbc_TableLbl.gridx = 1;
		gbc_TableLbl.gridy = 0;
		add(TableLbl, gbc_TableLbl);
		
		playerListlbl = new JLabel("Player List");
		GridBagConstraints gbc_playerListlbl = new GridBagConstraints();
		gbc_playerListlbl.insets = new Insets(0, 0, 5, 0);
		gbc_playerListlbl.gridx = 2;
		gbc_playerListlbl.gridy = 0;
		add(playerListlbl, gbc_playerListlbl);
		
		createTableBtn = new JButton("Create Table");
		createTableBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClientController.getInstance().makeTable();
			}
	    	
	    });
		GridBagConstraints gbc_createTableBtn = new GridBagConstraints();
		gbc_createTableBtn.insets = new Insets(0, 0, 5, 5);
		gbc_createTableBtn.gridx = 0;
		gbc_createTableBtn.gridy = 1;
		add(createTableBtn, gbc_createTableBtn);
		
		JScrollPane tableListScrollPane = new JScrollPane();
		tableListScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_tableListScrollPane = new GridBagConstraints();
		gbc_tableListScrollPane.gridheight = 3;
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
		gbc_playerListScrollPane.gridheight = 3;
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
		
		btnJoinTable = new JButton("Join Table");
		btnJoinTable.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClientController.getInstance().joinTable(tableList.getSelectedValue());
			}
	    	
	    });
		GridBagConstraints gbc_btnJoinTable = new GridBagConstraints();
		gbc_btnJoinTable.insets = new Insets(0, 0, 5, 5);
		gbc_btnJoinTable.gridx = 0;
		gbc_btnJoinTable.gridy = 2;
		add(btnJoinTable, gbc_btnJoinTable);
		
		btnObserveTable = new JButton("Observe Table");
		btnObserveTable.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					ClientController.getInstance().observeTable(tableList.getSelectedValue());
				} catch (RemoteException e)
				{
					e.printStackTrace();
				}
			}
	    });
		GridBagConstraints gbc_btnObserveTable = new GridBagConstraints();
		gbc_btnObserveTable.insets = new Insets(0, 0, 5, 5);
		gbc_btnObserveTable.gridx = 0;
		gbc_btnObserveTable.gridy = 3;
		add(btnObserveTable, gbc_btnObserveTable);
		
		 chatPanel =new ChatPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 4;
		add(chatPanel, gbc_panel);

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

	public ChatPanel getChatPanel() {
		return chatPanel;
	}

	public void setChatPanel(ChatPanel chatPanel) {
		this.chatPanel = chatPanel;
	}

}
