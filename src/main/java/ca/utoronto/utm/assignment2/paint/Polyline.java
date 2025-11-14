package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

import static ca.utoronto.utm.assignment2.paint.ShapeFactory.withAlpha;

/**
 * This class represents the Polyline on the canvas. A polyline is a connected sequence of straight line segments made
 * up from a list of Point
 */
public class Polyline implements Drawable{
    private final List<Point> points = new ArrayList<>();
    private Color outlineColour;
    private double strokeWidth = 2.0;

    /**
     * Constructor for the Polyline
     * @param start The first point of the polyline
     * @param end The second point of the polyline
     * @param outlineColour The color of the Polyline
     */
    public Polyline(Point start, Point end, Color outlineColour) {
        // start/end constructor to match the factory signature
        this.outlineColour = outlineColour;
        if (start != null) {
            points.add(start);
        }
        if (end != null) {
            points.add(end);
        }
    }

    //Overloading
    public Polyline(Color outlineColour) {
        this.outlineColour = outlineColour;
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

    @Override
    public boolean intersects(Shape other) {
        if (other instanceof Rectangle rect) {
            for (int i = 0; i < points.size() - 1; i++) {
                Point p1 = points.get(i);
                Point p2 = points.get(i + 1);
                if (lineIntersectsRect(p1, p2, rect)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean lineIntersectsRect(Point p1, Point p2, Rectangle rect) {
        // Check if any of the line segment's endpoints are inside the rectangle
        if (rect.getLeftCornerX() <= p1.x && p1.x <= rect.getLeftCornerX() + rect.getWidth() &&
            rect.getLeftCornerY() <= p1.y && p1.y <= rect.getLeftCornerY() + rect.getHeight()) {
            return true;
        }
        if (rect.getLeftCornerX() <= p2.x && p2.x <= rect.getLeftCornerX() + rect.getWidth() &&
            rect.getLeftCornerY() <= p2.y && p2.y <= rect.getLeftCornerY() + rect.getHeight()) {
            return true;
        }

        // Check for intersection with each of the rectangle's sides
        Point[] rectVertices = {
            new Point(rect.getLeftCornerX(), rect.getLeftCornerY()),
            new Point(rect.getLeftCornerX() + rect.getWidth(), rect.getLeftCornerY()),
            new Point(rect.getLeftCornerX() + rect.getWidth(), rect.getLeftCornerY() + rect.getHeight()),
            new Point(rect.getLeftCornerX(), rect.getLeftCornerY() + rect.getHeight())
        };

        for (int i = 0; i < 4; i++) {
            if (lineIntersect(p1, p2, rectVertices[i], rectVertices[(i + 1) % 4])) {
                return true;
            }
        }

        return false;
    }

    private boolean lineIntersect(Point p1, Point p2, Point p3, Point p4) {
        double det = (p2.x - p1.x) * (p4.y - p3.y) - (p4.x - p3.x) * (p2.y - p1.y);
        if (det == 0) {
            return false;
        }
        double lambda = ((p4.y - p3.y) * (p4.x - p1.x) + (p3.x - p4.x) * (p4.y - p1.y)) / det;
        double gamma = ((p1.y - p2.y) * (p4.x - p1.x) + (p2.x - p1.x) * (p4.y - p1.y)) / det;
        return (0 < lambda && lambda < 1) && (0 < gamma && gamma < 1);
    }

    @Override
    public void move(double deltaX, double deltaY){
        for (Point p : points) {
            p.x += deltaX;
            p.y += deltaY;
        }
    }

    @Override
    public void setOpacity(double fillOpacity, double outlineOpacity) {
        outlineColour = withAlpha(outlineColour, outlineOpacity);
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

    @Override
    public Drawable copy() {
        Polyline copy  = new Polyline(outlineColour);
        for (Point p : points) {
            copy.addPoint(p.copy());
        }
        copy.setStrokeWidth(strokeWidth);
        return copy;
    }
}
