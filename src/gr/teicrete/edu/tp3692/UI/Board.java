package gr.teicrete.edu.tp3692.UI;

import gr.teicrete.edu.tp3692.icons.PowerIcons;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;

/**
 * Creates a 10x10 board with SNLPanel objects
 *
 * @author tp3692
 */
public class Board extends JPanel {

    private PowerIcons powers;
    private ArrayList<SNLPanel> panels;
    private ArrayList<SNLPanel> testPanels;

    /**
     * Creates a 10x10 board with SNLPanel objects
     *
     */
    public Board() {
        this.panels = new ArrayList();
        this.testPanels = new ArrayList();
        powers = new PowerIcons();
        setLayout(new GridLayout(10, 10, 2, 2));
        setPreferredSize(new Dimension(600, 600));

        int k = 100;
        for (int i = 10; i > 0; i--) {
            if (i % 2 == 0) {
                for (int j = 10; j > 0; j--) {
                    SNLPanel p = new SNLPanel();
                    p.setCell(k--);
                    p.setCellLabelText(Integer.toString(p.getCell()));
                    panels.add(p);
                    if (j % 2 == 0) {
                        p.setBackground(Color.decode("#ff6600"));
                    }
                    add(p);

                }

            } else {
                for (int j = 1; j < 11; j++) {
                    SNLPanel p = new SNLPanel();
                    p.setCell(++k);
                    p.setCellLabelText(Integer.toString(p.getCell()));
                    panels.add(p);
                    if (j % 2 == 0) {
                        p.setBackground(Color.decode("#ff6600"));
                    }
                    add(p);

                }
            }
            k -= 10;
        }

        revalidate();

        Collections.sort(panels, (SNLPanel o1, SNLPanel o2) -> {
            if (o1.getCell() == o2.getCell()) {
                return 0;
            }
            return o1.getCell() < o2.getCell() ? -1 : 1;
        });
    }

    /**
     *
     * @return an ArrayList with all the SNLPanels on the board
     */
    public ArrayList<SNLPanel> getPanels() {
        return panels;
    }
}


//        Collections.sort(panels, new Comparator<SNLPanel>() {
//            public int compare(SNLPanel o1, SNLPanel o2) {
//                if (o1.getCell() == o2.getCell()) {
//                    return 0;
//                }
//                return o1.getCell() < o2.getCell() ? -1 : 1;
//            }
//        });