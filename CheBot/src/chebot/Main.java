/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot;

import chebot.logic.Board;
import chebot.logic.Pieces.Knight;
import chebot.logic.Pieces.Piece;
import chebot.logic.Pieces.Position;
import chebot.logic.Pieces.Rook;
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
        b.getPieceList().addPiece(new Knight(Side.WHITE, new Position(Position.C, 3), b));
        b.getPieceList().addPiece(new Rook(Side.BLACK, new Position(Position.E, 4), b));
        System.out.println(b);
        
        
        Piece p = b.getPieceList().get(new Position(Position.C, 3));
        System.out.println(p);
        System.out.println(p.getPositionsToMove());
        
        
        Piece e = new Rook(Side.WHITE, new Position(1, 1), null);
        System.out.println(e);
        
       
     
      //  System.out.println(l);
        
        
      
        
       // System.out.println(p1.equals(p2));
        
     
        
       
    }
}
