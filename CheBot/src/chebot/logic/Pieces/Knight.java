/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.Pieces;

import chebot.logic.PositionList;
import chebot.logic.DigVec;
import chebot.logic.Position;
import chebot.logic.enums.Side;
import chebot.logic.enums.Type;
import chebot.logic.Board;

/**
 *
 * @author Dick
 */
public class Knight extends Piece {

    public Knight(Side side, Position position, Board board) {
        super(side, position, board);
        simpleMoves.add(new DigVec(2, 1, Type.JUMP));
        simpleMoves.add(new DigVec(2, -1, Type.JUMP));

        simpleMoves.add(new DigVec(-2, 1, Type.JUMP));
        simpleMoves.add(new DigVec(-2, -1, Type.JUMP));

        simpleMoves.add(new DigVec(-1, -2, Type.JUMP));
        simpleMoves.add(new DigVec(1, -2, Type.JUMP));

        simpleMoves.add(new DigVec(1, 2, Type.JUMP));
        simpleMoves.add(new DigVec(-1, 2, Type.JUMP));


    }

    @Override
    public PositionList getPositionsToMove() {
        return go();
    }
}
