/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CheckersClient007;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Brian
 */
public  class Piece extends ImageIcon{

   
    private PieceType type;
    private int row;
    private int col;


    public Piece(String image){
        super(image);
    }
    public Piece(String image, PieceType type, int row, int col) {
        super(image);
        this.type = type;
        this.row = row;
        this.col = col;
    }
    
    public PieceType getType() {
        
        return type;
    }

    public void setType(PieceType type) {
        this.type = type;
    }


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
    public int getTypeValue(){
        return type.getPieceValue();
    }
        @Override
    public void paintIcon( Component c, Graphics g, int x, int y ) {
        g.drawImage(getImage(), x, y, c.getWidth()-10, c.getHeight()-10, c);
    }

    //public abstract boolean moveTo(Square sqaure);
}
