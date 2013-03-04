/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.Pieces;

import chebot.logic.Board;
import chebot.logic.LogicException;
import chebot.logic.Pieces.Piece;
import chebot.logic.Position;
import chebot.logic.PositionList;

/**
 *
 * @author Dick
 */
public abstract class Move {

    Position from;
    Position to;
    Piece out;
    int value;
    Board board;

    public Move(Position from, Position to, Board board) {
        this.from = from.clone();
        this.to = to.clone();
        this.board = board;
        this.out = null;
        
 
    }

    protected void execute() {
        Piece p = board.getPieceList().getByPosition(from);
        try {
                out = board.getPieceList().getByPosition(to);
            } catch (LogicException ex) {
                if (ex.getCode() != LogicException.NO_PIECE_FOUND) {
                    throw ex;
                }
            }
        p.move(to);
        if (board.getPieceList().hasCheck(p.side)) {
            p.undoLastMove();
            throw new LogicException("unexecutable move", LogicException.CANT_MOVE);
        } else {
            if (out!= null){
                out.out = true;
            }
            
        }
      
    }

    

    public void reverse() {
        Piece p = board.getPieceList().getByPosition(to);
        p.undoLastMove();
        if (out != null) {
                out.out = false;
            }
    }

    public PositionList getPositionsToMove() {
        return null;
    }

    @Override
    public String toString() {
        return "Move{" + "from=" + from + ", to=" + to + ", out=" + out + '}';
    }
    
}
