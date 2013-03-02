/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import chebot.logic.Pieces.Figure;
import chebot.logic.Pieces.Side;
import javax.swing.JMenuItem;

/**
 *
 * @author Dick
 */
public class Adder extends JMenuItem{
    private Figure figure;
    private Side side;

    public Adder(Figure figure, Side side, String text) {
        super(text);
        this.figure = figure;
        this.side = side;
    }

    public Figure getFigure() {
        return figure;
    }

    public Side getSide() {
        return side;
    }
    
    
    
    
    
}
