/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.move;

import chebot.logic.Board;
import chebot.logic.Position;
import chebot.logic.piece.Piece;

/**
 *Represents special move, when piece kick out piece, which is not on position on which selected piece go.
 * @author Dick
 */
public class EnPass extends Move{

    private Piece out2;
    public EnPass(Position from, Position to, Board board, Piece out2) {
        super(from, to, board);
        this.out2 = out2;
        this.execute(true);
        this.reverse(true);
    }

    @Override
    public final String execute(boolean save) {
       String s  = super.execute(save);
       out2.setOut(true);
       return s+"e.p.";
    }

    @Override
    public final String reverse(boolean save) {
        String s =  super.reverse(save);
        out2.setOut(false);
        return s+"e.p.";
    }

  
    
    
    
    
}
