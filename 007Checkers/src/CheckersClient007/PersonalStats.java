package CheckersClient007;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.GridLayout;

import javax.swing.JLabel;

public class PersonalStats extends JPanel {

	/**
	 * Create the panel.
	 */
	JTextArea statsArea;
	public PersonalStats() {
		
		
		statsArea = new JTextArea();
		statsArea.setEnabled(false);
		this.add(statsArea);
		

	}
	public void setStats(String[] stats)
	{
		statsArea.setText("");
		for(String s : stats)
		{
			statsArea.append(s);
		}
	}
	public JTextArea getStats()
	{
		return statsArea;
	}
	
}
