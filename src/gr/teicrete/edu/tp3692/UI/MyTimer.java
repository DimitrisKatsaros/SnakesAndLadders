/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.teicrete.edu.tp3692.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author tp3692
 */
public class MyTimer extends JPanel {

    private javax.swing.Timer timer;
    private String timeFormat;
    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;

    public MyTimer() {
        setBackground(Color.decode("#e0e0e0"));

        JTextField timeDisp = new JTextField();
        timeDisp.setPreferredSize(new Dimension(100, 50));

        timeFormat = "00:00:00";
        timer = new javax.swing.Timer(1000, (ActionEvent e) -> {
            seconds++;

            if (seconds > 59) {
                minutes++;
                seconds = 0;
            }

            if (minutes > 59) {
                hours++;
                minutes = 0;
            }

            if (hours > 1) {
                System.out.println("That's waaaaay to much for a Snakes and Ladders Game"
                        + " im stoping it!!!");
                stopTimer();
            }

            timeFormat = String.format("%02d:%02d:%02d", hours, minutes, seconds);

            timeDisp.setText(timeFormat);
            repaint();
        });

        timeDisp.setEditable(false);
        timeDisp.setHorizontalAlignment(SwingConstants.CENTER);
        timeDisp.setFont(new Font("Serif", Font.PLAIN, 20));
        timeDisp.setText(timeFormat);

        timer.start();
        add(timeDisp);
    }

    public void stopTimer() {
        timer.stop();
    }

    public String getTimeFormat() {
        return timeFormat;
    }

}
