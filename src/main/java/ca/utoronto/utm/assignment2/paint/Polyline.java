package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Polyline implements Shape{
    private final List<Point> points = new ArrayList<>();
    private Color outlineColour;
    private double strokeWidth = 2.0;

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

    public void addPoint(Point p) {
        if (p != null) {
            points.add(p);
        }
    }

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

    public void setStrokeWidth(double width) {
        if (width > 0) {
            this.strokeWidth = width;
        }
    }

    public List<Point> getPoints() {
        return points;
    }

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
        // https://stackoverflow.com/a/16725714
        double det = (p2.x - p1.x) * (p4.y - p3.y) - (p4.x - p3.x) * (p2.y - p1.y);
        if (det == 0) {
            return false;
        }
        double lambda = ((p4.y - p3.y) * (p4.x - p1.x) + (p3.x - p4.x) * (p4.y - p1.y)) / det;
        double gamma = ((p1.y - p2.y) * (p4.x - p1.x) + (p2.x - p1.x) * (p4.y - p1.y)) / det;
        return (0 < lambda && lambda < 1) && (0 < gamma && gamma < 1);
    }
}
