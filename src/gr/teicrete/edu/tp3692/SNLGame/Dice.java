package gr.teicrete.edu.tp3692.SNLGame;

import gr.teicrete.edu.tp3692.icons.DiceIcons;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Creates a panel that displays a dice side and a button so that you can roll
 * it
 *
 * @author tp3692
 */
public class Dice extends JPanel {

    public DiceIcons diceSides;
    public JLabel diceLabel;
    public JButton btn;

    /**
     * Creates a panel that displays a dice side and a button so that you can
     * roll it
     */
    public Dice() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.diceSides = new DiceIcons();
        this.diceLabel = new JLabel(new ImageIcon());
        diceLabel.setSize(new Dimension(100, 100));

        ImageIcon myImage = diceSides.getSides().get(0).getSide();
        Image img = myImage.getImage();
        Image newImage = img.getScaledInstance(diceLabel.getWidth(), diceLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        diceLabel.setIcon(image);

        this.btn = new JButton("ROLL   DICE");

        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(diceLabel);
        add(btn);
    }

    /**
     *
     * @return a random value from 0-5
     */
    public int rollDice() {
        int roll = 0;
        for (int i = 0; i < 5; i++) {
            roll = (int) (Math.random() * 6) + 1;
        }
        return roll - 1;
    }

}
