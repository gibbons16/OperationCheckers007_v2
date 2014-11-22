package CheckersClient007;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.JScrollPane;

public class ChatPanel extends JPanel
{
	
	private GridBagLayout chatAreaLayout;
	private GridBagConstraints gridConstraints;
	
	private BufferedImage backgroundImage;
	private final String BACKGROUND_IMAGE_FILE_LOCATION = "src\\Images\\metal007background.jpg";
	private final String EXECUTION_IMAGE_FILE_LOCATION = "src\\Images\\executebutton.png";
	private final String EXECUTION_CLICKED_IMAGE_FILE_LOCATION = "src\\Images\\executebutton_clicked.png";
	
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
		try
		{
			backgroundImage = ImageIO.read(new File(BACKGROUND_IMAGE_FILE_LOCATION));
		}
		catch (IOException e)
		{
			backgroundImage = null;
			System.out.println("ERROR: Chat image background not found.");
			e.printStackTrace();
		}
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		canPM = true;
		canGlobalMessage = true;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[] {37, 234, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		globalMessageRadioButton = new JRadioButton("Global");
		globalMessageRadioButton.setBackground(new Color(0,49,0,255)); // dark green
		globalMessageRadioButton.setForeground(new Color(0,255,0,255)); // light green
		globalMessageRadioButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.LIGHT_GRAY));
		globalMessageRadioButton.setSelected(true);;
		GridBagConstraints gbc_globalMessageRadioButton = new GridBagConstraints();
		gbc_globalMessageRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_globalMessageRadioButton.gridx = 1;
		gbc_globalMessageRadioButton.gridy = 0;
		add(globalMessageRadioButton, gbc_globalMessageRadioButton);
		
		_PM_MessageRadioButton = new JRadioButton("PM");
		_PM_MessageRadioButton.setBackground(new Color(0,49,0,255)); // dark green
		_PM_MessageRadioButton.setForeground(new Color(0,255,0,255)); // light green
		_PM_MessageRadioButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.LIGHT_GRAY));
		GridBagConstraints gbc__PM_MessageRadioButton = new GridBagConstraints();
		gbc__PM_MessageRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc__PM_MessageRadioButton.gridx = 4;
		gbc__PM_MessageRadioButton.gridy = 0;
		add(_PM_MessageRadioButton, gbc__PM_MessageRadioButton);
		
		pmTextField = new JTextField();
		pmTextField.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.LIGHT_GRAY));
		pmTextField.setCaretColor(Color.GREEN);
		pmTextField.setBackground(Color.BLACK);
		pmTextField.setForeground(Color.GREEN);
		GridBagConstraints gbc_pmTextField = new GridBagConstraints();
		gbc_pmTextField.gridwidth = 3;
		gbc_pmTextField.insets = new Insets(0, 0, 5, 5);
		gbc_pmTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_pmTextField.gridx = 5;
		gbc_pmTextField.gridy = 0;
		add(pmTextField, gbc_pmTextField);
		
	    sendMessageButton = new JButton("", new ImageIcon(EXECUTION_IMAGE_FILE_LOCATION));
	    //sendMessageButton.setSize(65, 30);
	    sendMessageButton.setBorder(new EmptyBorder(0,0,0,0));
	    sendMessageButton.setPressedIcon(new ImageIcon(EXECUTION_CLICKED_IMAGE_FILE_LOCATION));
	    sendMessageButton.addActionListener(new ActionListener()
	    {
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
		scrollPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.LIGHT_GRAY));
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
		chatBoxTextArea.setBackground(Color.BLACK);
		chatBoxTextArea.setForeground(Color.GREEN);
		scrollPane.setViewportView(chatBoxTextArea);
		GridBagConstraints gbc_sendMessageButton = new GridBagConstraints();
		gbc_sendMessageButton.insets = new Insets(0, 0, 0, 5);
		gbc_sendMessageButton.gridx = 0;
		gbc_sendMessageButton.gridy = 3;
		add(sendMessageButton, gbc_sendMessageButton);
		
		sendMessageTextField = new JTextField();
		sendMessageTextField.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.LIGHT_GRAY));
		sendMessageTextField.setCaretColor(Color.GREEN);
		sendMessageTextField.setBackground(Color.BLACK);
		sendMessageTextField.setForeground(Color.GREEN);
		GridBagConstraints gbc_sendMessageTextField = new GridBagConstraints();
		gbc_sendMessageTextField.gridwidth = 9;
		gbc_sendMessageTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_sendMessageTextField.gridx = 1;
		gbc_sendMessageTextField.gridy = 3;
		add(sendMessageTextField, gbc_sendMessageTextField);
		sendMessageTextField.setColumns(10);
		sendMessageTextField.addKeyListener(
	            new KeyListener(){
	            	
					@Override
					public void keyPressed(KeyEvent arg0)
					{
						if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
							if(globalMessageRadioButton.isSelected() && canGlobalMessage)
								ClientController.getInstance().sendGlobalMessage(sendMessageTextField.getText());
							else if(_PM_MessageRadioButton.isSelected() && canPM)
							{
								ClientController.getInstance().sendMessage(pmTextField.getText(), sendMessageTextField.getText());
							}
							sendMessageTextField.setText("");
	                    }   
					}

					@Override
					public void keyReleased(KeyEvent arg0)
					{
					}

					@Override
					public void keyTyped(KeyEvent arg0)
					{
					}
	            }
	        );

		
		
		// set group
		chatBoxButtonGroup = new ButtonGroup();
		chatBoxButtonGroup.add(globalMessageRadioButton);
		chatBoxButtonGroup.add(_PM_MessageRadioButton);
		
		
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		if(backgroundImage != null)
		{
			g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
		}
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
		JScrollBar vertical = scrollPane.getVerticalScrollBar();
		vertical.setValue( vertical.getMaximum() );
	}

	public void addNewMessage(String msg, String who, boolean global )
	{
		if(global && canGlobalMessage)
		{
			chatBoxTextArea.append(getFormattedLocalTime() + " " + who + ": " + msg + "\n" );
		}
		else if(!global && canPM)
		{
			chatBoxTextArea.append(getFormattedLocalTime().concat(" [PM] ").concat(who).concat(": ").concat(msg).concat("\n"));
		}
		JScrollBar vertical = scrollPane.getVerticalScrollBar();
		vertical.setValue( vertical.getMaximum() );
	}
	
	public void addNewSystemMessage(String msg)
	{
		chatBoxTextArea.append(getFormattedLocalTime().concat(" [System Message] ").concat(msg).concat("\n"));
	}
	
	public String getFormattedLocalTime()
	{
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		int minute = Calendar.getInstance().get(Calendar.MINUTE);
		int second = Calendar.getInstance().get(Calendar.SECOND);
		
		return "["
				.concat(hour < 10 ? "0" : "").concat(String.valueOf(hour))
				.concat(":")
				.concat(minute < 10 ? "0" : "").concat(String.valueOf(minute))
				.concat(":")
				.concat(second < 10 ? "0" : "").concat(String.valueOf(second))
				.concat("]");
	}
	
		
}
