/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.Pieces;

import chebot.logic.PositionList;
import chebot.logic.DigVec;
import chebot.logic.Position;
import chebot.logic.enums.Direction;
import chebot.logic.enums.Side;
import chebot.logic.enums.Type;
import chebot.logic.Board;
import chebot.logic.LogicException;
import java.util.LinkedList;
import javax.swing.border.Border;

/**
 *
 * @author Dick
 */
public class King extends Piece {

    public King(Side side, Position position, Board board) {
        super(side, position, board);
        for (Direction dir : Direction.values()) {
            simpleMoves.add(new DigVec(dir, Type.JUMP));
        }
    }

    @Override
    public PositionList getPositionsToMoveUnchecked() {
        PositionList positionList = go();
        DigVec dir = new DigVec(Direction.RIGHT);
        boolean free;
        boolean castle = false;
        LinkedList<Integer> l = new LinkedList<>();
        l.add(2);
        l.add(3);
        if (!this.moved && !board.getPieceList().hasCheck(side)) {
            for (Integer it : l) {
                Position next = position;
                free = true;
                for (int i = 0; i < it; i++) {
                    next = next.getNextMove(dir);
                    if (!board.getPieceList().isFree(next)) {
                        free = false;
                    }
                }
                try{
                if (free) {
                    Position last = next.getNextMove(dir);
                    Piece rook = board.getPieceList().getByPosition(last);
                    if (!rook.moved && rook.side == side) {
                        positionList.add(position.getNextMove(dir).getNextMove(dir));
                        castle = true;
                    }
                }}catch(LogicException ex){
                    if (ex.getCode() != LogicException.NO_PIECE_FOUND){
                        throw ex;
                    }
                }
                dir = new DigVec(Direction.LEFT);

            }
        }
     
        return positionList;
    }


}
