/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.Pieces;

/**
 *
 * @author Dick
 */
public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP_RIGHT(1, 1),
    UP_LEFT(-1, 1),
    DOWN_LEFT(-1, -1),
    DOWN_RIGHT(1, -1);
    private int row, line;

    private Direction(int row, int line) {
        this.row = row;
        this.line = line;
    }

    public int getRow() {
        return row;
    }

    public int getLine() {
        return line;
    }
}
