/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.move;

/**
 *Movement exception.
 * @author Dick
 */
public class MoveException extends RuntimeException{
  
    public static final int CANT_EXECUTE =1;
    public static final int CANT_REVERSE=2;
    public static final int CANT_MOVE =3;
    public static final int SPECIAL = 4;
  
    
    
    private int code;

 
    public MoveException(String message, int code) {
        super(message);
         this.code = code;
    }

   
    public int getCode() {
        return code;
    }   
}
