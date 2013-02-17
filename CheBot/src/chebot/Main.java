/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot;

import chebot.logic.Board;
import chebot.logic.Pieces.Pawn;
import chebot.logic.Pieces.Piece;
import chebot.logic.Pieces.Position;
import chebot.logic.Pieces.Side;

/**
 *
 * @author Dick
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board b = new Board();
        Piece p = new Pawn(new Position(Position.A, 1), Side.WHITE);
        b.placePiece(p);
        b.placePiece(p);
        System.out.println(b);
        
       
    }
}
