/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.move;

import chebot.logic.Board;
import chebot.logic.Position;
import java.util.LinkedList;

/**
 *Represents special move, when multiple pieces move together in same move. Like Castle.
 * @author Dick
 */
public class Multiple extends Move {
    LinkedList<Move> others = new LinkedList<>();

    public Multiple(Position from, Position to, Board board, LinkedList<Move> others) {
        super(from, to, board);
        this.others = others;
    }

    
    @Override
    public String execute(boolean save) {
        String ret = super.execute(true);
        for (Move m: others){
            m.execute(false);
        }
        return ret+ " castle";
        
    }
      @Override
    public String reverse(boolean save) {
        String ret =  super.reverse(true);
        for (Move m: others){
            m.reverse(false);
        }  
        return ret+" castle";
        
    }

  
    
    
    
    
    
    
    
    
}
