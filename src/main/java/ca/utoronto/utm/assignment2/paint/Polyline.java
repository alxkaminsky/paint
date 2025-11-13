package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the Polyline on the canvas. A polyline is a connected sequence of straight line segments made
 * up from a list of Point
 */
public class Polyline implements Shape{
    private final List<Point> points = new ArrayList<>();
    private Color outlineColour;
    private double strokeWidth = 2.0;

    /**
     * Constructor for the Polyline
     * @param start The first point of the polyline
     * @param end The second point of the polyline
     * @param fillColour
     * @param outlineColour The color of the Polyline
     * @param style
     */
    public Polyline(Point start, Point end, Color fillColour, Color outlineColour, String style) {
        // start/end constructor to match the factory signature
        this.outlineColour = outlineColour;
        if (start != null) {
            points.add(start);
        }
        if (end != null) {
            points.add(end);
        }
    }

    /**
     * Adding another point to the end of the polyline
     * @param p
     */
    public void addPoint(Point p) {
        if (p != null) {
            points.add(p);
        }
    }

    /**
     * Adding the final point to the polyline
     * @param endPoint
     */
    public void setEndPoint(Point endPoint) {
        if (endPoint == null) return;

        if (points.isEmpty()) {
            points.add(endPoint);
        } else {
            points.set(points.size() - 1, endPoint);
        }
    }

    public void setFillColour(Color color) {
        // Polyline is not filled; ignore.
    }

    /**
     * Set the stroke width for the polyline
     * @param width the desired width
     */
    public void setStrokeWidth(double width) {
        if (width > 0) {
            this.strokeWidth = width;
        }
    }

    /**
     *
     * @return the list of point that make up the polyline
     */
    public List<Point> getPoints() {
        return points;
    }

    /**
     * Draw the current polyline to the canvas
     * @param g2d
     */
    public void draw(GraphicsContext g2d) {
        if (points.size() < 2) {
            return;
        }

        g2d.setStroke(outlineColour);
        g2d.setLineWidth(strokeWidth);

        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g2d.strokeLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    public String getStyle() {return null;}

    /**
     * To adhere to Shape factory standard
     * @param p
     * @return false
     */
    @Override
    public boolean contains(Point p) {
        return false;
    }
}
