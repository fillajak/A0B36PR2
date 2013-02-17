/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.Pieces;

import chebot.logic.Move;
import java.util.LinkedList;

/**
 *
 * @author Dick
 */
public class Pawn extends Piece{

    public Pawn(Position position, Side side) {
       super.position = position;
       super.side = side;
       super.out = false;
    }

    @Override
    public LinkedList<Move> getMoves() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    

    
    
    
}
