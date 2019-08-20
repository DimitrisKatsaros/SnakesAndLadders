package gr.teicrete.edu.tp3692.icons;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * Creates an ArrayList with DiceIcons objects
 *
 * @author tp3692
 */
public class DiceIcons {

    private ArrayList<DiceIcons> sides;
    private ImageIcon side;

    /**
     * Creates an ArrayList with DiceIcons objects
     *
     */
    public DiceIcons() {

        sides = new ArrayList<>();
        sides.add(new DiceIcons(new ImageIcon("src\\gr\\teicrete\\edu\\tp3692\\icons\\dice1.png")));
        sides.add(new DiceIcons(new ImageIcon("src\\gr\\teicrete\\edu\\tp3692\\icons\\dice2.png")));
        sides.add(new DiceIcons(new ImageIcon("src\\gr\\teicrete\\edu\\tp3692\\icons\\dice3.png")));
        sides.add(new DiceIcons(new ImageIcon("src\\gr\\teicrete\\edu\\tp3692\\icons\\dice4.png")));
        sides.add(new DiceIcons(new ImageIcon("src\\gr\\teicrete\\edu\\tp3692\\icons\\dice5.png")));
        sides.add(new DiceIcons(new ImageIcon("src\\gr\\teicrete\\edu\\tp3692\\icons\\dice6.png")));
    }

    /**
     *
     * @param side path for ImageIcon of the side
     */
    public DiceIcons(ImageIcon side) {
        this.side = side;
    }

    /**
     * returns an ArrayList with the DiceIcons
     *
     * @return
     */
    public ArrayList<DiceIcons> getSides() {
        return sides;
    }

    /**
     * returns an ImageIcon with a dice side
     *
     * @return
     */
    public ImageIcon getSide() {
        return side;
    }
}
