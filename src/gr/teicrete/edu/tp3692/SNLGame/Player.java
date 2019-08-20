
package gr.teicrete.edu.tp3692.SNLGame;

import gr.teicrete.edu.tp3692.icons.PlayerIcons;
import javax.swing.ImageIcon;

/**
 * Creates a player
 * @author tp3692
 */
public class Player {

    private int currentPosition;
    private PlayerIcons pawns;
    private int pawnSelect;
    private boolean backwards;
    private boolean turtle;
    public String name;
    public boolean pwrAffected;
    
    
    public Player() {

        this.currentPosition = 1;
        this.turtle = false;
        this.backwards = false;
        this.pawns = new PlayerIcons();
        this.name = "Unamed";
        this.pwrAffected = false;
        this.pawnSelect = 0;

    }

    /**
     *
     * @return the current position of player on the board
     */
    public int getCurrentPosition() {
        return currentPosition;
    }

    /**
     *
     * @param currentPosition sets the current position of player on the board
     */
    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     *
     * @return boolean so that you know if the player got in a turtle square
     */
    public boolean isTurtle() {
        return turtle;
    }

    /**
     *
     * @param turtle sets the boolean for turtle square
     */
    public void setTurtle(boolean turtle) {
        this.turtle = turtle;
    }

    /**
     *
     * @return returns a list with the available pawns
     */
    public PlayerIcons getPawns() {
        return pawns;
    }

    /**
     *
     * @return boolean so that you know if the player got in a reverse movement square
     */
    public boolean isBackwards() {
        return backwards;
    }

    /**
     *
     * @param backwards boolean to set the backwards square
     */
    public void setBackwards(boolean backwards) {
        this.backwards = backwards;
    }

    /**
     *
     * @return returns the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name sets the name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return return an integer that equals the array list element of the available pawns
     */
    public int getPawnSelect() {
        return pawnSelect;
    }

    /**
     *
     * @param pawnSelect
     */
    public void setPawnSelect(int pawnSelect) {
        this.pawnSelect = pawnSelect;
    }
}
