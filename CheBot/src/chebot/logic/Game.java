/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic;

import chebot.logic.piece.Piece;
import chebot.logic.piece.Rook;
import gui.Adder;
import gui.BoardGUI;
import gui.Selection;
import chebot.logic.enums.Side;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.NoSuchElementException;
import javax.swing.JMenuItem;

/**
 * Represents game, move with pieces. Implements action and mouse listeners.
 *
 * @author Dick
 */
public class Game implements ActionListener, MouseListener {

    public Board board;
    private BoardGUI gui;
    private boolean setPiece = false;
    private Position position;
    private boolean showMoves = true;
    private Side side;

    public Game() {
        gui = new BoardGUI(this);
        gui.setVisible(true);
        board = new Board();
        setGame();
    }

    private void setGame() {
        gui.paintField();


    }

    public void turn(Position pos) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JMenuItem) {
            JMenuItem m = (JMenuItem) e.getSource();
            if ("New game".equals(m.getActionCommand())) {
                board.clear();
                gui.paintField();
            }
            if ("Exit".equals(m.getActionCommand())) {
                System.out.println("show possible moves");
                //   gui.paintAllSelected(board.getPieceList().getAllMoves(Side.BLACK), Selection.RED);
            }
            if ("Undo".equals(m.getActionCommand())) {
                try {
                    gui.setHistoryWrap(board.undoLast());
                } catch (NoSuchElementException ex) {
                    // gui.undo.setEnabled(false);
                }

                setPiece = false;
                gui.paintField();

            }
            if ("Status".equals(m.getActionCommand())) {
              //  System.out.println(board.getBestMove(Side.WHITE));
              //  gui.paintField();
                    gui.setHistoryWrap(
                 "status of black? " + board.getPieceList().getStatus(Side.BLACK));

               // gui.setHistoryWrap("value: " + board.evaluateBoard(Side.WHITE) + "");
               // System.out.println(board.getPieceList().getAllMoves(Side.WHITE));
                /*   for (Move mov: board.getPieceList().getAllPositionToMove(Side.WHITE)){
                    
                 }*/
            }
            if ("playWhite".equals(m.getActionCommand())) {
                gui.setHistoryWrap("radnom move from White\n"+board.playBestMove(Side.WHITE,1));
                
                gui.paintField();
            }
            if ("playBlack".equals(m.getActionCommand())) {
                gui.setHistoryWrap("radnom move from Black\n "+board.playRandom(Side.BLACK));
                
                gui.paintField();
            }

        }
        if (e.getSource() instanceof Adder) {
            Adder a = (Adder) e.getSource();
            board.addPiece(a.getFigure(), a.getSide(), position);
            gui.paintField();

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            Position pos = Position.getPositionFromLine(gui.gui_list.indexOf(e.getSource()));
            gui.popUp.show(e.getComponent(), e.getX(), e.getY());
            position = pos;
        }
        if (e.getButton() == MouseEvent.BUTTON1) {
            gui.paintField();
            Position pos = Position.getPositionFromLine(gui.gui_list.indexOf(e.getSource()));
            gui.paintSelected(pos, Selection.BLUE);
            if (!setPiece) {
                if (!board.getPieceList().isFree(pos)) {
                    PositionList test = board.getPieceList().getByPosition(pos).getMoves().getPositionsToMove(pos);
                    gui.paintAllSelected(test, Selection.RED);


                    position = pos;
                    System.out.println(board.getPieceList().getByPosition(position).getMoves());
                    setPiece = true;
                }
            } else {

                try {
                    gui.setHistoryWrap(board.move(position, pos));

                } catch (LogicException ex) {
                    if (ex.getCode() != LogicException.CANT_MOVE) {
                        throw ex;
                    }
                }
                setPiece = false;
                gui.paintField();

            }

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        gui.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

    }
}