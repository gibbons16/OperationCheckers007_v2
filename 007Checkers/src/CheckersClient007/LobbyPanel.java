package CheckersClient007;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JScrollPane;

import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;


public class LobbyPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	JLabel TableLbl;
	JLabel playerListlbl;
	JButton createTableBtn;
	JList tableList;
	JTextArea playerListTextArea;
	JButton btnJoinTable;
	JButton btnObserveTable ;
	
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
		playerListTextArea.setEnabled(false);
		playerListScrollPane.setViewportView(playerListTextArea);
		
		btnJoinTable = new JButton("Join Table");
		GridBagConstraints gbc_btnJoinTable = new GridBagConstraints();
		gbc_btnJoinTable.insets = new Insets(0, 0, 5, 5);
		gbc_btnJoinTable.gridx = 0;
		gbc_btnJoinTable.gridy = 2;
		add(btnJoinTable, gbc_btnJoinTable);
		
		btnObserveTable = new JButton("Observe Table");
		GridBagConstraints gbc_btnObserveTable = new GridBagConstraints();
		gbc_btnObserveTable.insets = new Insets(0, 0, 5, 5);
		gbc_btnObserveTable.gridx = 0;
		gbc_btnObserveTable.gridy = 3;
		add(btnObserveTable, gbc_btnObserveTable);
		
		ChatPanel panel = ChatPanel.getInstance();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 4;
		add(panel, gbc_panel);

	}
	public void setPlayerList(String[] players)
	{
		playerListTextArea.setText("");
		for(String s: players)
		{
			playerListTextArea.append(s +"\n");
		}
	
	}
	public void setTableList(String[] tables)
	{
        DefaultListModel listModel = (DefaultListModel) tableList.getModel();
        listModel.removeAllElements();
        
        for(int i = 0; i < tables.length; i++)
        {
        	listModel.addElement(tables[i]);
        	
        }
        
	}

}
