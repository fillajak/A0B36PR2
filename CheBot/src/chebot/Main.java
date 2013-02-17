/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot;

import chebot.logic.Position;

/**
 *
 * @author Dick
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("test");
        Position p = new Position(Position.A, 8);
        System.out.println(p);
        p.changePosition(Position.B, 3);
        System.out.println(p);
    }
}
