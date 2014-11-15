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

    RED, BLACK, RED_KING, BLACK_KING;

    public int getPieceValue() {
        int retValue = 0;
        switch (this) {
            case RED:
                retValue = 1;
                break;
            case BLACK:
            	retValue = 2;
            	break;
            case RED_KING:
            	retValue = 3;
            	break;
            case BLACK_KING:
            	retValue = 4;
            	break;
            	

        }
        return retValue;
    }
}
