import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JRadioButton;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;


public class ChatPanel_v2 extends JPanel {
	private JTextField sendMessageTextField;

	/**
	 * Create the panel.
	 */
	public ChatPanel_v2() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JRadioButton globalMessageRadioButton = new JRadioButton("Global");
		GridBagConstraints gbc_globalMessageRadioButton = new GridBagConstraints();
		gbc_globalMessageRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_globalMessageRadioButton.gridx = 1;
		gbc_globalMessageRadioButton.gridy = 0;
		add(globalMessageRadioButton, gbc_globalMessageRadioButton);
		
		JRadioButton _PM_MessageRadioButton = new JRadioButton("PM");
		GridBagConstraints gbc__PM_MessageRadioButton = new GridBagConstraints();
		gbc__PM_MessageRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc__PM_MessageRadioButton.gridx = 4;
		gbc__PM_MessageRadioButton.gridy = 0;
		add(_PM_MessageRadioButton, gbc__PM_MessageRadioButton);
		
		JComboBox pmTargetComboBox = new JComboBox();
		GridBagConstraints gbc_pmTargetComboBox = new GridBagConstraints();
		gbc_pmTargetComboBox.gridwidth = 3;
		gbc_pmTargetComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_pmTargetComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_pmTargetComboBox.gridx = 5;
		gbc_pmTargetComboBox.gridy = 0;
		add(pmTargetComboBox, gbc_pmTargetComboBox);
		
		JTextArea chatBoxTextArea = new JTextArea();
		GridBagConstraints gbc_chatBoxTextArea = new GridBagConstraints();
		gbc_chatBoxTextArea.gridheight = 2;
		gbc_chatBoxTextArea.gridwidth = 9;
		gbc_chatBoxTextArea.insets = new Insets(0, 0, 5, 0);
		gbc_chatBoxTextArea.fill = GridBagConstraints.BOTH;
		gbc_chatBoxTextArea.gridx = 1;
		gbc_chatBoxTextArea.gridy = 1;
		add(chatBoxTextArea, gbc_chatBoxTextArea);
		
		JButton sendMessageButton = new JButton("Send");
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

	}
}
