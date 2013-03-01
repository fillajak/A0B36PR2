/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic;

import chebot.logic.Pieces.PieceList;

/**
 *Represents chess board.
 * @author Dick
 */
public class Board  {
     private PieceList pieceList = new PieceList();

     /**
      * Contains all pieces.
      * @return all pieces in PieceList
      */
    public PieceList getPieceList() {
        return pieceList;
    }

    @Override
    public String toString() {
        return pieceList.toString();
    }
    
    
    
     
     
}
