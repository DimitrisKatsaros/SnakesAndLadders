package gr.teicrete.edu.tp3692.UI;

import gr.teicrete.edu.tp3692.SNLGame.Player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * Creates a frame that displays the winner of the game
 *
 * @author tp3692
 */
public class Won extends JFrame {

    private Player player;
    private String difficulty;

    /**
     * Creates a frame that displays the winner of the game
     *
     * @param player the manual player
     * @param difficulty the difficulty chosen
     */
    public Won(Player player, String difficulty) {
        this.player = player;
        this.difficulty = difficulty;

        setTitle("Snakes&Ladders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 550));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 5 - this.getSize().width / 5, dim.height / 5 - this.getSize().height / 5);

        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#4286f4"));

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.decode("#4286f4"));

        JLabel nameLabel = new JLabel(player.getName() + " WON!!!");
        nameLabel.setFont(new Font("Serif", Font.PLAIN, 50));
        nameLabel.setForeground(Color.decode("#f7aa47"));

        Icon icon = new ImageIcon("src\\gr\\teicrete\\edu\\tp3692\\icons\\nyan.gif");
        JLabel label = new JLabel(icon);

        panel2.add(nameLabel);
        panel.add(label);
        add(panel, BorderLayout.CENTER);
        add(panel2, BorderLayout.NORTH);
        setJMenuBar(new MyMenuBar(this));
        pack();
        setVisible(true);
    }

    class MyMenuBar extends JMenuBar {

        MyMenuBar(JFrame frame) {

            JMenu game = new JMenu("Game");
            add(game);

            JMenuItem open = new JMenuItem("New Game");
            game.add(open);
            open.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    frame.dispose();
                    String[] args = null;
                    new StartGame();
                }
            });

            JMenuItem exit = new JMenuItem("Exit");
            game.add(exit);
            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    frame.dispose();
                }
            });

            JMenu help = new JMenu("Help");
            add(help);

            JMenuItem about = new JMenuItem("About");
            help.add(about);
        }
    }

}
