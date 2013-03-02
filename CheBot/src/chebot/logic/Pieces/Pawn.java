/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.Pieces;

import chebot.logic.Board;
import chebot.logic.LogicException;

/**
 *
 * @author Dick
 */
public class Pawn extends Piece {

    public Pawn(Side side, Position position, Board board) {
        super(side, position, board);
    }

    @Override
    public PositionList getPositionsToMove() {
        PositionList list = new PositionList();
        Position next;
        DigVec go;
        if (side == Side.WHITE) {
            go = new DigVec(Direction.UP);
            simpleMoves.add(new DigVec(Direction.UP_LEFT));
            simpleMoves.add(new DigVec(Direction.UP_RIGHT));
        } else {
            go = new DigVec(Direction.DOWN);
            simpleMoves.add(new DigVec(Direction.DOWN_LEFT));
            simpleMoves.add(new DigVec(Direction.DOWN_RIGHT));
        }
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
        for (DigVec v : simpleMoves) {
            next = position.getNextMove(v);
            try {
                if (side != board.getPieceList().getByPosition(next).side) {
                    list.add(next);
                }
            } catch (LogicException e) {
               
            }
        }




        return list;
    }
}
