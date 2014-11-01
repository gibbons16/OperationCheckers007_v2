package CheckersClient007;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class ChatPanel extends JPanel
{
	
	private GridBagLayout chatAreaLayout;
	private GridBagConstraints gridConstraints;
	
	private ButtonGroup chatBoxButtonGroup;
	private JRadioButton globalMessageRadioButton;
	private JRadioButton _PM_MessageRadioButton;
	
	private JComboBox pmTargetComboBox;
	private JButton sendMessageButton;
	private JTextArea sendMessageTextArea;
	private JTextArea chatBoxTextArea;
	
	public ChatPanel()
	{
		chatAreaLayout = new GridBagLayout();
		this.setLayout(chatAreaLayout);
		
		chatBoxButtonGroup = new ButtonGroup();
		globalMessageRadioButton = new JRadioButton("Global");
		_PM_MessageRadioButton = new JRadioButton("PM");
		chatBoxButtonGroup.add(globalMessageRadioButton);
		chatBoxButtonGroup.add(_PM_MessageRadioButton);
		
		pmTargetComboBox = new JComboBox();
		
		sendMessageButton = new JButton("Send");
		sendMessageTextArea = new JTextArea();
		chatBoxTextArea = new JTextArea();
		chatBoxTextArea.setEnabled(false);
		
		initGUI();
	}
	
	private void initGUI()
	{
		
		
	}
	
}
