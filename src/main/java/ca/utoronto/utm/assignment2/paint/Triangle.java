package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Triangle implements Shape {
    private final Point start;
    private Point end;
    private Point thirdVertex;
    private String style;
    protected Color fillColour;
    protected Color outlineColour;
    private double strokeWidth;

    public Triangle(Point start, Point end, Color fillColour, Color outlineColour, String style) {
        this.start = start;
        this.end = end;
        this.fillColour = fillColour;
        this.outlineColour = outlineColour;
        this.style = style;
        setThirdVertex();
    }

    protected void setThirdVertex() {
    } // Abstract method to be implemented in subclasses

    protected void updateThirdVertex(Point point) {
        this.thirdVertex = point;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public Point getThirdVertex() {
        return thirdVertex;
    }

    public double[] getXAllVertices() {
        double[] points = new double[3];
        points[0] = getStart().x;
        points[1] = getEnd().x;
        points[2] = getThirdVertex().x;
        return points;
    }

    public double[] getYAllVertices() {
        double[] points = new double[3];
        points[0] = getStart().y;
        points[1] = getEnd().y;
        points[2] = getThirdVertex().y;
        return points;
    }

    @Override
    public void setFillColour(Color color) {
        this.fillColour = color;
    }

    @Override
    public void setEndPoint(Point end) {
        this.end = end;
        setThirdVertex();
    }

    @Override
    public void draw(GraphicsContext g2d) {
        if (style.equals("Filled")) {
            g2d.setFill(fillColour);
            g2d.fillPolygon(getXAllVertices(), getYAllVertices(), 3);
            g2d.setStroke(Color.BLACK);
        }
        g2d.setStroke(outlineColour);
        g2d.setLineWidth(getStrokeWidth());
        g2d.strokePolygon(getXAllVertices(), getYAllVertices(), 3);

    }

    public void setStrokeWidth(double width) {
        this.strokeWidth = width;
    }

    public double getStrokeWidth() {
        return this.strokeWidth;
    }

    @Override
    public boolean intersects(Shape other) {
        if (other instanceof Rectangle rect) {
            // Check if any of the triangle's vertices are inside the rectangle
            if (rect.getLeftCornerX() <= start.x && start.x <= rect.getLeftCornerX() + rect.getWidth() &&
                rect.getLeftCornerY() <= start.y && start.y <= rect.getLeftCornerY() + rect.getHeight()) {
                return true;
            }
            if (rect.getLeftCornerX() <= end.x && end.x <= rect.getLeftCornerX() + rect.getWidth() &&
                rect.getLeftCornerY() <= end.y && end.y <= rect.getLeftCornerY() + rect.getHeight()) {
                return true;
            }
            if (rect.getLeftCornerX() <= thirdVertex.x && thirdVertex.x <= rect.getLeftCornerX() + rect.getWidth() &&
                rect.getLeftCornerY() <= thirdVertex.y && thirdVertex.y <= rect.getLeftCornerY() + rect.getHeight()) {
                return true;
            }

            // Check if any of the rectangle's vertices are inside the triangle
            Point[] rectVertices = {
                new Point(rect.getLeftCornerX(), rect.getLeftCornerY()),
                new Point(rect.getLeftCornerX() + rect.getWidth(), rect.getLeftCornerY()),
                new Point(rect.getLeftCornerX(), rect.getLeftCornerY() + rect.getHeight()),
                new Point(rect.getLeftCornerX() + rect.getWidth(), rect.getLeftCornerY() + rect.getHeight())
            };
            for (Point v : rectVertices) {
                if (isInside(v)) {
                    return true;
                }
            }

            // Check for line segment intersection
            Point[] triVertices = {start, end, thirdVertex};
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    if (lineIntersect(triVertices[i], triVertices[(i + 1) % 3], rectVertices[j], rectVertices[(j + 1) % 4])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isInside(Point p) {
        // https://stackoverflow.com/a/2049593
        double d1, d2, d3;
        boolean has_neg, has_pos;

        d1 = sign(p, start, end);
        d2 = sign(p, end, thirdVertex);
        d3 = sign(p, thirdVertex, start);

        has_neg = (d1 < 0) || (d2 < 0) || (d3 < 0);
        has_pos = (d1 > 0) || (d2 > 0) || (d3 > 0);

        return !(has_neg && has_pos);
    }

    private double sign(Point p1, Point p2, Point p3) {
        return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
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
