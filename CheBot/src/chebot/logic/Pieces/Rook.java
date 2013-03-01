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
public class Rook extends Piece{
    

    public Rook(Side side, Position position, Board board) {
        super(side, position, board);
        simpleMoves.add(new DigVec(Direction.UP));
        simpleMoves.add(new DigVec(Direction.DOWN));
        simpleMoves.add(new DigVec(Direction.LEFT));
        simpleMoves.add(new DigVec(Direction.RIGHT)); 
    }

    @Override
    public PositionList getPositionsToMove() {
        return go();
    }

    @Override
    public String toString() {
        return "R"+super.toString();
    }
    
    

 
    
    
    
}
