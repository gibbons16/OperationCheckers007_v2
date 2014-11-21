/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CheckersClient007;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Brian
 */
public class Board extends JPanel {

    private final int NUM_SQUARES = 8;
    private Square[][] squares;
 
    String BLACK_IMAGE = "src\\Images\\black.jpg";
    String RED_IMAGE = "src\\Images\\red.jpg";
    String RED_KING_IMAGE = "src\\Images\\redKing.jpg";
    String BLACK_KING_IMAGE = "src\\Images\\blackKing.jpg";
    private int width;
    private int height;
    // 0 - normal view  1 - flipped board view
    private boolean viewInverted;
    public static Color color1 = new Color(184, 134, 11);
    public static Color color2 = new Color(139, 69, 19);
    // 0 - no move 1 - can move
    public boolean canMove;
    
    public String player;
   

    public Board() {
        width = 800;
        height = 800;
        squares = new Square[NUM_SQUARES][NUM_SQUARES];
        
        this.setSize(width, height);
        GridLayout layout = new GridLayout(NUM_SQUARES, NUM_SQUARES);
        this.canMove = false;
        this.setLayout(layout);
        viewInverted = false;
        createBoard();
    }

    public Board(int height, int width) {

        this.height = height;
        this.width = width;
        squares = new Square[NUM_SQUARES][NUM_SQUARES];
        this.setSize(width, height);
        GridLayout layout = new GridLayout(NUM_SQUARES, NUM_SQUARES);
        this.setLayout(layout);
        viewInverted = false;
        createBoard();

    }

    private void createBoard() {
        Color squareColor;
        for (int row = 0; row < NUM_SQUARES; row++) {
            for (int col = 0; col < NUM_SQUARES; col++) {
                if ((row + col) % 2 == 0) {

                    squareColor = color1;
                } else {
                    squareColor = color2;
                }
                Square square = new Square(width / NUM_SQUARES, height / NUM_SQUARES,
                        row, col, squareColor, this);
                squares[row][col] = square;
                this.add(square);
            }
        }
        //intiliazeTest();
        
    }

    public Square[][] getAllSquares()
    {
    	return squares;
    }
    public void clearAllPieces()
    {
    	for(int i = 0; i < this.NUM_SQUARES; i++)
    	{
    		for(int j = 0; j < this.NUM_SQUARES; j++)
    		{
    			squares[i][j].setPiece(null);
    		}
    	}
    	repaint();
    }
    public void initialize()
    {
    	
    	    
    	    	for(int i = 0; i < this.NUM_SQUARES; i++)
    	    	{
    	    		for(int j = 0; j < this.NUM_SQUARES; j++)
    	    		{
    	    			if ((i + j) % 2 != 0 && i < 3) 
    	    				this.setPiece(i,j,PieceType.BLACK);
    	    			else if ((i + j) % 2 != 0 && i > 4) 
    	    				this.setPiece(i,j,PieceType.RED);
    	    		}
    	    	}
    	    repaint();
    }
    
    public void setPiece(int row, int col, PieceType type)
    {
    	row = this.translatePiecePos(row);
    	col = this.translatePiecePos(col);
    	if(row < this.NUM_SQUARES && col < this.NUM_SQUARES)
    	{
    		if(type == PieceType.BLACK)
    			squares[row][col].setPiece(new Piece( this.BLACK_IMAGE,  type,  row,  col));
    		else if(type == PieceType.RED)
    			squares[row][col].setPiece(new Piece( this.RED_IMAGE,  type,  row,  col));
    		else if (type == PieceType.BLACK_KING)
    			squares[row][col].setPiece(new Piece(this.BLACK_KING_IMAGE, type, row, col));
    		else
    			squares[row][col].setPiece(new Piece(this.RED_KING_IMAGE, type, row, col));
    	}
    	
    
    }
    public String getBlackImage()
    {
    	return this.BLACK_IMAGE;
    }
    public String getRedImage()
    {
    	return this.RED_IMAGE;
    
    }
    public boolean getMoveStatus()
    {
    	return canMove;
    }
    public void setMoveStatus(boolean moveStatus)
    {
    	canMove = moveStatus;
    }

	public String getPlayer() {
		return player;
	}

	public void setPlayerColor(String player) {
		
		this.player = player;
	}

	public static Color getColor1() {
		return color1;
	}

	public static void setColor1(Color color1) {
		Board.color1 = color1;
		ClientController.getInstance().repaintBoards();
	
	}

	public static Color getColor2() {
		return color2;
	}

	public static void setColor2(Color color2) {
		
		Board.color2 = color2;
		ClientController.getInstance().repaintBoards();
	}
	public int translatePiecePos(int pos)
	{
		
		if(this.viewInverted){
			switch(pos ){
			case 0:
				return 7;
			case 1:
				return 6;
			case 2:
				return 5;
			case 3:
				return 4;
			case 4:
				return 3;
			case 5:
				return 2;
			case 6:
				return 1;
			case 7:
				return 0;
			default:
				return -1;
			}
		}
		else
			return pos;
	}
	
	public boolean isViewInverted() {
		return viewInverted;
	}

	public void setViewInverted(boolean viewInverted) {
		this.viewInverted = viewInverted;
	}

	@Override
    protected void paintComponent(Graphics g) {
		
        super.paintComponent(g);

        for(Square[] row: squares)
        {
        	for(Square s : row )
        	{
        		s.repaint();
        	}
        }
    }//end of paintComponent method
 }
  

