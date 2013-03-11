/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic;

import chebot.logic.enums.Figure;
import chebot.logic.piece.Knight;
import chebot.logic.move.Move;
import chebot.logic.piece.Pawn;
import chebot.logic.piece.Piece;
import chebot.logic.piece.PieceList;
import chebot.logic.piece.Queen;
import chebot.logic.piece.Rook;
import chebot.logic.enums.Side;
import chebot.logic.move.MoveList;
import chebot.logic.piece.Bishop;
import chebot.logic.piece.King;
import java.util.LinkedList;

/**
 * Represents chess board.
 *
 * @author Dick
 */
public class Board {

    private PieceList pieceList = new PieceList();
    public LinkedList<Move> history = new LinkedList<>();
    

    /**
     * Contains all pieces.
     *
     * @return all pieces in PieceList
     */
    public PieceList getPieceList() {
        return pieceList;
    }

    @Override
    public String toString() {
        return pieceList.toString();
    }

    public final void clear() {
        pieceList.getLinkedList().clear();
        history.clear();
    }

    public String move(Position from, Position to) {
        String s = (history.size() + 1) + ". " + pieceList.getByPosition(from).getMoves().executeMove(from, to);
        return s;

    }

    public String undoLast() {
        Move m = history.getLast();
        return "undo: " + m.reverse(true);

    }

    public Piece getPiece(Position position) {
        return pieceList.getByPosition(position);
    }

    /**
     * Adds piece to board.
     *
     * @param figure type of piece PAWN, ROOK ...
     * @param side side WHITE or BLACK
     * @param position position on board
     */
    public void addPiece(Figure figure, Side side, Position position) {
        Piece piece = null;
        switch (figure) {
            case PAWN: {
                piece = new Pawn(side, position, this);
                break;
            }
            case BISHOP: {
                piece = new Bishop(side, position, this);
                break;
            }
            case KNIGHT: {
                piece = new Knight(side, position, this);
                break;
            }
            case ROOK: {
                piece = new Rook(side, position, this);
                break;
            }
            case QUEEN: {
                piece = new Queen(side, position, this);
                break;
            }
            case KING: {
                piece = new King(side, position, this);
                break;
            }

        }
        pieceList.addPiece(piece);
    }

    /**
     * Statistatical evaluating function.
     *
     * @param side evaluated side
     * @return value of board
     */
    public int evaluateBoard(Side side) { //jenom figurky
        int ret = 0;
    
        for (Piece p : pieceList.getLinkedList()) {
            if (p.getSide() == side) {
                ret += p.getValue();
                
            }
            else{
                ret-= p.getValue();
                
            }
        }
        return ret;
    }
    
}
