/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.move;

import chebot.logic.LogicException;
import chebot.logic.Position;
import chebot.logic.PositionList;
import chebot.logic.enums.Side;
import chebot.logic.piece.Piece;
import chebot.logic.piece.Piece;
import java.util.LinkedList;

/**
 * Contains possible moves.
 *
 * @author Dick
 */
public class MoveList {

    protected LinkedList<Move> moves;

    public MoveList() {
        moves = new LinkedList<>();
    }

    /**
     * Creates list of moves, that can be played by selected player. These moves
     * are checked.
     *
     * @param start
     */
    public MoveList(Piece start) {
        moves = new LinkedList<>();
        Position pos = start.getPosition();
        PositionList tos = start.getPositionsToMoveUnchecked();
        for (Position select : tos.getList()) {
            Move tested = new Simple(pos, select, start.getBoard());
            try {
                tested.execute(true);
                tested.reverse(true);
                moves.add(tested);
            } catch (LogicException ex) {
                if (ex.getCode() != LogicException.CANT_MOVE) {
                    throw ex;
                }
            }
        }

    }

    public LinkedList<Move> getMoves() {
        return moves;
    }

    public void add(Move move) {
        try {
            move.execute(true);       
            move.reverse(true);
            moves.add(move);
        } catch (LogicException ex) {
            if (ex.getCode() != LogicException.CANT_MOVE) {
                throw ex;
            }
        }

    }
    public void addAll(MoveList moveList){
        for (Move m: moveList.getMoves()){
            this.add(m);
        }
    }

    /**
     * Executes move from its list. See execute in Move.
     *
     * @param from start
     * @param to finish
     * @return information about move
     */
    public String executeMove(Position from, Position to) {
        for (Move m : this.moves) {
            if (m.isSame(from, to)) {
                return m.execute(true);

            }
        }
        throw new LogicException("cant exec. move", LogicException.CANT_MOVE);
    }

    /**
     * Adds move.
     *
     * @param move move to add
     */
    public void addMove(Move move) {
        moves.add(move);
    }

    /**
     * Generates positions on which can piece from selected position move.
     *
     * @param from selected position
     * @return positions
     */
    public PositionList getPositionsToMove(Position from) {
        PositionList pList = new PositionList();
        for (Move move : moves) {

            if (move.from.equals(from)) {

                pList.add(move.to);
            }
        }
        return pList;
    }

    public PositionList getPositionsToMove(Side side) {
        throw new UnsupportedOperationException("not supported");
    }

    @Override
    public String toString() {
        return "MoveList{" + "moves=" + moves + '}';
    }
}
