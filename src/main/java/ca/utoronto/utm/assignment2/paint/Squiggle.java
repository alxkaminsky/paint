package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Squiggle implements Shape{
    private final List<Point> points = new ArrayList<>();
    private Color outlineColor;
    private double strokeWidth = 2.0;

    public Squiggle(Point start, Point end, Color fillColor, Color outlineColor, String style) {
        this.outlineColor = outlineColor;
        if (start != null) {
            points.add(start);
        }
        if (end != null && (end.x != start.x || end.y != start.y)) {
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
    }

    public void setStrokeWidth(double strokeWidth) {
        if (strokeWidth > 0) {
            this.strokeWidth = strokeWidth;
        }
    }

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
}
