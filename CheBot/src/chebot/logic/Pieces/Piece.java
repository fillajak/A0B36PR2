/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.Pieces;

import chebot.logic.Board;
import chebot.logic.DigVec;
import chebot.logic.LogicException;
import chebot.logic.Position;
import chebot.logic.PositionList;
import chebot.logic.enums.Side;
import chebot.logic.enums.Type;
import java.util.LinkedList;
import javax.swing.border.Border;

/**
 * Represents one pieceon board. Has referece to board.
 *
 * @author Dick
 */
public abstract class Piece {

    protected Side side;
    protected Position position;
    protected Board board;
    protected boolean out;
    protected LinkedList<DigVec> simpleMoves = new LinkedList<>();
    protected boolean moved;
    protected LinkedList<Position> history = new LinkedList<>();

    public Piece(Side side, Position position, Board board) {
        this.side = side;
        this.position = position;
        this.board = board;
        this.out = false;
        this.moved = false;
    }

    public boolean isOut() {
        return out;
    }

    public void move(Position position) {
        history.add(this.position.clone());
        this.position.changePosition(position);
        moved = true;
    }

    public void undoLastMove() {
        if (history.isEmpty()) {
            throw new LogicException("cant undo", -1);
        } else {
            if (history.size() == 1) {
                moved = false;
            }
            position.changePosition(history.removeLast());
        }
    }

    public LinkedList<Position> getHistory() {
        return history;
    }

    /*  private String testMove(Position position, int index){
     Piece kicked;
     try {
     kicked = board.getPieceList().getByPosition(position);
     kicked.out = true;
     } catch (LogicException ex) {
     kicked = null;
     }
     history.add(new Move2(index, position, kicked));
     this.moved = true;
     this.position.changePosition(position);
     if (kicked == null){
     return "";
     }
     else {
     return kicked.getTag();
     }
     }*/
    /* private void undoLastMove(){
     if (history.size()<=1){
     throw new LogicException("cant undo", -1);
     }
     Move2 last = history.removeLast();
     if (last.out != null){
     last.out.out=false;
     }
     if (history.size() == 1){
     moved = false;
     }
     position.changePosition(history.getLast().to);
     }*/
    /**
     * Generates list of positions on which can this piece move. This method
     * dont check board for unwanted situations.
     *
     * @return unchecked positions to move
     */
    protected abstract PositionList getPositionsToMoveUnchecked();

    /**
     * Generates list of position, which can be taken (or give check) by this
     * piece.
     *
     * @return positions
     */
    protected PositionList getPositionsToCheck() {
        return getPositionsToMoveUnchecked();
    }

    /**
     * Gets result of getPositionsToMove() and delete moves, that would endanger
     * king of same side.
     *
     * @return checked positions to move
     */
    /*  public PositionList positionsToMove() {
     PositionList positions = getPositionsToMoveUnchecked();
     PositionList valid = new PositionList();
     for (Position p : positions.getList()) {    
     testMove(p, history.getLast().index+1);
     if (!board.getPieceList().hasCheck(side)) {
     valid.add(p);
     }
     undoLastMove();
     }

     return valid;
     }*/
    public Position getPosition() {
        return position;
    }

    /**
     * Generates available postions, where can selected piece move. Moves
     * generated by this method must be check before actual move. - check
     * positionsToMove()
     *
     * @return - positions
     */
    protected final PositionList go() {
        Position next;
        Piece piece;
        PositionList res = new PositionList();
        for (DigVec d : simpleMoves) {
            next = position.clone();
            while (true) {
                try {
                    next = next.getNextMove(d);
                    piece = board.getPieceList().getByPosition(next);
                    if (piece.side == this.side) {
                        break;
                    }
                    if (piece.side != this.side) {
                        res.add(next);
                        break;
                    }
                } catch (LogicException ex) {
                    if (ex.getCode() == LogicException.OUT_OF_FIELD_CODE) {
                        break;
                    }
                    if (ex.getCode() == LogicException.NO_PIECE_FOUND) {
                        res.add(next);
                    }
                }
                if (d.getType() == Type.JUMP) {
                    break;
                }
            }

        }
        return res;

    }

    @Override
    public final String toString() {
        return getTag() + position;
    }

    public String getTag() {
        String shrt;
        if (this instanceof Knight) {
            shrt = "N";
        } else {
            shrt = this.getClass().getSimpleName().charAt(0) + "";
        }
        return side.getShrt() + shrt;
    }

    public Side getSide() {
        return side;
    }

    public MoveList getMoves() {
        MoveList mlist = new MoveList(this);
        return mlist;
    }
}
