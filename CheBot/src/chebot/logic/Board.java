/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic;

import GUI.Adder;
import chebot.logic.Pieces.Bishop;
import chebot.logic.Pieces.King;
import chebot.logic.Pieces.Knight;
import chebot.logic.Pieces.Pawn;
import chebot.logic.Pieces.Piece;
import chebot.logic.Pieces.PieceList;
import chebot.logic.Pieces.Position;
import chebot.logic.Pieces.Queen;
import chebot.logic.Pieces.Rook;

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

    public void addPiece(Adder adder, Position position) {
        Piece piece = null;
        switch (adder.getFigure()) {
            case PAWN: {
                piece = new Pawn(adder.getSide(), position, this);
                break;
            }
            case BISHOP: {
                piece =new Bishop(adder.getSide(), position, this);
                break;
            }
            case KNIGHT: {
                piece = new Knight(adder.getSide(), position, this);
                break;
            }
            case ROOK: {
                piece = new Rook(adder.getSide(), position, this);
                break;
            }
            case QUEEN: {
                piece = new Queen(adder.getSide(), position, this);
                break;
            }
            case KING: {
                piece = new King(adder.getSide(), position, this);
                break;
            }

        }
        pieceList.addPiece(piece);
    }
}
