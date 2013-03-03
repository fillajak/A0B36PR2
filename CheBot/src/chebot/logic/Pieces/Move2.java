/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.Pieces;

import chebot.logic.Position;
import java.util.logging.Logger;

/**
 *
 * @author Dick
 */
public class Move2 {
    
    int index;
    Position to;
    Piece out;

    public Move2(int index, Position to, Piece out) {
        this.index = index;
        this.to = to.clone();
        this.out = out;
    }
   
    
    
    
    
}
