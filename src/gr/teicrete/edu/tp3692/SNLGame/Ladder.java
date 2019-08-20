package gr.teicrete.edu.tp3692.SNLGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

/**
 * Draws a ladders in a panel with given starting and ending points
 *
 * @author user
 */
public class Ladder extends JPanel {

    private Point2D start;
    private Point2D end;

    /**
     * Draws a ladders in a panel with given starting and ending points
     *
     * @param start is the starting 2d point of the ladder
     * @param end is the end 2d point of the ladder
     */
    public Ladder(Point2D start, Point2D end) {
        this.start = start;
        this.end = end;

        setOpaque(true);
    }

    private static void drawLadderBetweenPoints(
            Graphics2D g, Point2D p0, Point2D p1) {
        final double ladderWidth = 40;
        final double distanceBetweenSteps = 30;
        final double barWidth = 5;

        double dx = p1.getX() - p0.getX();
        double dy = p1.getY() - p0.getY();
        double distance = p1.distance(p0);

        double dirX = dx / distance;
        double dirY = dy / distance;

        double offsetX = dirY * ladderWidth * 0.5;
        double offsetY = -dirX * ladderWidth * 0.5;

        Line2D lineR = new Line2D.Double(
                p0.getX() + offsetX,
                p0.getY() + offsetY,
                p1.getX() + offsetX,
                p1.getY() + offsetY);

        Line2D lineL = new Line2D.Double(
                p0.getX() - offsetX,
                p0.getY() - offsetY,
                p1.getX() - offsetX,
                p1.getY() - offsetY);

        drawBar(g, lineL, barWidth);
        drawBar(g, lineR, barWidth);

        int numSteps = (int) (distance / distanceBetweenSteps);
        for (int i = 0; i < numSteps; i++) {
            double stepOffsetX = (i + 1) * distanceBetweenSteps;
            double stepOffsetY = (i + 1) * distanceBetweenSteps;

            Line2D step = new Line2D.Double(
                    p0.getX() + stepOffsetX * dirX - offsetX,
                    p0.getY() + stepOffsetY * dirY - offsetY,
                    p0.getX() + stepOffsetX * dirX + offsetX,
                    p0.getY() + stepOffsetY * dirY + offsetY);
            drawBar(g, step, barWidth);
        }
    }

    private static void drawBar(Graphics2D g, Line2D line, double barWidth) {
        Stroke stroke = new BasicStroke(
                (float) barWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        Shape bar = stroke.createStrokedShape(line);
        g.setColor(new Color(200, 100, 0));
        g.fill(bar);
        g.setColor(Color.BLACK);
        g.draw(bar);
    }

    @Override
    protected void paintComponent(Graphics gr) {

        Graphics2D g = (Graphics2D) gr;

        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        drawLadderBetweenPoints(g, start, end);
    }

    /**
     *
     * @return the starting point of the ladder
     */
    public Point2D getStart() {
        return start;
    }

    /**
     *
     * @return the ending point of the ladder
     */
    public Point2D getEnd() {
        return end;
    }

    /**
     *
     * @param start starting point of the ladder
     * @param end ending point of the ladder
     */
    public void setPoints(Point2D start, Point2D end) {
        this.start = start;
        this.end = end;
    }
}
