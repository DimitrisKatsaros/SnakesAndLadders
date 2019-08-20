
package gr.teicrete.edu.tp3692.SNLGame;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Creates a panel that displays the name and the pawns the players use
 * @author tp3692
 */
public class CurrentPlayer extends JPanel {

    private JLabel playerLabel;
    private JLabel computerLabel;
    private JPanel playerPanel;
    private JPanel computerPanel;

    /**
     * Creates a panel that displays the name and the pawns the players use
     */
    public CurrentPlayer() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        playerPanel = new JPanel();
        playerPanel.setPreferredSize(new Dimension(100, 100));

        JLabel playerName = new JLabel(" Player ");
        playerPanel.add(playerName);

        playerLabel = new JLabel(new ImageIcon());
        playerLabel.setPreferredSize(new Dimension(80, 75));
        playerPanel.add(playerLabel);

        computerPanel = new JPanel();
        computerPanel.setPreferredSize(new Dimension(100, 100));

        JLabel computerName = new JLabel(" Computer ");
        computerPanel.add(computerName);

        computerLabel = new JLabel(new ImageIcon());
        computerLabel.setPreferredSize(new Dimension(80, 75));
        computerPanel.add(computerLabel);

        add(playerPanel);
        add(computerPanel);
    }

    /**
     *
     * @return returns the panel that displays players information
     */
    public JPanel getPlayerPanel() {
        return playerPanel;
    }

    /**
     *
     * @param playerPanel the panel that displays players information
     */
    public void setPlayerPanel(JPanel playerPanel) {
        this.playerPanel = playerPanel;
    }

    /**
     *
     * @return returns the panel that displays computers information
     */
    public JPanel getComputerPanel() {
        return computerPanel;
    }

    /**
     *
     * @param computerPanel the panel that displays computers information
     */
    public void setComputerPanel(JPanel computerPanel) {
        this.computerPanel = computerPanel;
    }

    /**
     *
     * @return returns the label that displays players pawn
     */
    public JLabel getPlayerLabel() {
        return playerLabel;
    }

    /**
     *
     * @param playerLabel the label that displays players pawn
     */
    public void setPlayerLabel(JLabel playerLabel) {
        this.playerLabel = playerLabel;
    }

    /**
     *
     * @return returns the label that displays computers pawn
     */
    public JLabel getComputerLabel() {
        return computerLabel;
    }

    /**
     *
     * @param computerLabel the label that displays computers pawn
     */
    public void setComputerLabel(JLabel computerLabel) {
        this.computerLabel = computerLabel;
    }

}
