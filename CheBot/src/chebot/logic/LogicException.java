/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic;

/**
 *Exception for logic.
 * @author Dick
 */
public class LogicException extends RuntimeException {
    
    public static final int OUT_OF_FIELD_CODE = 1;
    public static final int NO_PIECE_FOUND = 2;
    
    private int code;

    /**
     * Exception for package logic. 
     * codes:
     * 1 - out of field
     * 2 - placing one piece on another
     * @param message - what happend
     * @param code - unique code for each situation
     */
    public LogicException(String message, int code) {
        super(message);
         this.code = code;
    }

    /**
     * Returns unique code for each error.
     * @return code
     */
    public int getCode() {
        return code;
    }
    
    
    
    
}
