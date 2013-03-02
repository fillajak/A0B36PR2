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
public class Queen extends Piece{

    public Queen(Side side, Position position, Board board) {
        super(side, position, board);
        simpleMoves.add(new DigVec(Direction.UP));
        simpleMoves.add(new DigVec(Direction.DOWN));
        simpleMoves.add(new DigVec(Direction.LEFT));
        simpleMoves.add(new DigVec(Direction.RIGHT)); 
        simpleMoves.add(new DigVec(Direction.UP_LEFT));
        simpleMoves.add(new DigVec(Direction.UP_RIGHT));
        simpleMoves.add(new DigVec(Direction.DOWN_LEFT));
        simpleMoves.add(new DigVec(Direction.DOWN_RIGHT));
    }

    @Override
    public PositionList getPositionsToMove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
