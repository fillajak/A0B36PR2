/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.piece;

import chebot.logic.PositionList;
import chebot.logic.DigVec;
import chebot.logic.Position;
import chebot.logic.enums.Direction;
import chebot.logic.enums.Side;
import chebot.logic.Board;
import chebot.logic.LogicException;
import chebot.logic.enums.Figure;
import chebot.logic.move.Change;
import chebot.logic.move.EnPass;
import chebot.logic.move.Move;
import chebot.logic.move.MoveList;
import chebot.logic.move.Simple;
import java.util.LinkedList;

/**
 *
 * @author Dick
 */
public class Pawn extends Piece {

    private DigVec go;
    private static final int START_LINE_WHITE = 2;
    private static final int START_LINE_BLACK = 7;

    public Pawn(Side side, Position position, Board board) {
        super(side, position, board);
        if (side == Side.WHITE) {
            go = new DigVec(Direction.UP);
            simpleMoves.add(new DigVec(Direction.UP_LEFT));
            simpleMoves.add(new DigVec(Direction.UP_RIGHT));
            if (position.getLine() != 2) {
                moved = true;
            }
        } else {
            go = new DigVec(Direction.DOWN);
            simpleMoves.add(new DigVec(Direction.DOWN_LEFT));
            simpleMoves.add(new DigVec(Direction.DOWN_RIGHT));
            if (position.getLine() != 7) {
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
        try {
            next = position.getNextMove(go);
            if (board.getPieceList().isFree(next)) {
                list.add(next);
                if (!moved && side == Side.BLACK && position.getLine() == START_LINE_BLACK ||
                        !moved && side == Side.WHITE && position.getLine() == START_LINE_WHITE) {
                    next = next.getNextMove(go);
                    if (board.getPieceList().isFree(next)) {
                        list.add(next);
                    }
                }
            }
        } catch (LogicException ex) {
        }
        list.addAll(getPositionsToCheck());
        return list;
    }

    @Override
    public MoveList getMoves() {
        MoveList moves = super.getMoves();
        switch (side) {
            case WHITE: {
                add(8, moves);
                break;
            }
            case BLACK: {
                add(1, moves);
                break;
            }
        }

        enPassant(moves);

        return moves;
    }

    public void enPassant(MoveList moves) {
        if (!board.history.isEmpty()) {
            Move last = board.history.getLast();
            Piece taken = board.getPiece(last.getTo());
            switch (side) {
                case WHITE: {
                    if (taken instanceof Pawn && taken.side != side
                            && last.getFrom().getLine() == 7 && last.getTo().getLine() == 5) {
                        Direction dir;
                        if (taken.position.getRow() < this.position.getRow()) {
                            dir = Direction.UP_LEFT;
                        } else {
                            dir = Direction.UP_RIGHT;
                        }
                        moves.add(new EnPass(position, position.getNextMove(new DigVec(dir)), board, taken));
                    }
                    break;
                }
                case BLACK: {
                    if (taken instanceof Pawn && taken.side != side
                            && last.getFrom().getLine() == 2 && last.getTo().getLine() == 4) {
                        Direction dir;
                        if (taken.position.getRow() < this.position.getRow()) {
                            dir = Direction.DOWN_LEFT;
                        } else {
                            dir = Direction.DOWN_RIGHT;
                        }
                        moves.add(new EnPass(position, position.getNextMove(new DigVec(dir)), board, taken));
                    }
                    break;
                }
            }
        }



    }

    private void add(int line, MoveList moves) {
        LinkedList<Move> rem = new LinkedList<>();
        LinkedList<Move> in = new LinkedList<>();
        for (Move m : moves.getMoves()) {
            if (m instanceof Simple && m.getTo().getLine() == line) {
                rem.add(m);
                in.add(new Change(m.getFrom(), m.getTo(), board, Figure.QUEEN));
            }
        }
        moves.getMoves().removeAll(rem);
        moves.getMoves().addAll(in);
    }
}
