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
        for (Direction dir: Direction.values()){
            simpleMoves.add(new DigVec(dir));
        }
    }

    @Override
    public PositionList getPositionsToMove() {
        return go();
    }
    
    
}
