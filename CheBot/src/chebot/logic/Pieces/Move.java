/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.Pieces;

import chebot.logic.Board;
import chebot.logic.Pieces.Piece;
import chebot.logic.Position;

/**
 *
 * @author Dick
 */
public class Move {//osetrit nezadouci stavy -mozna uplne predelat
    Position from;
    Position to;
    Piece out;
    Board board;
    int number;
    int globalNumber;
    
    public Move(Position from, Position to, Board board) {
        this.from = from.clone();
        this.to = to.clone();
        this.board = board;
        this.globalNumber = board.getGlobalNumber();
        number = 0;
    }
    
  
    
   public void execute(){
       Piece moving = board.getPieceList().getByPosition(from);
       moving.moved = true;
       if (!board.getPieceList().isFree(to) && board.getPieceList().getByPosition(to).getSide() !=moving.getSide()){
           out = board.getPieceList().getByPosition(to);
           this.out.out = true;
       }
       else{
           out =null;
       }
       moving.getPosition().changePosition(to);
       number++;
       
       
   }
   public void reverse(){
       Piece back = board.getPieceList().getByPosition(to);
       back.position.changePosition(from);
       if (out!=null){
           board.getPieceList().getByPositionOut(to).out = false;
       }
       if (--number == 0){
           back.moved = false;
       } 
   }

    @Override
    public String toString() {
        return "Move from "+ from +" to "+ to +", globalNumber = " +globalNumber+ " out = "+ out;
    }
   
   
    
    
    

    
    
    
    
    
}
