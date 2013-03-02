/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import chebot.logic.Game;
import chebot.logic.Position;
import chebot.logic.PositionList;
import chebot.logic.enums.Figure;
import chebot.logic.enums.Side;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;



/**
 *  Zobrazení pole.
 * @author Dick
 */
public class BoardGUI extends JFrame {
    public PieceListGUI gui_list = new PieceListGUI();
    private JLabel l;
    public static final int TOTAL_SIZE = 64;
    public static final int ROW = 8;
    private static final Dimension SIZE = new Dimension(480,480);
    private Game game;
    private JPanel game_panel, mainPanel;
    private JMenuBar menuBar;
    private JMenuItem exit, newGame, undo, showMove;
    private JMenu gameMenu, editMenu;
    private JTextArea history;
    private JScrollPane historyPane;
    public JPopupMenu popUp;
    
   

    public BoardGUI(Game game) {
        this.game = game;
        super.setTitle("MagicChess");
       // this.setSize(SIZE);
        Dimension d = new Dimension(800, 600);
     //   this.setLocation((int)((d.getWidth()-SIZE.getWidth())/2), (int)((d.getHeight()-SIZE.getHeight())/2));
        this.setSize(d);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
        
        mainPanel = new JPanel(new FlowLayout());
        
        game_panel = new JPanel(new GridLayout(10,10,0,0));
        game_panel.setPreferredSize(SIZE);
        
        menuBar = new JMenuBar();
        gameMenu = new JMenu("Game");
        editMenu = new JMenu("Edit");
        
        
        showMove = new JCheckBoxMenuItem("Show moves", true);
        showMove.addActionListener(game);
        exit = new JMenuItem("Exit");
        exit.addActionListener(game);
        

        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
        newGame = new JMenuItem("New game");
        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        newGame.addActionListener(game);
        
        undo = new JMenuItem("Undo");
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK));
        undo.addActionListener(game);
        editMenu.add(undo);
        
        gameMenu.add(newGame);
        gameMenu.add(exit);
        gameMenu.add(showMove);
        menuBar.add(gameMenu);
        
        menuBar.add(editMenu);
        history = new JTextArea();
     //   history.setLineWrap(true);
        history.setEditable(false);
        d = new Dimension(SIZE.width/2, SIZE.height);
       // history.setPreferredSize(d);
        history.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        history.setText("...starting new game\n");
        historyPane = new JScrollPane(history);
        historyPane.setPreferredSize(d);
        historyPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        historyPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      //  DefaultCaret caret = (DefaultCaret)history.getCaret();
      //  caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        init();
        this.add(menuBar, BorderLayout.NORTH);
        mainPanel.add(game_panel);
        mainPanel.add(historyPane);
        
        this.add(mainPanel, BorderLayout.CENTER);
        
    // this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        popUp = new JPopupMenu();
        JMenu white = new JMenu("white");
        JMenu black = new JMenu("black");
        
      //  JMenuItem rook = new JMenuItem("rook");
        for (Figure f: Figure.values()){
            Adder adder = new Adder(f, Side.WHITE, f.name());
            adder.addActionListener(game);
            white.add(adder);
            adder = new Adder(f, Side.BLACK, f.name());
            adder.addActionListener(game);
            black.add(adder);
        }
       
        popUp.add(white);
        popUp.add(black);
        
        
       // JMenuItem rook = new JMenuItem("rook");
       
       
   
             
       //    rook.addActionListener(game);
           
           
           
           
           
    

    }
      public void paintAllSelected(PositionList positionList, Selection s){
         if (positionList == null) {
              return;
          }
        for (int a = 0;a<positionList.size();a++){
            paintSelected(positionList.get(a), s);
        }
        repaint();
     }
      public void paintSelected(Position position, Selection s){
         int i = position.getLinePosition();
         this.gui_list.get(i).select(s);
 
     }
    public void paintField(){
       gui_list.paintField(game.board.getPieceList());
       
    }
    public void setHistoryWrap(String s){
        history.append(s+"\n");
        history.setCaretPosition(history.getDocument().getLength());
    }
     public void setHistoryWrapFirstWrap(String s){
        history.append("\n"+s+"\n");
        history.setCaretPosition(history.getDocument().getLength());
    }
    public void setHistoryWrapFirstNoWrap(String s){
        history.append("\n"+s);
        history.setCaretPosition(history.getDocument().getLength());
    }
     public void setHistoryNoWrap(String s){
        history.append(s);
        history.setCaretPosition(history.getDocument().getLength());
    }
     public void clearHistory(){
         history.setText(null);
     }
    private void init(){
        l = new JLabel();
        game_panel.add(l);
             l.setHorizontalAlignment(JLabel.CENTER);
          for (int i = 0;i<8;i++){
              int x = i+65;
              
             l = new JLabel((char)x+"");
             l.setHorizontalAlignment(JLabel.CENTER);
            game_panel.add(l);
          }
             l = new JLabel();
        game_panel.add(l);
          for (int j = 0;j<BoardGUI.ROW;j++){
                  l = new JLabel(8-j+"");
                  l.setHorizontalAlignment(JLabel.CENTER);
        game_panel.add(l);
         for(int i =0; i<BoardGUI.ROW; i++){ 
             
               PieceGUI b = new PieceGUI();
               b.paintback(i%2==0&j%2!=0 |i%2!=0& j%2==0);
               gui_list.add(b);
               b.addMouseListener(this.game);
               game_panel.add(b);        
        }
          l = new JLabel(8-j+"");
                  l.setHorizontalAlignment(JLabel.CENTER);
        game_panel.add(l);
          }
             l = new JLabel();
        game_panel.add(l);
              for (int i = 0;i<8;i++){
               int x = i+65;
              
             l = new JLabel((char)x+"");
             l.setHorizontalAlignment(JLabel.CENTER);
             game_panel.add(l);
          }
                  l = new JLabel();
        game_panel.add(l);
    }

 
  }
     
  
  


   
    
    

