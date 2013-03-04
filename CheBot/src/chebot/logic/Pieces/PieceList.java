/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.Pieces;

import chebot.logic.LogicException;
import chebot.logic.Position;
import chebot.logic.PositionList;
import chebot.logic.Status;
import chebot.logic.enums.Side;
import java.util.LinkedList;

/**
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
  /*  public void undoMovesWithHighestNumber(){
        int max = 0;
        for(Piece p : this.list){
            if (p.history.getLast().index>max){
                max = p.history.getLast().index;
            }
        }
        for(Piece p: this.list){
            if(p.history.getLast().index == max){
                p.undoLastMove();
            }
        }
       
       
    }*/

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
   
    public Piece getKing(Side side){
        for(Piece p: this.list){
            if (p instanceof King && p.side == side){
                return p;
            }
        }
        throw new LogicException("no king found", -1);
    }
   /* public PositionList getAllMoves(Side side){
        PositionList ret = new PositionList();
        for (Piece p: this.list){
            if (p.side == side){
                ret.addAll(p.positionsToMove());
            }
        }
        return ret;
    }*/
   /* public Status getStatus(Side side){
        if (!hasCheck(side)){
            if (getAllMoves(side).getList().isEmpty()){
                return Status.TIE;
            }
            else{
                return Status.NORMAL;
            }
            
        }
        else{
            if (getAllMoves(side).getList().isEmpty()){
                return Status.CHECK_MATE;
            }
            else{
                return Status.CHECK;
            }
        }
        
    }*/
    

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