package gr.teicrete.edu.tp3692.UI;

import gr.teicrete.edu.tp3692.SNLGame.CurrentPlayer;
import gr.teicrete.edu.tp3692.SNLGame.Dice;
import gr.teicrete.edu.tp3692.SNLGame.Ladder;
import gr.teicrete.edu.tp3692.SNLGame.Player;
import gr.teicrete.edu.tp3692.SNLGame.Powers;
import gr.teicrete.edu.tp3692.SNLGame.Snake;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 * Creates a Frame with a Board, Dice, CurrentPlayer and History objects
 *
 * @author tp3692
 */
public class Game extends JFrame {

    public Player player;
    public Player computer;
    public Dice dice;
    public Powers power;
    public MyTimer timer;
    public CurrentPlayer cp;
    public ArrayList<Integer> ladders;
    public ArrayList<Integer> snakes;
    public JLayeredPane panelCenter;
    public JTextArea history;
    public String difficulty;

    /**
     * Creates a Frame with a Board, Dice, CurrentPlayer and History objects
     *
     * @param player the manual player
     * @param difficulty the given difficulty
     */
    public Game(Player player, String difficulty) {
        this.dice = new Dice();
        this.player = player;
        this.computer = new Player();
        this.computer.setName("computer");
        this.difficulty = difficulty;
        this.timer = new MyTimer();
        cp = new CurrentPlayer();

        this.computer.getPawns().getPlayers().get(this.player.getPawnSelect());

        this.computer.setPawnSelect(randPwn());
        while (this.player.getPawnSelect() == this.computer.getPawnSelect()) {
            int rand = randPwn();
            this.computer.setPawnSelect(rand);
        }
        this.power = new Powers(this, dice);
        this.ladders = new ArrayList();
        this.snakes = new ArrayList();
        this.history = new JTextArea(34, 17);

        setTitle("Snakes&Ladders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(1000, 700));

        JPanel panelLeft = new JPanel();
        panelLeft.setLayout(new BorderLayout());

        JScrollPane scroll = new JScrollPane(this.history);
        panelLeft.add(scroll, BorderLayout.CENTER);
        panelLeft.add(new JLabel("History"), BorderLayout.NORTH);

        JPanel panelRight = new JPanel();
        panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
        panelRight.add(timer);

        JPanel panelRightTop = new JPanel();
        panelRightTop.setLayout(new FlowLayout());
        panelRight.add(panelRightTop);
        panelRightTop.add(dice);

        panelRight.add(cp);
        JPanel panelRightBottom = new JPanel();
        panelRightBottom.setLayout(new FlowLayout());
        panelRight.add(panelRightBottom);
        panelRightBottom.add(cp);

        Board myBoard = new Board();

        panelCenter = new JLayeredPane();
        panelCenter.setPreferredSize(myBoard.getPreferredSize());

        cp.getComputerPanel().setBackground(UIManager.getColor("Panel.background"));
        cp.getPlayerPanel().setBackground(Color.LIGHT_GRAY);

        myBoard.setBounds(0, 0, 600, 600);
        dice.btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                history.append("\n--------- NEW ROUND ---------\n");
                int rand = dice.rollDice();
                ImageIcon image = imageSizing(dice.diceSides.getSides().get(rand).getSide(), dice.diceLabel);
                dice.diceLabel.setIcon(image);

                player.pwrAffected = false;
                history.append(player.getName() + " got: " + (rand + 1) + "\n");
                pacing(player, rand + 1);

                int rand2 = dice.rollDice();
                computer.pwrAffected = false;
                image = imageSizing(dice.diceSides.getSides().get(rand2).getSide(), dice.diceLabel);
                dice.diceLabel.setIcon(image);
                history.append(computer.getName() + " got: " + (rand2 + 1) + "\n");
                pacing(computer, rand2 + 1);
            }
        });

        panelCenter.add(myBoard, new Integer(1));

        setJMenuBar(new MyMenuBar(panelLeft, this));
        add(panelLeft);
        add(panelCenter);
        add(panelRight);
        setVisible(true);
        pack();

        setPlayer(player);
        setPlayer(computer);

        cp.getPlayerLabel().setIcon(imageSizing(this.player.getPawns().getPlayers().get(this.player.getPawnSelect()).getPawn(),
                cp.getPlayerLabel()));
        cp.getComputerLabel().setIcon(imageSizing(this.computer.getPawns().getPlayers().get(this.computer.getPawnSelect()).getPawn(),
                cp.getComputerLabel()));

        /*  An theleis na dokimaseis kapoia dunami vale se comment ti switch
         *  kai vale auti tin addPowers pairnei to board poses fores ti dunami
         *  kai pia dunami apo 0-9
         */
//        
//        addPowers(myBoard, 20, 9);
//        addLadders(panelCenter, 4);
//        addSnakes(panelCenter, 3);
//
        switch (difficulty) {
            case "Easy":
                addLadders(panelCenter, 4);
                addSnakes(panelCenter, 1);
                addPowers(myBoard, 5, 3, 2);
                break;
            case "Normal":
                addLadders(panelCenter, 3);
                addSnakes(panelCenter, 3);
                addPowers(myBoard, 4, 3, 3);
                break;
            case "Hard":
                addLadders(panelCenter, 1);
                addSnakes(panelCenter, 4);
                addPowers(myBoard, 2, 3, 5);
                break;
            default:
                System.out.println("Didn't get any difficulty");
                break;
        }
    }

    /**
     *
     * @param myImage the image that you want to resize
     * @param p the label that you want to put it
     * @return returns the resized ImageIcon
     */
    public ImageIcon imageSizing(ImageIcon myImage, JLabel p) {

        Image img = myImage.getImage();
        Image newImage = img.getScaledInstance(p.getWidth(), p.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }

    /**
     *
     * @param player the player that you want to set at the start
     */
    public void setPlayer(Player player) {
        Component[] componentsInLayer = panelCenter.getComponentsInLayer(1);
        Board myBoard = (Board) componentsInLayer[0];
        player.setCurrentPosition(1);
        if ("computer".equals(player.getName())) {
            myBoard.getPanels().get(0).setComputerLabelImage(
                    imageSizing(player.getPawns().getPlayers().get(player.getPawnSelect()).getPawn(),
                            myBoard.getPanels().get(0).getComputerLabel()));
        } else {
            myBoard.getPanels().get(0).setPlayerLabelImage(
                    imageSizing(player.getPawns().getPlayers().get(player.getPawnSelect()).getPawn(),
                            myBoard.getPanels().get(0).getPlayerLabel()));
        }
    }

    /**
     * Holds the rounds history of the game
     *
     * @param player the player that you want to add history info
     * @param dice the dice that player rolled
     * @param cell the block that player got to
     */
    public void history(Player player, int dice, int cell) {
        Component[] componentsInLayer = panelCenter.getComponentsInLayer(1);
        Board myBoard = (Board) componentsInLayer[0];

        int newPosition = player.getCurrentPosition() + dice;

        if (player.isTurtle() == true) {
            dice /= 2;
            this.history.append("becomes: " + dice + " because turtle is activated \n");
        }
        if (newPosition == 100) {
            this.history.append(player.getName() + " won \n");
        } else {
            this.history.append(" goes from: " + player.getCurrentPosition() + " to: " + newPosition + "\n");
        }
        if (myBoard.getPanels().get(cell).isHasLadder()) {
            this.history.append(player.getName() + " got in a ladder \n");
            this.history.append(" goes from: " + player.getCurrentPosition() + " to: " + newPosition + "\n");
        }
        if (myBoard.getPanels().get(cell).isHasPower() && player.pwrAffected == false) {
            this.history.append(player.getName() + " activated " + myBoard.getPanels().get(cell).getPwrName() + "\n");
        }
        if (myBoard.getPanels().get(cell).isHasSnake()) {
            this.history.append(player.getName() + " got in a snake \n");
            this.history.append(" goes from: " + player.getCurrentPosition() + " to: " + newPosition + "\n");
        }
    }

    /**
     * Checks which one of the players is and if a special effect is affected on
     * player
     *
     * @param player
     * @param move
     */
    public void pacing(Player player, int move) {

        Component[] componentsInLayer = panelCenter.getComponentsInLayer(1);
        Board myBoard = (Board) componentsInLayer[0];
        if (player.isTurtle()) {
            if (myBoard.getPanels().get((player.getCurrentPosition() - 1)).isHasLadder() || myBoard.getPanels().get((player.getCurrentPosition() - 1)).isHasSnake()) {

            } else {
                move /= 2;
            }
        }
        if (player.getCurrentPosition() == 1) {
            player.setBackwards(false);
        }
        if (player.getName().equals("computer")) {
            cp.getComputerPanel().setBackground(UIManager.getColor("Panel.background"));
            cp.getPlayerPanel().setBackground(Color.LIGHT_GRAY);

            if (player.isBackwards()) {
                move = -move;
                computerMove(player, move);
            } else {
                computerMove(player, move);
            }
        } else {
            cp.getPlayerPanel().setBackground(UIManager.getColor("Panel.background"));
            cp.getComputerPanel().setBackground(Color.LIGHT_GRAY);

            if (player.isBackwards()) {
                move = -move;
                playerMove(player, move);
            } else {
                playerMove(player, move);

            }
        }

    }

    /**
     * Moves the player
     *
     * @param player the player
     * @param moveTo the dice value
     */
    public void playerMove(Player player, int moveTo) {
        Component[] componentsInLayer = panelCenter.getComponentsInLayer(1);
        Board myBoard = (Board) componentsInLayer[0];

        int newPosition = player.getCurrentPosition() + moveTo;

        if (newPosition < 1) {
            newPosition = 1;
        }
        if (newPosition < 101) {
            myBoard.getPanels().get((player.getCurrentPosition() - 1)).setPlayerLabelImage(null);
            myBoard.getPanels().get((player.getCurrentPosition() - 1)).getPlayerLabel().repaint();
            history(player, moveTo, (newPosition - 1));
            player.setCurrentPosition(newPosition);
            myBoard.getPanels().get((player.getCurrentPosition() - 1)).setPlayerLabelImage(
                    imageSizing(player.getPawns().getPlayers().get(player.getPawnSelect()).getPawn(),
                            myBoard.getPanels().get((player.getCurrentPosition() - 1)).getPlayerLabel()));
            if (myBoard.getPanels().get((player.getCurrentPosition() - 1)).isHasLadder()) {
                int climb = myBoard.getPanels().get((player.getCurrentPosition() - 1)).getLadderEnd().getCell() - myBoard.getPanels().get((player.getCurrentPosition() - 1)).getCell();
                pacing(player, climb);
            }
            if (myBoard.getPanels().get((player.getCurrentPosition() - 1)).isHasSnake()) {
                int slide = myBoard.getPanels().get((player.getCurrentPosition() - 1)).getSnakeTale().getCell() - myBoard.getPanels().get((player.getCurrentPosition() - 1)).getCell();
                pacing(player, slide);
            }
            if (myBoard.getPanels().get((player.getCurrentPosition() - 1)).isHasPower() && player.pwrAffected == false) {
                player.pwrAffected = true;
                power.powerEffect(myBoard.getPanels().get((player.getCurrentPosition() - 1)).getPwrName(), player);
            }
        }
        if (newPosition == 100) {
            new Won(player, difficulty);
            timer.stopTimer();
            history.append("/n The game took " + timer.getTimeFormat() + "\n");
//            this.dispose();
        }

    }

    /**
     * Moves the computer
     *
     * @param player the player
     * @param moveTo the dice value
     */
    public void computerMove(Player player, int moveTo) {
        Component[] componentsInLayer = panelCenter.getComponentsInLayer(1);
        Board myBoard = (Board) componentsInLayer[0];

        int newPosition = player.getCurrentPosition() + moveTo;
        if (newPosition < 1) {
            newPosition = 1;
        }
        if (newPosition < 101) {

            myBoard.getPanels().get((player.getCurrentPosition() - 1)).setComputerLabelImage(null);
            myBoard.getPanels().get((player.getCurrentPosition() - 1)).getComputerLabel().repaint();
            history(player, moveTo, (newPosition - 1));
            player.setCurrentPosition(newPosition);
            myBoard.getPanels().get((player.getCurrentPosition() - 1)).setComputerLabelImage(
                    imageSizing(player.getPawns().getPlayers().get(player.getPawnSelect()).getPawn(),
                            myBoard.getPanels().get((player.getCurrentPosition() - 1)).getComputerLabel()));
            if (myBoard.getPanels().get((player.getCurrentPosition() - 1)).isHasLadder()) {
                int climb = myBoard.getPanels().get((player.getCurrentPosition() - 1)).getLadderEnd().getCell() - myBoard.getPanels().get((player.getCurrentPosition() - 1)).getCell();
                pacing(player, climb);
            }
            if (myBoard.getPanels().get((player.getCurrentPosition() - 1)).isHasSnake()) {
                int slide = myBoard.getPanels().get((player.getCurrentPosition() - 1)).getSnakeTale().getCell() - myBoard.getPanels().get((player.getCurrentPosition() - 1)).getCell();
                pacing(player, slide);
            }
            if (myBoard.getPanels().get((player.getCurrentPosition() - 1)).isHasPower() && player.pwrAffected == false) {
                player.pwrAffected = true;
                power.powerEffect(myBoard.getPanels().get((player.getCurrentPosition() - 1)).getPwrName(), player);
            }
        }
        if (newPosition == 100) {
            new Won(player, difficulty);
            timer.stopTimer();
            history.append("/n The game took " + timer.getTimeFormat() + "\n");
//            this.dispose();
        }

    }

    /**
     * returns an index for random pawn from board
     *
     * @return
     */
    public static int randPwn() {
        int randomNum = 0;
        for (int i = 0; i < 5; i++) {
            int min = 0;
            int max = 3;

            Random rand = new Random();
            randomNum = rand.nextInt((max - min) + 1) + min;
        }
        return randomNum;
    }

    /**
     * returns an index for random special effect
     *
     * @return
     */
    public static int randPwrs() {
        int randomNum = 0;
        for (int i = 0; i < 5; i++) {
            int min = 0;
            int max = 9;

            Random rand = new Random();
            randomNum = rand.nextInt((max - min) + 1) + min;
        }
        return randomNum;
    }

    /**
     * returns an index for random panel on board
     *
     * @return
     */
    public static int randPnl() {
        int randomNum = 0;
        for (int i = 0; i < 5; i++) {
            int min = 0;
            int max = 99;

            while (randomNum == 0 || randomNum == 99) {
                Random rand = new Random();
                randomNum = rand.nextInt((max - min) + 1) + min;
            }
        }
        return randomNum;
    }

    /**
     * Adds special effect blocks to board
     *
     * @param board the current board
     * @param posNum the wanted number of positive effects
     * @param midNum the wanted number of doubtful effects
     * @param negNum the wanted number of negative effects
     */
    public void addPowers(Board board, int posNum, int midNum, int negNum) {

        for (int i = 0; i < posNum; i++) {
            int randPwr = randPwrs();
            while (randPwr > 4) {
                randPwr = randPwrs();
            }
            int randPnl = randPnl();
            System.out.println("easy: " + (randPnl + 1) + " power: " + (randPwr + 1));
            board.getPanels().get(randPnl).setPowerLabelImage(
                    imageSizing(power.pwrIcons.pwr.get(randPwr).getImg(), board.getPanels().get(randPnl).getPowerLabel()));
            board.getPanels().get(randPnl).setHasPower(true);
            board.getPanels().get(randPnl).setPwrName(power.pwrIcons.pwr.get(randPwr).getPwrName());
        }

        for (int i = 0; i < midNum; i++) {
            int randPwr = randPwrs();
            while (randPwr != 5 && randPwr != 6) {
                randPwr = randPwrs();
            }
            int randPnl = randPnl();
            System.out.println("medium: " + (randPnl + 1) + " power: " + (randPwr + 1));
            board.getPanels().get(randPnl).setPowerLabelImage(
                    imageSizing(power.pwrIcons.pwr.get(randPwr).getImg(), board.getPanels().get(randPnl).getPowerLabel()));
            board.getPanels().get(randPnl).setHasPower(true);
            board.getPanels().get(randPnl).setPwrName(power.pwrIcons.pwr.get(randPwr).getPwrName());
        }

        for (int i = 0; i < negNum; i++) {
            int randPwr = randPwrs();
            while (randPwr < 7) {
                randPwr = randPwrs();
            }
            int randPnl = randPnl();
            System.out.println("hard: " + (randPnl + 1) + " power: " + (randPwr + 1));
            board.getPanels().get(randPnl).setPowerLabelImage(
                    imageSizing(power.pwrIcons.pwr.get(randPwr).getImg(), board.getPanels().get(randPnl).getPowerLabel()));
            board.getPanels().get(randPnl).setHasPower(true);
            board.getPanels().get(randPnl).setPwrName(power.pwrIcons.pwr.get(randPwr).getPwrName());
        }
    }

    /**
     * Adds a specific special effect blocks to board for given number of times
     *
     * @param board the current board
     * @param num repeated times
     * @param pwr special effect selection from 0-9
     */
    public void addPowers(Board board, int num, int pwr) {
        Component[] componentsInLayer = panelCenter.getComponentsInLayer(1);
        Board myBoard = (Board) componentsInLayer[0];

        for (int i = 0; i < num; i++) {
            int randPnl = randPnl();
            int randPwr = pwr;
            myBoard.getPanels().get(randPnl).setPowerLabelImage(
                    imageSizing(power.pwrIcons.pwr.get(randPwr).getImg(), myBoard.getPanels().get(randPnl).getPowerLabel()));
            myBoard.getPanels().get(randPnl).setHasPower(true);
            myBoard.getPanels().get(randPnl).setPwrName(power.pwrIcons.pwr.get(randPwr).getPwrName());
        }
    }

    /**
     * Draws a given number of ladders with random starting and ending points
     *
     * @param panelCenter the LayerdPane to draw to
     * @param num number of ladders
     */
    public void addLadders(JLayeredPane panelCenter, int num) {
        Component[] componentsInLayer = panelCenter.getComponentsInLayer(1);
        Board myBoard = (Board) componentsInLayer[0];

        for (int i = 0; i < num; i++) {
            Point2D.Double start = new Point2D.Double(0, 0);
            Point2D.Double end = new Point2D.Double(0, 0);
            int startPnl = 0;
            int endPnl = 0;

            while (startPnl >= endPnl) {
                startPnl = randPnl();
                endPnl = randPnl();
                start = new Point2D.Double(myBoard.getPanels().get(startPnl).getLocation().x + (myBoard.getPanels().get(startPnl).getWidth() / 2), myBoard.getPanels().get(startPnl).getLocation().y + (myBoard.getPanels().get(startPnl).getHeight() / 2));
                end = new Point2D.Double(myBoard.getPanels().get(endPnl).getLocation().x + (myBoard.getPanels().get(endPnl).getWidth() / 2), myBoard.getPanels().get(endPnl).getLocation().y + (myBoard.getPanels().get(endPnl).getHeight() / 2));
            }
            System.out.println("start: " + (startPnl + 1) + " end: " + (endPnl + 1));
            myBoard.getPanels().get(startPnl).setHasLadder(true);
            myBoard.getPanels().get(startPnl).setLadderEnd(myBoard.getPanels().get(endPnl));

            Ladder ladder = new Ladder(start, end);
            ladder.setBounds(0, 0, 600, 600);
            ladder.setOpaque(false);
            ladders.add(startPnl);
            ladders.add(endPnl);
            panelCenter.add(ladder, new Integer(2));
        }
    }

    /**
     * Draws a given number of snakes with random starting and ending points
     *
     * @param panelCenter the JLayerdPane to draw to
     * @param num number of snakes
     */
    public void addSnakes(JLayeredPane panelCenter, int num) {
        Component[] componentsInLayer = panelCenter.getComponentsInLayer(1);
        Board myBoard = (Board) componentsInLayer[0];

        for (int i = 0; i < num; i++) {
            Point2D.Double start = new Point2D.Double(0, 0);
            Point2D.Double end = new Point2D.Double(0, 0);
            int headPnl = 0;
            int talePnl = 0;

            while (headPnl <= talePnl) {
                headPnl = randPnl();
                talePnl = randPnl();
                start = new Point2D.Double(myBoard.getPanels().get(headPnl).getLocation().x + (myBoard.getPanels().get(headPnl).getWidth() / 2), myBoard.getPanels().get(headPnl).getLocation().y + (myBoard.getPanels().get(headPnl).getHeight() / 2));
                end = new Point2D.Double(myBoard.getPanels().get(talePnl).getLocation().x + (myBoard.getPanels().get(talePnl).getWidth() / 2), myBoard.getPanels().get(talePnl).getLocation().y + (myBoard.getPanels().get(talePnl).getHeight() / 2));
            }
            System.out.println("head: " + (headPnl + 1) + " tale: " + (talePnl + 1));
            myBoard.getPanels().get(headPnl).setHasSnake(true);
            myBoard.getPanels().get(headPnl).setSnakeTale(myBoard.getPanels().get(talePnl));

            Snake snake = new Snake(start, end);
            snake.setBounds(0, 0, 600, 600);
            snake.setOpaque(false);
            snakes.add(headPnl);
            snakes.add(talePnl);
            panelCenter.add(snake, new Integer(2));
        }
    }

    /**
     * Changes the ladders to snakes and the other way around
     *
     * @param panelCenter the JLayerdPane to draw to
     */
    public void reverseGravity(JLayeredPane panelCenter) {
        Component[] componentsInLayer = panelCenter.getComponentsInLayer(1);
        Board myBoard = (Board) componentsInLayer[0];

        ArrayList<Integer> ladderTemp = (ArrayList<Integer>) ladders.clone();
        ladders.clear();

        ArrayList<Integer> snakesTemp = (ArrayList<Integer>) snakes.clone();
        snakes.clear();

        for (int i = 0; i < ladderTemp.size(); i += 2) {

            int headPnl = ladderTemp.get(i + 1);
            int talePnl = ladderTemp.get(i);

            Point2D.Double start = new Point2D.Double(myBoard.getPanels().get(headPnl).getLocation().x + (myBoard.getPanels().get(headPnl).getWidth() / 2), myBoard.getPanels().get(headPnl).getLocation().y + (myBoard.getPanels().get(headPnl).getHeight() / 2));
            Point2D.Double end = new Point2D.Double(myBoard.getPanels().get(talePnl).getLocation().x + (myBoard.getPanels().get(talePnl).getWidth() / 2), myBoard.getPanels().get(talePnl).getLocation().y + (myBoard.getPanels().get(talePnl).getHeight() / 2));

            myBoard.getPanels().get(headPnl).setHasSnake(true);
            myBoard.getPanels().get(talePnl).setHasLadder(false);
            myBoard.getPanels().get(headPnl).setSnakeTale(myBoard.getPanels().get(talePnl));

            Snake snake = new Snake(start, end);
            snake.setBounds(0, 0, 600, 600);
            snake.setOpaque(false);
            snakes.add(headPnl);
            snakes.add(talePnl);
            panelCenter.add(snake, new Integer(2));
        }

        for (int j = 0; j < snakesTemp.size(); j += 2) {

            int startPnl = snakesTemp.get(j + 1);
            int endPnl = snakesTemp.get(j);

            Point2D.Double start = new Point2D.Double(myBoard.getPanels().get(startPnl).getLocation().x + (myBoard.getPanels().get(startPnl).getWidth() / 2), myBoard.getPanels().get(startPnl).getLocation().y + (myBoard.getPanels().get(startPnl).getHeight() / 2));
            Point2D.Double end = new Point2D.Double(myBoard.getPanels().get(endPnl).getLocation().x + (myBoard.getPanels().get(endPnl).getWidth() / 2), myBoard.getPanels().get(endPnl).getLocation().y + (myBoard.getPanels().get(endPnl).getHeight() / 2));

            myBoard.getPanels().get(startPnl).setHasLadder(true);
            myBoard.getPanels().get(endPnl).setHasSnake(false);
            myBoard.getPanels().get(startPnl).setLadderEnd(myBoard.getPanels().get(endPnl));

            Ladder ladder = new Ladder(start, end);
            ladder.setBounds(0, 0, 600, 600);
            ladder.setOpaque(false);
            ladders.add(startPnl);
            ladders.add(endPnl);
            panelCenter.add(ladder, new Integer(2));

        }
    }

    class MyMenuBar extends JMenuBar {

        MyMenuBar(JPanel panelLeft, JFrame frame) {

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

            JMenuItem restart = new JMenuItem("Restart");
            game.add(restart);
            restart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    frame.dispose();
                    player.setBackwards(false);
                    player.setTurtle(false);
                    new Game(player, difficulty);
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

            JMenu historyItem = new JMenu("History");
            game.add(historyItem);

            JMenuItem showHistory = new JMenuItem("Show History");
            historyItem.add(showHistory);
            showHistory.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    panelLeft.setVisible(true);
                }
            });

            JMenuItem hideHistory = new JMenuItem("Hide History");
            historyItem.add(hideHistory);
            hideHistory.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {

                    panelLeft.setVisible(false);
                }
            });

            JMenu help = new JMenu("Help");
            add(help);

            JMenuItem about = new JMenuItem("About");
            help.add(about);
        }
    }
}
