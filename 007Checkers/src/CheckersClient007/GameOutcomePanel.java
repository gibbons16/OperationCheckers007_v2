package CheckersClient007;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Font;

public class GameOutcomePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public GameOutcomePanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblYouWinlost = new JLabel("You Win/Lost");
		lblYouWinlost.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_lblYouWinlost = new GridBagConstraints();
		gbc_lblYouWinlost.gridwidth = 2;
		gbc_lblYouWinlost.gridheight = 3;
		gbc_lblYouWinlost.fill = GridBagConstraints.VERTICAL;
		gbc_lblYouWinlost.insets = new Insets(0, 0, 5, 0);
		gbc_lblYouWinlost.gridx = 0;
		gbc_lblYouWinlost.gridy = 4;
		add(lblYouWinlost, gbc_lblYouWinlost);
		
		JButton btnNewButton = new JButton("Share to Facebook\r\n");
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setBackground(Color.BLUE);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 9;
		add(btnNewButton, gbc_btnNewButton);

	}

}
