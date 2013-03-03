/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import chebot.logic.Pieces.Piece;
import chebot.logic.Pieces.PieceList;
import java.util.LinkedList;

/**
 *
 * @author Dick
 */
public class PieceListGUI extends LinkedList<PieceGUI> {
    
    public void paintPiece(Piece piece){
        get(piece.getPosition().getLinePosition()).paintFromPiece(piece);
    }
    
    public void paintField(PieceList pieces){
      eraseAll();
      for (Piece p: pieces.getLinkedList()){
          if(!p.isOut()){
              paintPiece(p);
          }
      }
    }
    public void eraseAll(){
        for (int i = 0;i<this.size();i++){
            get(i).paintFromPiece(null);
        }
    }
}

