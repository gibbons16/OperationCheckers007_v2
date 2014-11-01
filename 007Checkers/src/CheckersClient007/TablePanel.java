package CheckersClient007;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;


public class TablePanel extends JPanel {

	private JButton createTableButton;
	private JButton joinTableButton;
	private JButton observeTableButton;
	private JTextArea chatBoxTextArea;
	private JTextArea sendMessageTextArea;
	private JButton sendMessageButton;
	private ButtonGroup chatOptionsButtonGroup; 
	private JRadioButton globalMessageRadioButton;
	private JRadioButton _PM_MessageRadioButton;
	
	/**
	 * Create the panel.
	 */
	public TablePanel() {
		
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		createTableButton = new JButton("Create Table");
		createTableButton.setBounds(0, 54, 115, 25);
		this.add(createTableButton);
		
		joinTableButton = new JButton("Join Table");
		joinTableButton.setBounds(0, 118, 115, 25);
		this.add(joinTableButton);
		
		observeTableButton = new JButton("Observe Table");
		observeTableButton.setBounds(0, 188, 115, 25);
		this.add(observeTableButton);
		

		JButton btnNewButton_2 = new JButton("Observe Table");
		btnNewButton_2.setBounds(0, 188, 115, 25);
		this.add(btnNewButton_2);
//		
//		JTextArea textArea = new JTextArea();
//		textArea.setBounds(170, 377, 612, 114);
//		this.add(textArea);
//		
//		JTextArea textArea_1 = new JTextArea();
//		textArea_1.setBounds(170, 504, 612, 25);
//		this.add(textArea_1);
//		
//		JButton btnSend = new JButton("Send");
//		btnSend.setBounds(12, 501, 109, 29);
//		this.add(btnSend);
//		
//		JRadioButton rdbtnPm = new JRadioButton("PM");
//		rdbtnPm.setBounds(614, 341, 59, 25);
//		this.add(rdbtnPm);
//		
//		JComboBox comboBox_1 = new JComboBox();
//		comboBox_1.setBounds(681, 342, 89, 22);
//		this.add(comboBox_1);
//		
//		JRadioButton rdbtnGlobal = new JRadioButton("Global");
//		rdbtnGlobal.setBounds(170, 341, 73, 25);
//		this.add(rdbtnGlobal);

		chatBoxTextArea = new JTextArea();
		chatBoxTextArea.setBounds(170, 377, 612, 114);
		this.add(chatBoxTextArea);
		
		sendMessageTextArea = new JTextArea();
		sendMessageTextArea.setBounds(170, 504, 612, 25);
		this.add(sendMessageTextArea);
		
		sendMessageButton = new JButton("Send");
		sendMessageButton.setBounds(12, 501, 109, 29);
		this.add(sendMessageButton);
		
		chatOptionsButtonGroup = new ButtonGroup();
		chatOptionsButtonGroup.add(globalMessageRadioButton);
		chatOptionsButtonGroup.add(_PM_MessageRadioButton);
		
		JRadioButton _PM_MessageRadioButton = new JRadioButton("PM");
		_PM_MessageRadioButton.setBounds(614, 341, 59, 25);
		this.add(_PM_MessageRadioButton);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(681, 342, 89, 22);
		this.add(comboBox_1);
		
		JRadioButton rdbtnGlobal = new JRadioButton("Global");
		rdbtnGlobal.setBounds(170, 341, 73, 25);
		this.add(rdbtnGlobal);

		
		String[] arr = { "Carl" , " is ", "dumb", "dumb", "dumb", "dumb", "dumb","dumb","dumb","dumb","dumb","dumb"};
		JList list = new JList(arr);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(170, 38, 288, 262);
		
		this.add(scrollPane);
		
		JLabel lblTable = new JLabel("Table");
		lblTable.setBounds(170, 17, 56, 16);
		this.add(lblTable);
		
		
		JList list_1 = new JList();
		JScrollPane scrollPane_1 = new JScrollPane(list_1);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(506, 38, 224, 262);
		this.add(scrollPane_1);
		
		
	
		JLabel lblPlayerList = new JLabel("Player List");
		lblPlayerList.setBounds(506, 13, 109, 16);
		this.add(lblPlayerList);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 10, 10);
		this.add(panel);
	}
	
	

}
