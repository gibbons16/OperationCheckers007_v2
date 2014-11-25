package CheckersClient007;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class HelpManual extends JFrame
{
	private JPanel helpPanel;
	
	private JLabel helpTextLabel;
	private JScrollPane helpScrollPane;
	
	private String helpText;
	
	private final File helpTextFile = new File(ClientController.class.getClassLoader().getResource("CheckersClient007/checkers_helpmanual.txt").getPath());
	private final int DEFAULT_WIDTH = 400;
	private final int DEFAULT_HEIGHT = 400;
	private final double SIZE_ADJUSTMENT = 0.68;
	
	public HelpManual()
	{
		init();
	}
	
	private void init()
	{
		this.setTitle("Help Manual");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.addComponentListener(new ComponentAdapter()
		{
				public void componentResized(ComponentEvent event)
				{
					HelpManual currentHelpManual = (HelpManual)event.getSource();
					currentHelpManual.setHtmlWidth(String.valueOf((int)(currentHelpManual.getWidth()*SIZE_ADJUSTMENT)));
				}
		});
		
		this.helpPanel = new JPanel(new BorderLayout());
		this.helpPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		
		this.helpTextLabel = new JLabel();
		this.helpTextLabel.setOpaque(true);
		this.helpTextLabel.setBackground(Color.BLACK);
		this.helpTextLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
		setFileText(String.valueOf((int)(this.getWidth()*SIZE_ADJUSTMENT)));
		
		this.helpScrollPane = new JScrollPane();
		this.helpScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.helpScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.helpScrollPane.setViewportView(this.helpTextLabel);
		this.helpPanel.add(this.helpScrollPane);
		
		this.setContentPane(this.helpScrollPane);
		this.pack();
		this.setVisible(true);
	}

	public void setFileText(String htmlWidth)
	{
		this.helpText = "";
		try
		{
			Scanner fileReader = new Scanner(helpTextFile);
			while(fileReader.hasNextLine())
			{
///*DEBUGGING*/	String nextLine = fileReader.nextLine();
//				System.out.println(nextLine);
//				fileText = fileText.concat(nextLine);
				
				this.helpText = this.helpText.concat(fileReader.nextLine());
			}
			fileReader.close();
		}
		catch(FileNotFoundException noFile)
		{
			System.out.println("ERROR: Help text manual not found!");
		}
		
		this.helpText = this.helpText.replace("[WIDTHHERE]", htmlWidth);
		this.helpTextLabel.setText(this.helpText);
	}
	
	public void setHtmlWidth(String htmlWidth)
	{
		System.out.println("Resizing to ".concat(htmlWidth));		
		// inserts the width into the html:
		this.helpText = this.helpText.replaceFirst("width:[0-9]*px", "width:".concat(htmlWidth).concat("px"));
		this.helpTextLabel.setText(this.helpText);
	}
	
}
