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
import chebot.logic.enums.Type;
import chebot.logic.Board;
import chebot.logic.LogicException;
import chebot.logic.move.Move;
import chebot.logic.move.MoveList;
import chebot.logic.move.Multiple;
import chebot.logic.move.Simple;
import java.util.LinkedList;

/**
 *Represents king.
 * @author Dick
 */
public class King extends Piece {

    private final Position START_BLACK = new Position(Position.E, 8);
    private final Position START_WHITE = new Position(Position.E, 1);

    public King(Side side, Position position, Board board) {
        super(side, position, board);
        value = 0;
        for (Direction dir : Direction.values()) {
            simpleMoves.add(new DigVec(dir, Type.JUMP));
        }
    }
    @Override
    public PositionList getPositionsToMoveUnchecked() {
        return go();
    }
/**
 * Check if castle is possible, if true creates associated move.
 * @return moves
 */
    @Override
    public MoveList getMoves() { 
        MoveList moves = super.getMoves();

        DigVec dir = new DigVec(Direction.RIGHT);
        boolean free;
        Position start;
        LinkedList<Integer> l = new LinkedList<>();
        l.add(2);
        l.add(3);
        if (side == Side.BLACK) {
            start = START_BLACK;
        } else {
            start = START_WHITE;
        }
        if (!this.moved && !board.getPieceList().hasCheck(side) && position.equals(start)) {
            for (Integer it : l) {
                Position next = position;
                free = true;
                for (int i = 0; i < it; i++) {
                    try {
                        next = next.getNextMove(dir);
                        if (board.getPieceList().getPositionsToCheck(side.getOponent()).conntains(next)) {
                            free = false;
                            break;
                        }
                    } catch (LogicException ex) {
                        if (ex.getCode() != LogicException.OUT_OF_FIELD_CODE) {
                            throw ex;
                        } else {
                            free = false;
                            break;
                        }
                    }
                    if (!board.getPieceList().isFree(next)) {
                        free = false;
                    }
                }
                try {
                    if (free) {
                        Position last = next.getNextMove(dir);
                        Piece rook = board.getPieceList().getByPosition(last);
                        if (!rook.moved && rook.side == side) {
                            Position to = position.getNextMove(dir).getNextMove(dir);
                            LinkedList<Move> other = new LinkedList<>();
                            other.add(new Simple(last, position.getNextMove(dir), board));
                            moves.add(new Multiple(position.clone(), to, board, other));

                        }
                    }
                } catch (LogicException ex) {
                    if (ex.getCode() != LogicException.NO_PIECE_FOUND) {
                        throw ex;
                    }
                }
                dir = new DigVec(Direction.LEFT);

            }
        }

        return moves;
    }
}
