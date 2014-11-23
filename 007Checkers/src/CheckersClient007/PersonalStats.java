package CheckersClient007;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTextArea;


public class PersonalStats extends JFrame {

	/**
	 * Create the panel.
	 */
	JTextArea statsArea;
	public PersonalStats(String userName) {
		super();
		this.setTitle("Personal Stats: " + userName);
		statsArea = new JTextArea();
		statsArea.setEnabled(false);
		this.getContentPane().add(statsArea);
		statsArea.setBackground(Color.BLACK);
		statsArea.setForeground(Color.GREEN);

	}
	public void setStats(String[] stats)
	{
		
		statsArea.setText("");
		for(String s : stats)
		{
			statsArea.append(s);
			statsArea.append("\n");
		}
		
	}
	public JTextArea getStats()
	{
		return statsArea;
	}
	
}
