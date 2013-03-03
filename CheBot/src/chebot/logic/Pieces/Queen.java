/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.Pieces;

import chebot.logic.PositionList;
import chebot.logic.DigVec;
import chebot.logic.Position;
import chebot.logic.enums.Side;
import chebot.logic.Board;
import chebot.logic.enums.Direction;
import chebot.logic.enums.Figure;

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
