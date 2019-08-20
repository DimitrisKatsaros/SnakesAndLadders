package gr.teicrete.edu.tp3692.SNLGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

/**
 * Draws a snake in a panel with given starting and ending points
 *
 * @author user
 */
public class Snake extends JPanel {

    private Point2D head;
    private Point2D tale;

    double bodyWidth = 8;
    int waves = 4;
    double waveHeight = 0.05;
    double tailStart = 0.8;
    double headLength = 20;
    double headWidth = 16;
    double eyeRadius = 6;
    double irisRadius = 3;

    private Shape body;
    private Shape headDraw;
    private Shape eyeR;
    private Shape eyeL;
    private Shape irisR;
    private Shape irisL;

    /**
     * Draws a snake in a panel with given starting and ending points
     *
     * @param head starting poind of the snake
     * @param tale ending poind of the snake
     */
    public Snake(Point2D head, Point2D tale) {
        this.head = head;
        this.tale = tale;

        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics gr) {

        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        setPoints(head, tale);
        draw(g);
    }

    void setPoints(Point2D point0, Point2D point1) {
        this.head.setLocation(point0);
        this.tale.setLocation(point1);

        AffineTransform at = AffineTransform.getRotateInstance(
                currentAngleRad(), point0.getX(), point0.getY());
        at.translate(point0.getX(), point0.getY());

        createBody(at);
        createHead(at);
    }

    void draw(Graphics2D g) {
        g.setColor(new Color(0, 128, 0));
        g.fill(body);
        g.fill(headDraw);
        g.setColor(Color.WHITE);
        g.fill(eyeR);
        g.fill(eyeL);
        g.setColor(Color.BLACK);
        g.fill(irisR);
        g.fill(irisL);
    }

    private void createBody(AffineTransform at) {
        double distance = tale.distance(head);
        int steps = 100;
        Path2D body = new Path2D.Double();
        Point2D previousPoint = null;
        for (int i = 0; i < steps; i++) {
            double alpha = (double) i / (steps - 1);
            Point2D point = computeCenterPoint(alpha, distance);
            if (previousPoint != null) {
                Point2D bodyPoint
                        = computeBodyPoint(alpha, point, previousPoint);
                if (i == 1) {
                    body.moveTo(bodyPoint.getX(), bodyPoint.getY());
                } else {
                    body.lineTo(bodyPoint.getX(), bodyPoint.getY());
                }
            }
            previousPoint = point;
        }
        previousPoint = null;
        for (int i = steps - 1; i >= 0; i--) {
            double alpha = (double) i / (steps - 1);
            Point2D point = computeCenterPoint(alpha, distance);
            if (previousPoint != null) {
                Point2D bodyPoint
                        = computeBodyPoint(alpha, point, previousPoint);
                body.lineTo(bodyPoint.getX(), bodyPoint.getY());
            }
            previousPoint = point;
        }
        this.body = at.createTransformedShape(body);
    }

    private Point2D computeBodyPoint(
            double alpha, Point2D point, Point2D previousPoint) {
        double dx = point.getX() - previousPoint.getX();
        double dy = point.getY() - previousPoint.getY();
        double rdx = -dy;
        double rdy = dx;
        double d = Math.hypot(dx, dy);
        double localBodyWidth = bodyWidth;
        if (alpha > tailStart) {
            localBodyWidth *= (1 - (alpha - tailStart) / (1.0 - tailStart));
        }
        double px = point.getX() + rdx * (1.0 / d) * localBodyWidth;
        double py = point.getY() + rdy * (1.0 / d) * localBodyWidth;
        return new Point2D.Double(px, py);
    }

    private Point2D computeCenterPoint(
            double alpha, double distance) {
        double r = alpha * Math.PI * 2 * waves;
        double verticalScaling = 1 - (alpha * 2 - 1) * (alpha * 2 - 1);
        double y = Math.sin(r) * distance * waveHeight * verticalScaling;
        double x = alpha * distance;
        return new Point2D.Double(x, y);
    }

    private void createHead(AffineTransform at) {
        Shape head = new Ellipse2D.Double(
                -headLength, -headWidth,
                headLength + headLength,
                headWidth + headWidth);
        this.headDraw = at.createTransformedShape(head);

        Shape eyeR = new Ellipse2D.Double(
                -headLength * 0.5 - eyeRadius,
                -headWidth * 0.6 - eyeRadius,
                eyeRadius + eyeRadius,
                eyeRadius + eyeRadius);
        Shape eyeL = new Ellipse2D.Double(
                -headLength * 0.5 - eyeRadius,
                headWidth * 0.6 - eyeRadius,
                eyeRadius + eyeRadius,
                eyeRadius + eyeRadius);
        this.eyeR = at.createTransformedShape(eyeR);
        this.eyeL = at.createTransformedShape(eyeL);

        Shape irisR = new Ellipse2D.Double(
                -headLength * 0.4 - eyeRadius,
                -headWidth * 0.6 - irisRadius,
                irisRadius + irisRadius,
                irisRadius + irisRadius);
        Shape irisL = new Ellipse2D.Double(
                -headLength * 0.4 - eyeRadius,
                headWidth * 0.6 - irisRadius,
                irisRadius + irisRadius,
                irisRadius + irisRadius);
        this.irisR = at.createTransformedShape(irisR);
        this.irisL = at.createTransformedShape(irisL);
    }

    private double currentAngleRad() {
        double dx = tale.getX() - head.getX();
        double dy = tale.getY() - head.getY();
        double angleRad = Math.atan2(dy, dx);
        return angleRad;
    }
}
