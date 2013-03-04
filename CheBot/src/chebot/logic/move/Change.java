/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.move;

import chebot.logic.Board;
import chebot.logic.Position;
import chebot.logic.enums.Figure;
import chebot.logic.piece.Pawn;
import chebot.logic.piece.Piece;
import chebot.logic.piece.Queen;

/**
 *Represents special move, when piece type is changing. e.g. pawn promotion to queen.
 * @author Dick
 */
public class Change extends Move{
    Figure changeTo;

    public Change(Position from, Position to, Board board, Figure changeTo) {
        super(from, to, board);
        this.changeTo = changeTo;
                
    }

    @Override
    protected String execute(boolean save) {
        Piece p = board.getPieceList().getByPosition(from);
        board.getPieceList().getLinkedList().remove(p);
        Piece changed = new Queen(p.getSide(), from, board);
        changed.setHistory(p.getHistory());
        board.getPieceList().addPiece(changed);
        return super.execute(true)+" prom.";
    }

    @Override
    public String reverse(boolean save) {
        Piece p = board.getPieceList().getByPosition(to);
        board.getPieceList().getLinkedList().remove(p);
        Piece changed = new Pawn(p.getSide(), from, board);
        changed.setHistory(p.getHistory());
        board.getPieceList().addPiece(changed);
        
        return super.reverse(true)+" prom.";
    }

    
    @Override
    public String toString() {
        return super.toString()+" change to "+changeTo;
    }
    
    
    
    
    
}
