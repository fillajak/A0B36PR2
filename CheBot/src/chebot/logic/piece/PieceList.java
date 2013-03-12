/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.piece;

import chebot.logic.LogicException;
import chebot.logic.Position;
import chebot.logic.PositionList;
import chebot.logic.Status;
import chebot.logic.enums.Side;
import chebot.logic.move.Move;
import chebot.logic.move.MoveList;
import java.util.LinkedList;

/**
 * Represents list of pieces.
 *
 * @author Dick
 */
public class PieceList {

    private LinkedList<Piece> list = new LinkedList<>();

    /**
     * Gets piece from list on defined position. Applies on pieces in game.
     *
     * @param position position
     * @return piece
     */
    public Piece getByPosition(Position position) {
        return getByPosition(position, true);
    }

    /**
     * Gets piece from list on defined position. Applies on kicked out pieces.
     *
     * @param position position
     * @return piece
     */
    public Piece getByPositionOut(Position position) {
        return getByPosition(position, false);
    }

    /**
     * Gets piece from list on defined position. Takes first one.
     *
     * @param position position
     * @param excludeDisabled true - takes pieces in game, false - takes pieces
     * kicked out
     * @return piece
     */
    public Piece getByPosition(Position position, boolean excludeDisabled) {
        for (Piece p : list) {
            if (excludeDisabled) {
                if (p.getPosition().equals(position) && !p.out) {
                    return p;
                }
            } else {
                if (p.getPosition().equals(position) && p.out) {
                    return p;
                }
            }
        }
        throw new LogicException("no pieces found on position: " + position, LogicException.NO_PIECE_FOUND);

    }

    /**
     * Adds single piece to list.
     *
     * @return true if no pieces of same position was found, false otherwise.
     */
    public boolean addPiece(Piece piece) {
        try {
            getByPosition(piece.getPosition());
        } catch (LogicException ex) {
            if (ex.getCode() == LogicException.NO_PIECE_FOUND) {
                list.add(piece);
                return true;
            }
        }
        return true;
    }

    /**
     * Determinates, which positions are endagered from specified side.
     *
     * @param side endangering side
     * @return list of positions endangered
     */
    public PositionList getPositionsToCheck(Side side) {
        PositionList ret = new PositionList();
        for (Piece p : this.list) {
            if (p.side == side && !p.out) {
                ret.addAll(p.getPositionsToCheck());
            }
        }
        return ret;
    }

    /**
     * Determinates check.
     *
     * @param side side in check
     * @return true - check
     */
    public boolean hasCheck(Side side) {
        for (Position p : getPositionsToCheck(side.getOponent()).getList()) {
            try {
                if (this.getByPosition(p).side == side && this.getByPosition(p) instanceof King) {
                    return true;
                }
            } catch (LogicException ex) {
            }
        }
        return false;
    }

    public Piece getKing(Side side) {
        for (Piece p : this.list) {
            if (p instanceof King && p.side == side) {
                return p;
            }
        }
        throw new LogicException("no king found", -1);
    }

    /**
     * Gets all moves by, which van be executed by selected side.
     *
     * @param side side that moves
     * @return all moves
     */
    public PositionList getAllPositionToMove(Side side) {
        PositionList ret = new PositionList();
        for (Piece p : this.list) {
            if (p.side == side) {
                ret.addAll(p.getMoves().getPositionsToMove(p.position));
            }
        }
        return ret;
    }

    public LinkedList<Move> getAllMoves(Side side) {
        LinkedList<Move> moves = new LinkedList<>();
        for (Piece p : list) {
            if (side == p.side) {
                moves.addAll(p.getMoves().getMoves());
            }
        }
        return moves;

    }

    /**
     * Returns status of field.
     *
     * @param side supervised side
     * @return TIE, CHECK, CHECK_MATE, NORMAL - nothing prev.
     */
    public Status getStatus(Side side) {
        if (!hasCheck(side)) {
            if (getAllPositionToMove(side).getList().isEmpty()) {
                return Status.TIE;
            } else {
                return Status.NORMAL;
            }

        } else {
            if (getAllPositionToMove(side).getList().isEmpty()) {
                return Status.CHECK_MATE;
            } else {
                return Status.CHECK;
            }
        }

    }

    public LinkedList<Piece> getLinkedList() {
        return list;
    }

    @Override
    public String toString() {
        return "PieceList{" + "list=" + list + '}';
    }

    /**
     * Check position for Piece.
     *
     * @param position position, which is free
     * @return true - free, false - occupied
     */
    public boolean isFree(Position position) {
        try {
            getByPosition(position);
        } catch (LogicException ex) {
            if (ex.getCode() == LogicException.NO_PIECE_FOUND) {
                return true;
            }
        }
        return false;
    }
}