package gr.teicrete.edu.tp3692.icons;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * Creates an ArrayList with PowerIcons objects
 *
 * @author tp3692
 */
public class PowerIcons {

    public ArrayList<PowerIcons> pwr;
    private ImageIcon img;
    private String pwrName;

    /**
     * Creates an ArrayList with PowerIcons objects
     */
    public PowerIcons() {
        pwr = new ArrayList<>();
        pwr.add(new PowerIcons(new ImageIcon("src\\gr\\teicrete\\edu\\tp3692\\icons\\clover.png"), "clover"));
        pwr.add(new PowerIcons(new ImageIcon("src\\gr\\teicrete\\edu\\tp3692\\icons\\love.png"), "love"));
        pwr.add(new PowerIcons(new ImageIcon("src\\gr\\teicrete\\edu\\tp3692\\icons\\rethrow_dice.png"), "dice_rethrow"));
        pwr.add(new PowerIcons(new ImageIcon("src\\gr\\teicrete\\edu\\tp3692\\icons\\reverse_cancel.png"), "reverse_movement_cancel"));
        pwr.add(new PowerIcons(new ImageIcon("src\\gr\\teicrete\\edu\\tp3692\\icons\\turtle_cancel.png"), "turtle_cancel"));
        pwr.add(new PowerIcons(new ImageIcon("src\\gr\\teicrete\\edu\\tp3692\\icons\\exchange_pawns.png"), "exchange_pawns"));
        pwr.add(new PowerIcons(new ImageIcon("src\\gr\\teicrete\\edu\\tp3692\\icons\\gravity_reversal.png"), "reverse_gravity"));
        pwr.add(new PowerIcons(new ImageIcon("src\\gr\\teicrete\\edu\\tp3692\\icons\\explosion.png"), "explosion"));
        pwr.add(new PowerIcons(new ImageIcon("src\\gr\\teicrete\\edu\\tp3692\\icons\\reverse.png"), "reverse_movement"));
        pwr.add(new PowerIcons(new ImageIcon("src\\gr\\teicrete\\edu\\tp3692\\icons\\turtle.png"), "turtle"));

    }

    /**
     * Creates an ArrayList with PlayerIcons objects and a name
     *
     * @param img a new ImageIcon with special effect image
     * @param pwrName a name to accompany the special effect image
     */
    public PowerIcons(ImageIcon img, String pwrName) {
        this.img = img;
        this.pwrName = pwrName;
    }

    /**
     * Returns an array list with special effect icons and the names that come
     * with
     *
     * @return
     */
    public ArrayList<PowerIcons> getPwr() {
        return pwr;
    }

    /**
     * returns a special effect image
     *
     * @return
     */
    public ImageIcon getImg() {
        return img;
    }

    /**
     * sets a special effect image
     *
     * @param img a special effect image
     */
    public void setImg(ImageIcon img) {
        this.img = img;
    }

    /**
     *
     * @return the name that the special effect image comes with
     */
    public String getPwrName() {
        return pwrName;
    }

    /**
     * sets the name that the special effect image comes with
     *
     * @param pwrName name to accompany the special effect image
     */
    public void setPwrName(String pwrName) {
        this.pwrName = pwrName;
    }
}
