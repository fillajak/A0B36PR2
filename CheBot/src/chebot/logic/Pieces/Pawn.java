/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.Pieces;

import chebot.logic.PositionList;
import chebot.logic.DigVec;
import chebot.logic.Position;
import chebot.logic.enums.Direction;
import chebot.logic.enums.Side;
import chebot.logic.Board;
import chebot.logic.LogicException;

/**
 *
 * @author Dick
 */
public class Pawn extends Piece {

    private DigVec go;

    public Pawn(Side side, Position position, Board board) {
        super(side, position, board);
        if (side == Side.WHITE) {
            go = new DigVec(Direction.UP);
            simpleMoves.add(new DigVec(Direction.UP_LEFT));
            simpleMoves.add(new DigVec(Direction.UP_RIGHT));
            if(position.getLine() !=2){
                moved = true;
            }
        } else {
            go = new DigVec(Direction.DOWN);
            simpleMoves.add(new DigVec(Direction.DOWN_LEFT));
            simpleMoves.add(new DigVec(Direction.DOWN_RIGHT));
             if(position.getLine() !=7){
                moved = true;
            }
        }
    }

    @Override
    protected PositionList getPositionsToCheck() {
        PositionList list = new PositionList();
        Position next;
        for (DigVec v : simpleMoves) {
            try {
            next = position.getNextMove(v);
                if (side != board.getPieceList().getByPosition(next).side) {
                    list.add(next);
                }
            } catch (LogicException e) {
            }
        }
        return list;
    }

    @Override
    public PositionList getPositionsToMoveUnchecked() {
        PositionList list = new PositionList();
        Position next;
        next = position.getNextMove(go);
        if (board.getPieceList().isFree(next)) {
            list.add(next);
            if (!moved) {
                next = next.getNextMove(go);
                if (board.getPieceList().isFree(next)) {
                    list.add(next);
                }
            }
        }
        list.addAll(getPositionsToCheck());
        return list;
    }
}
