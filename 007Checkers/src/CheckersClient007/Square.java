/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CheckersClient007;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Brian
 */
public class Square extends JPanel implements MouseListener {

	String DEFAULT_BLACK_IMAGE = "Images/balck.jpg";
	
    Piece piece;
    static Square selectedSquare;
    static Square pressed;
    private boolean available;
    private int xCoor;
    private int yCoor;
    private Board board;
    public Square() {
    }

    public Square(int width, int height, int xCoor, int yCoor, Color setColor, Board board) {

        this.addMouseListener(this);
        this.setSize(width, height);
        this.setBackground(setColor);
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        this.board = board;
        available = false;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getxCoor() {
        return xCoor;
    }

    public void setxCoor(int xCoor) {
        this.xCoor = xCoor;
    }

    public int getyCoor() {
        return yCoor;
    }

    public void setyCoor(int yCoor) {
        this.yCoor = yCoor;
    }

    public Piece getPiece() {

        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void reinitialize() {
        available = true;
    }
    public Color getColor()
    {
    	return this.getBackground();
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        Square s = (Square) (me.getSource());
        pressed = s;
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    	
        Square newSelectedSquare = (Square) (me.getSource());
        // check to see if the square where the mouse was originally pressed is
        // the same as the square where the mouse is released
        if (newSelectedSquare.equals(pressed) && board.getMoveStatus()) {
            // change the square the is selected 
            if (Square.selectedSquare != null) {
                if ((selectedSquare.getxCoor() + selectedSquare.getyCoor()) % 2 == 0) {

                    selectedSquare.setBackground(Board.color1);
                } else {
                    selectedSquare.setBackground(Board.color2);
                }

                Piece tempPiece = selectedSquare.getPiece();
                if (tempPiece != null && tempPiece.getType() == board.getPlayer() &&   !newSelectedSquare.equals(selectedSquare)) {

                    if (newSelectedSquare.getPiece() != null) {
                        if (!selectedSquare.getBackground().equals(newSelectedSquare.getBackground())) {

//                            newSelectedSquare.setPiece(tempPiece);
//                            selectedSquare.setPiece(null);
                        	
                        	ClientController.getInstance().move(selectedSquare.getxCoor(),
                        			selectedSquare.getyCoor(), newSelectedSquare.getxCoor(), newSelectedSquare.getyCoor());
                        }
                        
                    } else {
//                        newSelectedSquare.setPiece(tempPiece);
//                        selectedSquare.setPiece(null);
                    	
                    	ClientController.getInstance().move(selectedSquare.getxCoor(),
                    			selectedSquare.getyCoor(), newSelectedSquare.getxCoor(), newSelectedSquare.getyCoor());
                    }
                }


            }

            this.setBackground(Color.GRAY);

            selectedSquare = this;
            repaint();

        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        // set the currently selected  pressed square to null
        pressed = null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //g.setColor(Color.black);
        if (piece != null) {
            piece.paintIcon(this, g, 5, 5);
            

        }

    }//end of paintComponent method
}
