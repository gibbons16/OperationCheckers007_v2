package CheckersClient007;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.JScrollPane;

public class ChatPanel extends JPanel
{
	
	private GridBagLayout chatAreaLayout;
	private GridBagConstraints gridConstraints;
	
	private ButtonGroup chatBoxButtonGroup;
	private JRadioButton globalMessageRadioButton;
	private JRadioButton _PM_MessageRadioButton;
	
	private JTextField pmTextField;
	private JButton sendMessageButton;
	private JTextField sendMessageTextField;
	private JScrollPane scrollPane;
	private JTextArea chatBoxTextArea;
	
	private boolean canPM;
	private boolean canGlobalMessage;
	
	public  ChatPanel()
	{
		canPM = true;
		canGlobalMessage = true;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[] {37, 234, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		globalMessageRadioButton = new JRadioButton("Global");
		globalMessageRadioButton.setSelected(true);;
		GridBagConstraints gbc_globalMessageRadioButton = new GridBagConstraints();
		gbc_globalMessageRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_globalMessageRadioButton.gridx = 1;
		gbc_globalMessageRadioButton.gridy = 0;
		add(globalMessageRadioButton, gbc_globalMessageRadioButton);
		
		_PM_MessageRadioButton = new JRadioButton("PM");
		GridBagConstraints gbc__PM_MessageRadioButton = new GridBagConstraints();
		gbc__PM_MessageRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc__PM_MessageRadioButton.gridx = 4;
		gbc__PM_MessageRadioButton.gridy = 0;
		add(_PM_MessageRadioButton, gbc__PM_MessageRadioButton);
		
		pmTextField = new JTextField();
		GridBagConstraints gbc_pmTextField = new GridBagConstraints();
		gbc_pmTextField.gridwidth = 3;
		gbc_pmTextField.insets = new Insets(0, 0, 5, 5);
		gbc_pmTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_pmTextField.gridx = 5;
		gbc_pmTextField.gridy = 0;
		add(pmTextField, gbc_pmTextField);
		
	    sendMessageButton = new JButton("Send");
	    sendMessageButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(globalMessageRadioButton.isSelected() && canGlobalMessage)
					ClientController.getInstance().sendGlobalMessage(sendMessageTextField.getText());
				else if(_PM_MessageRadioButton.isSelected() && canPM)
				{
					ClientController.getInstance().sendMessage(pmTextField.getText(), sendMessageTextField.getText());
				}
				sendMessageTextField.setText("");
			}
	    	
	    });
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 9;
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		chatBoxTextArea = new JTextArea();
		chatBoxTextArea.setEditable(false);
		scrollPane.setViewportView(chatBoxTextArea);
		GridBagConstraints gbc_sendMessageButton = new GridBagConstraints();
		gbc_sendMessageButton.insets = new Insets(0, 0, 0, 5);
		gbc_sendMessageButton.gridx = 0;
		gbc_sendMessageButton.gridy = 3;
		add(sendMessageButton, gbc_sendMessageButton);
		
		sendMessageTextField = new JTextField();
		GridBagConstraints gbc_sendMessageTextField = new GridBagConstraints();
		gbc_sendMessageTextField.gridwidth = 9;
		gbc_sendMessageTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_sendMessageTextField.gridx = 1;
		gbc_sendMessageTextField.gridy = 3;
		add(sendMessageTextField, gbc_sendMessageTextField);
		sendMessageTextField.setColumns(10);

		
		
		// set group
		chatBoxButtonGroup = new ButtonGroup();
		chatBoxButtonGroup.add(globalMessageRadioButton);
		chatBoxButtonGroup.add(_PM_MessageRadioButton);
		
		
	}
	
	public void setCanPM(boolean canPM)
	{
		this.canPM = canPM;
		this._PM_MessageRadioButton.setEnabled(canPM);
		if(!canPM)
		{
			this._PM_MessageRadioButton.setSelected(canPM);
		}
	}
	
	public void setCanGlobalMessage(boolean canGlobalMessage)
	{
		this.canGlobalMessage = canGlobalMessage;
		this.globalMessageRadioButton.setEnabled(canGlobalMessage);
		if(!canGlobalMessage)
		{
			this.globalMessageRadioButton.setSelected(canGlobalMessage);
		}
	}

	public void addNewMessage(String msg, String who, boolean global )
	{
		if(global && canGlobalMessage)
		{
			chatBoxTextArea.append(who + ": " + msg + "\n" );
		}
		else if(!global && canPM)
		{
			chatBoxTextArea.append("[PM] ".concat(who).concat(": ").concat(msg).concat("\n"));
		}
	}
	
		
}
