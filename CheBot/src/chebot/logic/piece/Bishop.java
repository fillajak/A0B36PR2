/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.piece;

import chebot.logic.PositionList;
import chebot.logic.DigVec;
import chebot.logic.Position;
import chebot.logic.enums.Direction;
import chebot.logic.enums.Side;
import chebot.logic.Board;

/**
 *Represents bishop.
 * @author Dick
 */
public class Bishop extends Piece{
    
    public Bishop(Side side, Position position, Board board) {
        super(side, position, board);
        simpleMoves.add(new DigVec(Direction.UP_LEFT));
        simpleMoves.add(new DigVec(Direction.UP_RIGHT));
        simpleMoves.add(new DigVec(Direction.DOWN_LEFT));
        simpleMoves.add(new DigVec(Direction.DOWN_RIGHT));
    }

    @Override
    public PositionList getPositionsToMoveUnchecked() {
       return go();
    }
    
    
}
