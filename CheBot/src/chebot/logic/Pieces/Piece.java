/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.Pieces;

import chebot.logic.Move;
import chebot.logic.Pieces.Piece;
import java.util.LinkedList;

/**
 *
 * @author Dick
 */
public abstract class Piece {
    protected Side side;
    protected Position position;
    //list s tahy
    //list se zvlastnimi tahy
    //pole
    protected boolean out;
    public abstract LinkedList<Move> getMoves();
    
    
    public Position getPosition(){
        return position;
    }

    @Override
    public String toString() {
        return "Piece{" + "side=" + side + ", position=" + position + ", out=" + out + '}';
    }
    
    
    
    
}
