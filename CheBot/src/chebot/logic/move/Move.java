/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.move;

import chebot.logic.Board;
import chebot.logic.LogicException;
import chebot.logic.Position;
import chebot.logic.piece.Piece;

/**
 *Represents move from position1 to position2. Has special offspring. Contains method execute() and reverse(),
 * which can be called in pairs. Like this: exec - rev - exec - rev
 * @author Dick
 */
public abstract class Move {
    
    protected Position from;
    protected Position to;
    protected Piece out;
    protected int value;
    protected Board board;
    protected String info;
    private boolean executed;
   
    
    public Move(Position from, Position to, Board board) {
        this.from = from.clone();
        this.to = to.clone();
        this.board = board;
        this.out = null;
        info = board.getPiece(from).toString() + " to " + to;
        if (!board.getPieceList().isFree(to)) {
            info += board.getPiece(to).getTag();
        }
        executed = false;
        
        
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }
    
    
    /**
     * Executes move. Can be executed only once. Whem method reverse() is called, can be executed again. 
     * @param save save to history - true
     * @return  move report like this: PW[A2] to [B3]BR, white pawn from A2 takes black pawn on B3
     * @throws MoveException when called twice in row, without calling reverse();
     */
    protected String execute(boolean save) {     
        if (!(this instanceof Simple)){
           // throw  new MoveException("special move", MoveException.SPECIAL);
        }
        if (executed){
            throw new MoveException("move already executed", MoveException.CANT_EXECUTE);
        }
        Piece p = board.getPieceList().getByPosition(from);
        
        try {
            out = board.getPieceList().getByPosition(to);
        } catch (LogicException ex) {
            if (ex.getCode() != LogicException.NO_PIECE_FOUND) {
                throw ex;
            }
        }
        p.move(to);
        if (board.getPieceList().hasCheck(p.getSide())) {
            p.undoLastMove();
            throw new LogicException("unexecutable move", LogicException.CANT_MOVE);
        } else {
            if (out != null) {
                out.setOut(true);
            }
            
        }
        if (save){
            board.history.add(this);
        }
        executed = true;
        return info;
        
        
    }
    /**
     * Method to find move. Unique defined by its two positons.
     * @param from starting position
     * @param to where to go.
     * @return true - same move
     */
    public boolean isSame(Position from, Position to) {
        return this.from.equals(from) && this.to.equals(to);
    }
    /**
     * Reverts move. Must be executed first. Cant be called twice, without calling execute().
     * @param save true remove move from history
     * @return move report like this: PW[A2] to [B3]BR, white pawn from A2 takes black pawn on B3
     * @throws MoveException when move wasnt executed first
     */
    public String reverse(boolean save) {
        if(!executed){
            throw new MoveException("cant be reversed, wasnt executed", MoveException.CANT_REVERSE);
        }
        Piece p = board.getPieceList().getByPosition(to);
        p.undoLastMove();
        if (out != null) {
            out.setOut(false);
        }
       if (save){
           board.history.removeLast();
       }
        executed = false;
        return info;
    }
 
    @Override
    public String toString() {
        return info;
    }
}
