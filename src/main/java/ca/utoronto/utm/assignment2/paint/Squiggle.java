package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

import static ca.utoronto.utm.assignment2.paint.ShapeFactory.withAlpha;

/**
 * This class represent the Squiggle drawn on the canvas
 */
public class Squiggle implements Shape{
    private final List<Point> points = new ArrayList<>();
    private Color outlineColor;
    private double strokeWidth = 2.0;

    /**
     * Constructor for the Squiggle
     * @param start start point of the squiggle (this is where the mouse press is recorded)
     * @param end end point for the squiggle ( this is where a mouse release is recorded)
     * @param fillColor not needed, present to adhere to Shape factory standard
     * @param outlineColor the color of the line
     * @param style not needed, present to adhere to Shape factory standard
     */
    public Squiggle(Point start, Point end, Color fillColor, Color outlineColor, String style) {
        this.outlineColor = outlineColor;
        if (start != null) {
            points.add(start);
        }
        if (end != null && (end.x != start.x || end.y != start.y)) {
            points.add(end);
        }
    }

    /**
     * Add another point to the current arraylist of point to extend the Squiggle line
     * @param p
     */
    public void addPoint(Point p) {
        if (p != null) {
            points.add(p);
        }
    }

    /**
     * Set the end point of the squiggle line
     * @param endPoint
     */
    @Override
    public void setEndPoint(Point endPoint) {
        if (endPoint == null) return;

        if (points.isEmpty()) {
            points.add(endPoint);
        } else {
            points.set(points.size() - 1, endPoint);
        }
    }

    /**
     * Set the stroke width for the squiggle
     * @param strokeWidth
     */
    public void setStrokeWidth(double strokeWidth) {
        if (strokeWidth > 0) {
            this.strokeWidth = strokeWidth;
        }
    }

    /**
     * Draw the squiggle on the canvas
     * @param g2d
     */
    @Override
    public void draw(GraphicsContext g2d) {
        if (points.size() < 2) {
            return;
        }

        g2d.setStroke(outlineColor);
        g2d.setLineWidth(strokeWidth);

        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g2d.strokeLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    @Override
    public boolean intersects(Shape other) {
        if (other instanceof Rectangle rect) {

            double x = rect.getLeftCornerX();
            double y = rect.getLeftCornerY();
            double w = rect.getWidth();
            double h = rect.getHeight();

            for(Point p : points) {
                if (x <= p.x && p.x <= x + w && y <= p.y && p.y <= y + h) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void move(double deltaX, double deltaY) {
        for (Point p : points) {
            p.x += deltaX;
            p.y += deltaY;
        }
    }

    @Override
    public void setOpacity(double fillOpacity, double outlineOpacity) {
        outlineColor = withAlpha(outlineColor, outlineOpacity*outlineColor.getOpacity());
    }
}
