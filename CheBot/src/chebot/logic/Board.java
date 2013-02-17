/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic;

import chebot.logic.Pieces.Piece;
import chebot.logic.Pieces.Position;
import java.util.ArrayList;

/**
 *
 * @author Dick
 */
public class Board  {
    
    private ArrayList<Piece> board = new ArrayList<>();
    
    public Piece findByPosition(Position position){
        for (Piece piece: board){
          if (piece.getPosition().equals(position)){
              return piece;
          }
        }
        return null;
    }
    public void placePiece(Piece piece){
       if (findByPosition(piece.getPosition()) == null){
           board.add(piece);
       }
       else{
           throw new LogicException("Piece cant be placed, this position is already occupied by:" + piece, 2);
       }
    }

    @Override
    public String toString() {
        return "Board{" + "board=" + board + '}';
    }
    
    
    
    
}
