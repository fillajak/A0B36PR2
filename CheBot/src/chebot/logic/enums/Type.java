/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chebot.logic.enums;

/**
 *Represents type of movement. See DigVect. 
 * GO - go in specified direction, until owned unit is reached or opponent unit kicked out.
 * JUMP - same as GO, but only one move is allowed
 * @author Dick
 */
public enum Type {
    GO, JUMP;
}
