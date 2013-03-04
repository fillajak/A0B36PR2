/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.Pieces;

import chebot.logic.LogicException;
import chebot.logic.Position;
import chebot.logic.PositionList;
import chebot.logic.enums.Side;
import java.util.LinkedList;

/**
 *
 * @author Dick
 */
public class MoveList {
   
    LinkedList<Move> moves;
    /**
     * 
     * @param start
     * @param positionList
     * @param board 
     */
    public MoveList(){
        moves = new LinkedList<>();
    }
    public MoveList(Piece start){
        moves = new LinkedList<>();
        Position pos = start.position;
        PositionList tos = start.getPositionsToMoveUnchecked();
        for(Position select: tos.getList()){
           Move tested = new Simple(pos, select, start.board);
           try{
           tested.execute();
           tested.reverse();
           moves.add(tested);
           }catch(LogicException ex){
               if(ex.getCode() != LogicException.CANT_MOVE){
                   throw ex;
               }
           }
        }

    }
    public void addMove(Move move){
        moves.add(move);
    }
    
    public PositionList getPositionsToMove(Position from){
       // System.out.println(moves);
        System.out.println("from"+from);
        PositionList pList = new PositionList();
        for (Move move: moves){
            System.out.println(move.from);
            System.out.println(move);
            if (move.from.equals(from)){

                pList.add(move.to);
            }
        }
        System.out.println(pList);
        return pList;
    }
    public PositionList getPositionsToMove(Side side){
        return null;
    }

    @Override
    public String toString() {
        return "MoveList{" + "moves=" + moves + '}';
    }
    
    
    
    
   
    
}
