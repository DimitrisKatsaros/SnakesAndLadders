package gr.teicrete.edu.tp3692.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Creates a custom JPanel with 2x2 GridLayout
 *
 * @author tp3692
 */
public class SNLPanel extends JPanel {

    private int cell;
    private int indexInList;
    private String pwrName;
    private boolean hasPower;
    private boolean hasSnake;
    private SNLPanel snakeTale;
    private boolean hasLadder;
    private SNLPanel ladderEnd;
    private JLabel cellLabel;
    private JLabel playerLabel;
    private JLabel computerLabel;
    private JLabel powerLabel;

    /**
     * Creates a custom JPanel with 2x2 GridLayout
     *
     */
    public SNLPanel() {

        this.hasPower = false;
        this.hasSnake = false;
        this.hasLadder = false;

        this.cellLabel = new JLabel();
        this.playerLabel = new JLabel(new ImageIcon());
        this.computerLabel = new JLabel(new ImageIcon());
        this.powerLabel = new JLabel(new ImageIcon());

        setLayout(new GridLayout(2, 2));
        setBackground(Color.decode("#009999"));
        setPreferredSize(new Dimension(60, 60));

        add(cellLabel);
        add(powerLabel);
        add(playerLabel);
        add(computerLabel);

        setLayout(new GridLayout(2, 2));
    }

    /**
     *
     * @return gets the label that player displayed on
     */
    public JLabel getPlayerLabel() {
        return playerLabel;
    }

    /**
     *
     * @param playerLabel the label that player displayed on
     */
    public void setPlayerLabel(JLabel playerLabel) {
        this.playerLabel = playerLabel;
    }

    /**
     *
     * @return gets the label that computer displayed on
     */
    public JLabel getComputerLabel() {
        return computerLabel;
    }

    /**
     *
     * @param computerLabel the label that computer displayed on
     */
    public void setComputerLabel(JLabel computerLabel) {
        this.computerLabel = computerLabel;
    }

    /**
     *
     * @return gets the label that special effect icon displayed on
     */
    public JLabel getPowerLabel() {
        return powerLabel;
    }

    /**
     *
     * @param powerLabel the label that special effect icon displayed on
     */
    public void setPowerLabel(JLabel powerLabel) {
        this.powerLabel = powerLabel;
    }

    /**
     *
     * @return a boolean to know if the block has a special effect
     */
    public boolean isHasPower() {
        return hasPower;
    }

    /**
     *
     * @param hasPower sets the boolean for special effect
     */
    public void setHasPower(boolean hasPower) {
        this.hasPower = hasPower;
    }

    /**
     *
     * @return a boolean to know if the block has a snake head
     */
    public boolean isHasSnake() {
        return hasSnake;
    }

    /**
     *
     * @param hasSnake the boolean to know if the block has a snake head
     */
    public void setHasSnake(boolean hasSnake) {
        this.hasSnake = hasSnake;
    }

    /**
     *
     * @return a boolean to know if the block has a ladder start
     */
    public boolean isHasLadder() {
        return hasLadder;
    }

    /**
     *
     * @param hasLadder the boolean to know if the block has a ladder start
     */
    public void setHasLadder(boolean hasLadder) {
        this.hasLadder = hasLadder;
    }

    /**
     *
     * @return gets the number of the block
     */
    public int getCell() {
        return cell;
    }

    /**
     *
     * @param cell sets the number of the block
     */
    public void setCell(int cell) {
        this.cell = cell;
    }

    /**
     *
     * @param cellLabel a label that displays the block number
     */
    public void setCellLabelText(String cellLabel) {
        this.cellLabel.setText(cellLabel);
    }

    /**
     *
     * @param pwrImg the path for of the special effect icon
     */
    public void setPowerLabelImage(ImageIcon pwrImg) {
        this.powerLabel.setIcon(pwrImg);
    }

    /**
     *
     * @param pawnImg the path for of the pawn icon of the player
     */
    public void setPlayerLabelImage(ImageIcon pawnImg) {
        this.playerLabel.setIcon(pawnImg);
    }

    /**
     *
     * @param pawnImg the path for of the pawn icon of the computer
     */
    public void setComputerLabelImage(ImageIcon pawnImg) {
        this.computerLabel.setIcon(pawnImg);
    }

    /**
     *
     * @return gets the name of the special effect
     */
    public String getPwrName() {
        return pwrName;
    }

    /**
     *
     * @param pwrName sets the name of the special effect
     */
    public void setPwrName(String pwrName) {
        this.pwrName = pwrName;
    }

    /**
     *
     * @return gets the SNLPanel that the snake tale drawn to
     */
    public SNLPanel getSnakeTale() {
        return snakeTale;
    }

    /**
     *
     * @param snakeTale sets the SNLPanel that the snake tale drawn to
     */
    public void setSnakeTale(SNLPanel snakeTale) {
        this.snakeTale = snakeTale;
    }

    /**
     *
     * @return gets the SNLPanel that the ladders end drawn to
     */
    public SNLPanel getLadderEnd() {
        return ladderEnd;
    }

    /**
     *
     * @param ladderEnd sets the SNLPanel that the ladders end drawn to
     */
    public void setLadderEnd(SNLPanel ladderEnd) {
        this.ladderEnd = ladderEnd;
    }
}
