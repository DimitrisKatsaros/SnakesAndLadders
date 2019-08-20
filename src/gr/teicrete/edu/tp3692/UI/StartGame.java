package gr.teicrete.edu.tp3692.UI;

import gr.teicrete.edu.tp3692.SNLGame.Player;
import gr.teicrete.edu.tp3692.icons.PlayerIcons;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Creates the starting frame in which you can choose your name and pawn
 *
 * @author tp3692
 */
public class StartGame extends JFrame {

    private PlayerIcons players;
    private Player player;
    private ArrayList<JPanel> pawnPnls;
    private Color color;
    private String difficulty;

    /**
     * Creates the starting frame in which you can choose your name and pawn
     *
     */
    public StartGame() {
        this.players = new PlayerIcons();
        this.player = new Player();
        this.pawnPnls = new ArrayList();
        this.color = UIManager.getColor("Panel.background");
        this.difficulty = "Normal";

        setTitle("Snakes&Ladders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 500));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 5 - this.getSize().width / 5, dim.height / 5 - this.getSize().height / 5);

        JPanel startPnl = new JPanel();
        startPnl.setLayout(new GridLayout(4, 1));

        JPanel startTop = new JPanel();
        startTop.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
        startPnl.add(startTop);

        JLabel playerName = new JLabel("Enter Player Name: ");
        startTop.add(playerName);

        JTextField playerNameField = new JTextField("Unamed");
        playerNameField.setPreferredSize(new Dimension(100, 20));
        startTop.add(playerNameField);

        JPanel startMiddle = new JPanel();
        startMiddle.setLayout(new FlowLayout());
        startPnl.add(startMiddle);

        JLabel pawnSelect = new JLabel("Choose Your Pawn: ");
        startMiddle.add(pawnSelect);

        MyMouseListener mml = new MyMouseListener();

        JPanel bluePanel = new JPanel();
        bluePanel.addMouseListener(mml);
        pawnPnls.add(bluePanel);
        startMiddle.add(bluePanel);

        JLabel blue = new JLabel(new ImageIcon());
        blue.setPreferredSize(new Dimension(60, 60));
        bluePanel.add(blue);

        blue.addMouseListener(mml);

        JPanel greenPanel = new JPanel();
        pawnPnls.add(greenPanel);
        startMiddle.add(greenPanel);

        JLabel green = new JLabel(new ImageIcon());
        green.setPreferredSize(new Dimension(60, 60));
        greenPanel.add(green);

        green.addMouseListener(mml);

        JPanel magentaPanel = new JPanel();
        pawnPnls.add(magentaPanel);
        startMiddle.add(magentaPanel);

        JLabel magenta = new JLabel(new ImageIcon());
        magenta.setPreferredSize(new Dimension(60, 60));
        magentaPanel.add(magenta);

        magenta.addMouseListener(mml);

        JPanel redPanel = new JPanel();
        pawnPnls.add(redPanel);
        startMiddle.add(redPanel);

        JLabel red = new JLabel(new ImageIcon());
        red.setPreferredSize(new Dimension(60, 60));
        redPanel.add(red);

        red.addMouseListener(mml);
        JPanel startBottomTop = new JPanel();
        startBottomTop.setLayout(new FlowLayout());
        startPnl.add(startBottomTop);

        JRadioButton easy = new JRadioButton("Easy");
        easy.addActionListener(new MyRadioListener());
        JRadioButton normal = new JRadioButton("Normal");
        normal.addActionListener(new MyRadioListener());
        normal.setSelected(true);
        JRadioButton hard = new JRadioButton("Hard");
        hard.addActionListener(new MyRadioListener());

        ButtonGroup bG = new ButtonGroup();

        bG.add(easy);
        bG.add(normal);
        bG.add(hard);
        startBottomTop.add(easy);
        startBottomTop.add(normal);
        startBottomTop.add(hard);

        JPanel startBottom = new JPanel();
        startBottom.setLayout(new FlowLayout());
        startPnl.add(startBottom);

        JButton startBtn = new JButton(" Start Game ");
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JButton b = (JButton) e.getSource();
                player.setName(playerNameField.getText());

                new Game(player, difficulty);
                dispose();
            }
        });
        startBottom.add(startBtn);

        JPanel snlp = new JPanel();

        JLabel snl = new JLabel(" SNAKES AND LADDERS ");
        snl.setFont(new Font("Serif", Font.PLAIN, 20));
        snlp.add(snl);

        add(snlp, BorderLayout.NORTH);
        add(startPnl, BorderLayout.CENTER);
        pack();
        setVisible(true);

        blue.setIcon(imageSizing(players.getPlayers().get(0).getPawn(), blue));
        green.setIcon(imageSizing(players.getPlayers().get(1).getPawn(), blue));
        magenta.setIcon(imageSizing(players.getPlayers().get(2).getPawn(), blue));
        red.setIcon(imageSizing(players.getPlayers().get(3).getPawn(), blue));
    }

    /**
     * Resizes the given image to fit the given label
     *
     * @param myImage given ImageIcon
     * @param p given JLabel
     * @return returns an ImageIcon
     */
    public ImageIcon imageSizing(ImageIcon myImage, JLabel p) {

        Image img = myImage.getImage();
        Image newImage = img.getScaledInstance(p.getWidth(), p.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }

    class MyRadioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton b = (JRadioButton) e.getSource();
            difficulty = b.getText();
        }
    }

    class MyMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel pawnLabel = (JLabel) e.getSource();
            JPanel parentPanel = (JPanel) pawnLabel.getParent();

            int j = 0;

            for (int i = 0; i < pawnPnls.size(); i++) {
                pawnPnls.get(i).setBackground(color);
                if (parentPanel == pawnPnls.get(i)) {
                    j = i;
                }
            }

            parentPanel.setBackground(Color.LIGHT_GRAY);

            player.setPawnSelect(j);
            System.out.println(player.getPawnSelect());
        }

        @Override
        public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
}
