/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CheckersClient007;

/**
 *
 * @author Brian
 */
public enum PieceType {

    RED, BLACK;

    public int getPieceValue() {
        int retValue = 0;
        switch (this) {
            case RED:
                retValue = 1;
                break;
            case BLACK:
            	retValue = 2;
            	break;
            	

        }
        return retValue;
    }
}
