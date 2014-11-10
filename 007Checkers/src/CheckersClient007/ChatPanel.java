package CheckersClient007;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class ChatPanel extends JPanel
{
	
	private GridBagLayout chatAreaLayout;
	private GridBagConstraints gridConstraints;
	
	private ButtonGroup chatBoxButtonGroup;
	private JRadioButton globalMessageRadioButton;
	private JRadioButton _PM_MessageRadioButton;
	
	private JComboBox<String> pmTargetComboBox;
	private JButton sendMessageButton;
	private JTextField sendMessageTextField;
	private JTextArea chatBoxTextArea;
	private static ChatPanel chatPanel;
	private  ChatPanel()
	{
		
		
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		globalMessageRadioButton = new JRadioButton("Global");
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
		
		pmTargetComboBox = new JComboBox<>();
		GridBagConstraints gbc_pmTargetComboBox = new GridBagConstraints();
		gbc_pmTargetComboBox.gridwidth = 3;
		gbc_pmTargetComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_pmTargetComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_pmTargetComboBox.gridx = 5;
		gbc_pmTargetComboBox.gridy = 0;
		add(pmTargetComboBox, gbc_pmTargetComboBox);
		
	    chatBoxTextArea = new JTextArea();
		chatBoxTextArea.setEnabled(false);
		GridBagConstraints gbc_chatBoxTextArea = new GridBagConstraints();
		gbc_chatBoxTextArea.gridheight = 2;
		gbc_chatBoxTextArea.gridwidth = 9;
		gbc_chatBoxTextArea.insets = new Insets(0, 0, 5, 0);
		gbc_chatBoxTextArea.fill = GridBagConstraints.BOTH;
		gbc_chatBoxTextArea.gridx = 1;
		gbc_chatBoxTextArea.gridy = 1;
		add(chatBoxTextArea, gbc_chatBoxTextArea);
		
	    sendMessageButton = new JButton("Send");
	    sendMessageButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClientController.getInstance().sendGlobalMessage(sendMessageTextField.getText());
				
			}
	    	
	    });
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
		globalMessageRadioButton = new JRadioButton("Global");
		_PM_MessageRadioButton = new JRadioButton("PM");
		chatBoxButtonGroup.add(globalMessageRadioButton);
		chatBoxButtonGroup.add(_PM_MessageRadioButton);
		
	}
	public static ChatPanel getInstance()
	{
		if(chatPanel == null)
		{
			chatPanel  = new ChatPanel();
		}
		return chatPanel;
	}
	public void addNewMessage(String msg, String who, boolean global )
	{
		// global - true update msg globally
		if(global)
		{
			chatBoxTextArea.append(who + ": " + msg );
		}
		else
		{
			SimpleAttributeSet green = new SimpleAttributeSet();
			StyleConstants.setFontFamily(green, "Courier New Italic");
			StyleConstants.setForeground(green, Color.GREEN);
			try {
				chatBoxTextArea.getDocument().insertString(0, "(PM) "+ who + ": " + msg, green);
			} catch (BadLocationException e) {
				
				
			}
		}
	}
	
		
}
