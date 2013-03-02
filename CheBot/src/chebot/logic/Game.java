/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic;

import GUI.Adder;
import GUI.FieldGUI;
import GUI.Selection;
import chebot.logic.Pieces.Position;
import chebot.logic.Pieces.PositionList;
import chebot.logic.Pieces.Rook;
import chebot.logic.Pieces.Side;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;

/**
 *
 * @author Dick
 */
public class Game implements ActionListener, MouseListener {

    public Board board;
    private FieldGUI gui;
    private boolean setPiece = false;
    private Position position;
    private boolean showMoves = true;
    private Side side;

    public Game() {
        gui = new FieldGUI(this);
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
        
        if(e.getSource() instanceof JMenuItem){
            JMenuItem m = (JMenuItem) e.getSource();
            if ("New game".equals(m.getActionCommand())){
                board.clear();
                gui.paintField();
            }
        }
        if (e.getSource() instanceof Adder){
            Adder a = (Adder)e.getSource();
            board.addPiece(a.getFigure(),a.getSide(), position);
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
            PositionList test = board.getPieceList().getByPosition(pos).getPositionsToMove();
            gui.paintAllSelected(test, Selection.RED);
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