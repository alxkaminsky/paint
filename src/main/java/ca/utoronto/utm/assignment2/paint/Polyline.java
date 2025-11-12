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
}
