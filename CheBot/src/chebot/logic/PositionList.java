/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic;
import chebot.logic.Position;
import java.util.LinkedList;

/**
 * List of potions on board.
 * @author Dick
 */
public class PositionList {

    private LinkedList<Position> list = new LinkedList<>();

    /**
     * Adds position to list, if allready contains same position retunrns false,
     * and dont add another instance.
     *
     * @param position - postion to add
     * @return false - list contains specified position, true - potion addded
     */
    public boolean add(Position position) {
        if (conntains(position)) {
            return false;
        }
        list.add(position);
        return true;

    }

    /**
     * Contains postion? x1 = x2 and y1 = y2
     *
     * @param position - position
     * @return true - contains, false - otherwise
     */
    public boolean conntains(Position position) {
        for (Position p : list) {
            if (p.equals(position)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add all non existing postion to list. uses add(Position)
     *
     * @param in - position to add
     */
    public void addAll(PositionList in) {
        for (Position p : in.list) {
            add(p);
        }
    }
    
    public Position get(int i){
        return list.get(i);
    }
    public int size(){
        return list.size();
    }

    @Override
    public String toString() {
        return "PositionList{" + "list=" + list + '}';
    }
}
