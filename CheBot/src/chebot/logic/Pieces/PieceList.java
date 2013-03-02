/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.Pieces;

import chebot.logic.LogicException;
import java.util.LinkedList;

/**
 *
 * @author Dick
 */
public class PieceList{
    private LinkedList<Piece> list = new LinkedList<>();
    
   /**
    * Gets piece from list on defined position. Takes first one. Ignores pieces with out == true;
    * @param position
    * @throws LogicException - if no piece is found
    * @return piece
    */
   public Piece getByPosition(Position position){
       for (Piece p: list){
           if (p.getPosition().equals(position) && !p.out){
               return p;
           }
       }
       throw new LogicException("no pieces found on position: " +position, LogicException.NO_PIECE_FOUND);
   }
   /**
    * Adds single piece to list.
    * @return true if no pieces of same position was found, false otherwise. 
    */
   public boolean addPiece(Piece piece){
      try{
          getByPosition(piece.getPosition());
      }catch(LogicException ex){
          if (ex.getCode() == LogicException.NO_PIECE_FOUND){
              list.add(piece);
              return true;
          }
      }
      
      return true;
   }
   
  public LinkedList<Piece> getLinkedList(){
      return list;
  }

    @Override
    public String toString() {
        return "PieceList{" + "list=" + list + '}';
    }
   public boolean isFree(Position position){
       try{
           getByPosition(position);
       }catch(LogicException ex){
           if (ex.getCode() == LogicException.NO_PIECE_FOUND){
               return true;
           }
       }
       return false;
   }
   
    
}
