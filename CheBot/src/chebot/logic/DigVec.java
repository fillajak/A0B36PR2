/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic;

import chebot.logic.enums.Direction;
import chebot.logic.enums.Type;


/**
 * Vector used to move with pieces. Like this. vector + point =  new point
 * @author Dick
 */
public class DigVec {

    private int row, line;
    private Type type;


    public DigVec(int row, int line, Type type) {
        this.row = row;
        this.line = line;
        this.type = type;
    }

    public DigVec(Direction dir, Type type) {
        this.line = dir.getLine();
        this.row = dir.getRow();
        this.type = type;

    }

    public DigVec(Direction dir) {
        this.line = dir.getLine();
        this.row = dir.getRow();
        this.type = Type.GO;
    }

    public DigVec(int row, int line) {
        this.type = Type.GO;
        this.row = row;
        this.line = line;


    }

    public int getRow() {
        return row;
    }

    public int getLine() {
        return line;
    }

    public Type getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "DigVec{" + "row=" + row + ", line=" + line + ", type=" + type + '}';
    }
    
}
