/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic;

/**
 *Represents status of game. NORMAL-CHECK-CHECK_MATE-TIE
 * @author Dick
 */
public enum Status { 
    NORMAL(0),
    CHECK(0), CHECK_MATE(100000), TIE(0);   
    
    private int value;
    private Status(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
}
