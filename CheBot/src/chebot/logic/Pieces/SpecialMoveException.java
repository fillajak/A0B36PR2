/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.Pieces;

/**
 *
 * @author Dick
 */
public class SpecialMoveException extends RuntimeException{
    
       private int code;

   
    public SpecialMoveException(String message, int code) {
        super(message);
        this.code = code;
    }

    
    
  
    public int getCode() {
        return code;
    }
    
    
}
