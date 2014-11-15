package CheckersClient007;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ObserveFrame extends JFrame {

	private JPanel contentPane;
	private int tid;
	private Board board;
	public ObserveFrame()
	{
		tid = -1;
		init();
	}
	public ObserveFrame(int tid) {
		this.tid = tid;
		init();
	}
	private void init()
	{
		
		this.setTitle("Observing Table: " + tid );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		board = new Board();
		contentPane.add(board, BorderLayout.CENTER);
	}
	public Board getBoard()
	{
		return board;
	}
	public int getTID()
	{
		return tid;
	}

}
