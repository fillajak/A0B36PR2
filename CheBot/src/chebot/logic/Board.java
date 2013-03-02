/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic;

import chebot.logic.Pieces.Bishop;
import chebot.logic.enums.Figure;
import chebot.logic.Pieces.King;
import chebot.logic.Pieces.Knight;
import chebot.logic.Pieces.Pawn;
import chebot.logic.Pieces.Piece;
import chebot.logic.Pieces.PieceList;
import chebot.logic.Pieces.Queen;
import chebot.logic.Pieces.Rook;
import chebot.logic.enums.Side;

/**
 * Represents chess board.
 *
 * @author Dick
 */
public class Board {

    private PieceList pieceList = new PieceList();

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
    public void clear(){
        pieceList.getLinkedList().clear();
    }

    public void addPiece(Figure figure, Side side, Position position) {
        Piece piece = null;
        switch (figure) {
            case PAWN: {
                piece = new Pawn(side, position, this);
                break;
            }
            case BISHOP: {
                piece =new Bishop(side, position, this);
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
