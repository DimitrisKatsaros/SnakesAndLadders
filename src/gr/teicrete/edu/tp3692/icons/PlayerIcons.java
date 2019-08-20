/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.teicrete.edu.tp3692.icons;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * Creates an ArrayList with PlayerIcons objects
 *
 * @author tp3692
 */
public class PlayerIcons {

    private ArrayList<PlayerIcons> players;
    private ImageIcon pawn;
    private String playerName;

    /**
     * Creates an ArrayList with PlayerIcons objects
     */
    public PlayerIcons() {
        players = new ArrayList<>();
        players.add(new PlayerIcons(new ImageIcon("src\\gr\\teicrete\\edu\\tp3692\\icons\\pawn_blue.png"), "pawn_blue"));
        players.add(new PlayerIcons(new ImageIcon("src\\gr\\teicrete\\edu\\tp3692\\icons\\pawn_green.png"), "pawn_green"));
        players.add(new PlayerIcons(new ImageIcon("src\\gr\\teicrete\\edu\\tp3692\\icons\\pawn_magenta.png"), "pawn_magenta"));
        players.add(new PlayerIcons(new ImageIcon("src\\gr\\teicrete\\edu\\tp3692\\icons\\pawn_red.png"), "pawn_red"));
    }

    /**
     * Creates an ArrayList with PlayerIcons objects and a name
     *
     * @param pawn a path for pawn icon
     * @param playerName a name for the pawn
     */
    public PlayerIcons(ImageIcon pawn, String playerName) {
        this.pawn = pawn;
        this.playerName = playerName;
    }

    /**
     * Returns an array list with pawns icons and the names that come with
     *
     * @return
     */
    public ArrayList<PlayerIcons> getPlayers() {
        return players;
    }

    /**
     * returns a pawn image
     *
     * @return
     */
    public ImageIcon getPawn() {
        return pawn;
    }

    /**
     * sets a pawn image
     *
     * @param pawn a pawn image
     */
    public void setPawn(ImageIcon pawn) {
        this.pawn = pawn;
    }

    /**
     *
     * @return the name that the pawn image comes with
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * sets the name that the pawn image comes with
     *
     * @param playerName
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

}
