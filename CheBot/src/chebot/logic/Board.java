/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic;

import chebot.logic.piece.Bishop;
import chebot.logic.enums.Figure;
import chebot.logic.piece.King;
import chebot.logic.piece.Knight;
import chebot.logic.move.Move;
import chebot.logic.piece.Pawn;
import chebot.logic.piece.Piece;
import chebot.logic.piece.PieceList;
import chebot.logic.piece.Queen;
import chebot.logic.piece.Rook;
import chebot.logic.enums.Side;
import java.util.LinkedList;
import java.util.NoSuchElementException;

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

    public void clear() {
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
}
