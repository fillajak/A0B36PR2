/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import chebot.logic.Pieces.Bishop;
import chebot.logic.Pieces.King;
import chebot.logic.Pieces.Knight;
import chebot.logic.Pieces.Pawn;
import chebot.logic.Pieces.Piece;
import chebot.logic.Pieces.Queen;
import chebot.logic.Pieces.Rook;
import chebot.logic.enums.Side;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import javax.swing.JPanel;





/**
 *
 * @author Dick
 */
public class PieceGUI extends JPanel{
    
    
    Image back, front, sel;
    Image field_white, field_black,pawn_black, pawn_white, rook_white, rook_black, bishop_white, bishop_black,
            knight_white, knight_black, queen_white, queen_black, king_white, king_black, normal, special, blackSel;
    boolean select = false;
    boolean paintback = false;
    boolean blackBack = false;
    int sels = 2;
    int offs = 2;
    public PieceGUI() {
        MediaTracker tracker = new MediaTracker(this);
        blackSel = Toolkit.getDefaultToolkit().getImage("res/blackSel.png");
        tracker.addImage(blackSel, -2); 
        normal = Toolkit.getDefaultToolkit().getImage("res/select.png");
        tracker.addImage(normal, -1);
        special = Toolkit.getDefaultToolkit().getImage("res/special.png");
        tracker.addImage(special, 0);
        field_white = Toolkit.getDefaultToolkit().getImage("res/white.png");
        tracker.addImage(field_white, 1);
        field_black = Toolkit.getDefaultToolkit().getImage("res/black.png");
        tracker.addImage(field_black, 2);
        pawn_white = Toolkit.getDefaultToolkit().getImage("res/pawn_white.png");
        tracker.addImage(pawn_white, 3);
        pawn_black = Toolkit.getDefaultToolkit().getImage("res/pawn_black.png");
        tracker.addImage(pawn_black, 4);
        rook_black = Toolkit.getDefaultToolkit().getImage("res/rook_black.png");
        tracker.addImage(rook_black, 5);
        rook_white =  Toolkit.getDefaultToolkit().getImage("res/rook_white.png");
        tracker.addImage(rook_white, 6);
        bishop_white = Toolkit.getDefaultToolkit().getImage("res/bishop_white.png");
        tracker.addImage(bishop_white, 7);
        bishop_black = Toolkit.getDefaultToolkit().getImage("res/bishop_black.png");
        tracker.addImage(bishop_black, 8);
        knight_black = Toolkit.getDefaultToolkit().getImage("res/knight_black.png");
        tracker.addImage(king_black, 9);
        knight_white = Toolkit.getDefaultToolkit().getImage("res/knight_white.png");
        tracker.addImage(king_white, 10);
        queen_black = Toolkit.getDefaultToolkit().getImage("res/queen_black.png");
        tracker.addImage(queen_black, 11);
        queen_white = Toolkit.getDefaultToolkit().getImage("res/queen_white.png");
        tracker.addImage(queen_white, 12);
        king_black = Toolkit.getDefaultToolkit().getImage("res/king_black.png");
        tracker.addImage(king_black, 13);
        king_white = Toolkit.getDefaultToolkit().getImage("res/king_white.png");
        tracker.addImage(king_white, 14);
        try{
            tracker.waitForAll();
        }catch(InterruptedException e){
            System.out.println("interupted");
        }

         
        back =null;
        front = null;
        sel = null;

    }
    @Override
   public void paintComponent(Graphics g){
        g.drawImage(back,0,0,getWidth(),getHeight(), this);
       if (select){
       g.setColor(Color.red);
        g.drawImage(sel, 0, 0,getWidth(), getHeight(),this);
        select = false; 
       }
        g.drawImage(front,0,0,getWidth(),getHeight(), this);  
    }
   
 
    public void paintback(boolean black){
        if (black){
            back = field_black;
        }
        else{
            back = field_white;
        }
        
        paintback = true;
        repaint();
    }
 
    public void paintFromPiece(Piece piece){ // funguje jenom pawn
           if (piece == null){
               front = null;
           }
           if (piece instanceof Pawn){
                    if (piece.getSide() == Side.BLACK) {
                   front = pawn_black;
               }
                    else {
                   front = pawn_white;
               }  
           }
           if (piece instanceof Rook){
                    if (piece.getSide() == Side.BLACK) {
                   front = rook_black;
               } 
                    else {
                   front = rook_white;
               }      
           }                       
           if (piece instanceof Bishop){
                    if (piece.getSide() == Side.BLACK) {
                   front = bishop_black;
               }
                    else {
                   front = bishop_white;
               }
           }
           
           if (piece instanceof Knight){
                    if (piece.getSide() == Side.BLACK) {
                   front = knight_black;
               }
                    else {
                   front = knight_white;
               }
           }
           if (piece instanceof Queen){
                    if (piece.getSide() == Side.BLACK) {
                   front = queen_black;
               }
                    else {
                   front = queen_white;
               }
           }
           
           if (piece instanceof King){
                    if (piece.getSide() == Side.BLACK) {
                   front = king_black;
               }
                    else {
                   front = king_white;
               }
           }

            
                repaint();
            
            }
                
        
    public void select(Selection s){
        switch(s){
            case RED: {
                sel = normal;
                break;
            }
            case BLUE: {
                sel = special;
                break;
            }
            case BLACK:{
                sel = blackSel;
            }   break;
                
        }
        select = true;
        repaint();
    }
    }

        

            
    

