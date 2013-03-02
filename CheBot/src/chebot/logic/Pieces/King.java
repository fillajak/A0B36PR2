/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.Pieces;

import chebot.logic.Board;

/**
 *
 * @author Dick
 */
public class King extends Piece{

    public King(Side side, Position position, Board board) {
        super(side, position, board);
    }

    @Override
    public PositionList getPositionsToMove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}
