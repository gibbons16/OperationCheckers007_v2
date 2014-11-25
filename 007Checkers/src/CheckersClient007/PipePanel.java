package CheckersClient007;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PipePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private final String PIPE_IMAGE_FILE_LOCATION = "Images\\pipe.gif";
	private BufferedImage pipeImage;
	public PipePanel() {
		try
		{
			pipeImage = ImageIO.read(new File(PIPE_IMAGE_FILE_LOCATION));
		}
		catch (IOException e)
		{
			pipeImage = null;
			System.out.println("ERROR: Pipe image can not be found.");
			e.printStackTrace();
		}
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		if(pipeImage != null)
		{
			g.drawImage(pipeImage, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

}
