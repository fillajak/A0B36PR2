/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.Pieces;

import chebot.logic.*;

/**
 *
 * @author Dick
 */
public class Position {

    /**
     * A-H = 1-8 MAX, MIN = range
     */
    public static final int A = 1, B = 2, C = 3, D = 4, E = 5, F = 6, G = 7, H = 8, MIN = 1, MAX = 8;
    private int row, line; // row A-H, line 1-8

    /**
     * Represents positions on cheessboard within range [A1] to [H8]. Bottom left corner is [A1]
     *
     * exaple of contructor: Position [A3] -- Position(A, 3) or(1,3) or (1, C)
     *
     * @throws LogicException - out of field
     * @param row row A-H
     * @param line line 1-8
     */
    public Position(int row, int line) {
        checkPosition(row, line);
        this.row = row;
        this.line = line;

    }

    private boolean isIn(int value) {
        return value >= MIN && value <= MAX;
    }

    private void checkPosition(int row, int line) throws LogicException {
        if (!(isIn(row) && isIn(line))) {
            throw new LogicException("out of field [row: " + row + " line: " + line + "] possible range 1-8 inc.", 1);
        }
    }

    /**
     * Changes position on board.
     *
     * @throws LogicException - out of field
     * @param row row A-H
     * @param line line 1-8
     */
    public void changePosition(int row, int line) {
        checkPosition(row, line);
        this.row = row;
        this.line = line;
    }

    @Override
    public String toString() {
        return "[" + (char) (row - 1 + 'A')  + line + "]";
    }
    @Override
    public Position clone(){
        return new Position(this.row, this.line);
    }

    public int getRow() {
        return row;
    }

    public int getLine() {
        return line;
    }
    
}
