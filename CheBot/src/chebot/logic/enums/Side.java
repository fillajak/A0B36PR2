/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.enums;
/**
 *Represents side.
 * @author Dick
 */
public enum Side {
    WHITE("W"), BLACK("B");
    
    private String shrt;
    private Side(String shrt){
        this.shrt = shrt;
    }

    public String getShrt() {
        return shrt;
    }
    public Side getOponent(){
        switch(this){
            case WHITE: return BLACK;
            case BLACK: return WHITE;
        }
        return null;
    }
    
}
