package gr.teicrete.edu.tp3692.SNLGame;

import gr.teicrete.edu.tp3692.UI.Game;
import gr.teicrete.edu.tp3692.icons.PowerIcons;
import java.awt.Component;
import javax.swing.JPanel;

/**
 * Creates a power
 *
 * @author tp3692
 */
public class Powers {

    public PowerIcons pwrIcons;
    private Game game;
    private Dice dice;

    /**
     * Creates a power
     *
     * @param game a Game object that has all the info of the frame
     * @param dice a Dice object for re-throw propose
     */
    public Powers(Game game, Dice dice) {
        this.game = game;
        this.dice = dice;
        this.pwrIcons = new PowerIcons();
    }

    /**
     *
     * @param pwrName the name of the power
     * @param player a Player object
     */
    public void powerEffect(String pwrName, Player player) {
        switch (pwrName) {
            case "clover":
                clover(player);
                System.out.println(pwrName);
                break;
            case "exchange_pawns":
                exchangePawns();
                System.out.println(pwrName);
                break;
            case "explosion":
                explosion(player);
                System.out.println(pwrName);
                break;
            case "reverse_gravity":
                reverseGravity();
                System.out.println(pwrName);
                break;
            case "love":
                love(player);
                System.out.println(pwrName);
                break;
            case "dice_rethrow":
                diceRethrow(player);
                System.out.println(pwrName);
                break;
            case "reverse_movement":
                reverseMovement(player);
                System.out.println(pwrName);
                break;
            case "reverse_movement_cancel":
                reverseMovementCancel(player);
                System.out.println(pwrName);
                break;
            case "turtle":
                turtle(player);
                System.out.println(pwrName);
                break;
            case "turtle_cancel":
                turtleCancel(player);
                System.out.println(pwrName);
                break;
            default:
                break;
        }
    }

    /**
     * Re-throws the dice. If it's 6 goes directly to finish otherwise moves
     * accordingly to the dice
     *
     * @param player the player that triggered the special effect block
     */
    public void clover(Player player) {
        player.pwrAffected = false;
        int luckyDice = dice.rollDice() + 1;
        game.history.append(player.getName() + " lucky throw " + luckyDice + "\n");
        if (luckyDice == 6) {
            game.pacing(player, 100 - player.getCurrentPosition());

        } else {
            game.pacing(player, luckyDice);

        }
    }

    /**
     * Exchanges the players
     *
     */
    public void exchangePawns() {
        game.player.pwrAffected = true;
        game.computer.pwrAffected = true;

        int exchange = game.player.getCurrentPosition() - game.computer.getCurrentPosition();

        if (exchange > 0) {
            game.history.append("player ");
            game.pacing(game.player, (-exchange));
            game.history.append("computer ");
            game.pacing(game.computer, exchange);
        } else {
            game.history.append("player ");
            game.pacing(game.player, (-exchange));
            game.history.append("computer ");
            game.pacing(game.computer, exchange);
        }
    }

    /**
     * Sets the player to start
     *
     * @param player the player that triggered the special effect block
     */
    public void explosion(Player player) {
        int nathanExplosion = -player.getCurrentPosition() + 1;
        game.pacing(player, nathanExplosion);
    }

    /**
     * Changes the ladders to snakes and the other way around
     *
     */
    public void reverseGravity() {

        Component[] componentsInLayer = game.panelCenter.getComponentsInLayer(2);

        for (int i = 0; i < componentsInLayer.length; i++) {
            componentsInLayer[i].setVisible(false);
        }

        game.reverseGravity(game.panelCenter);
    }

    /**
     * Sends the other player to star
     *
     * @param player the player that triggered the special effect block
     */
    public void love(Player player) {

        if (player.getName().equals("computer")) {
            int nathanExplosion = -game.player.getCurrentPosition() + 1;
            game.pacing(game.player, nathanExplosion);
        } else {
            int nathanExplosion = -game.computer.getCurrentPosition() + 1;
            game.pacing(game.computer, nathanExplosion);
        }
    }

    /**
     * re-throws the dice and the player moves accordingly
     *
     * @param player the player that triggered the special effect block
     */
    public void diceRethrow(Player player) {
        player.pwrAffected = false;
        game.pacing(player, dice.rollDice() + 1);
    }

    /**
     * The player that triggered it moves backwards
     *
     * @param player the player that triggered the special effect block
     */
    public void reverseMovement(Player player) {
        player.setBackwards(true);
    }

    /**
     * Counter effects the reverse movement block
     *
     * @param player
     */
    public void reverseMovementCancel(Player player) {
        player.setBackwards(false);
    }

    /**
     * The pacing of player cuts in half
     *
     * @param player the player that triggered the special effect block
     */
    public void turtle(Player player) {
        player.setTurtle(true);
    }

    /**
     * Counter effects the turtle block
     *
     * @param player the player that triggered the special effect block
     */
    public void turtleCancel(Player player) {
        player.setTurtle(false);
    }
}
