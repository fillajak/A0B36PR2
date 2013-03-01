/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.Pieces;
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
    
}
