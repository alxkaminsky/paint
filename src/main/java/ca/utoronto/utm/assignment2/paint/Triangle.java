package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Triangle implements Shape {
    private final Point start;
    private Point end;
    private Point thirdVertex;
    private String style;
    protected Color colour;

    public Triangle(Point start, Point end, Color colour, String style) {
        this.start = start;
        this.end = end;
        this.colour = colour;
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
    public void setColour(Color color) {
        this.colour = color;
    }

    @Override
    public void setEndPoint(Point end) {
        this.end = end;
        setThirdVertex();
    }

    @Override
    public void draw(GraphicsContext g2d) {
        if (style.equals("Filled")) {
            g2d.setFill(colour);
            g2d.fillPolygon(getXAllVertices(), getYAllVertices(), 3);
        }
        else {
            g2d.setStroke(colour);
        }
        g2d.strokePolygon(getXAllVertices(), getYAllVertices(), 3);

    }
}
