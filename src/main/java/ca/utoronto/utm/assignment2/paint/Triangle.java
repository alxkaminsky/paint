package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Parent class for the triangle shape. Both isosceles and right triangle extends from this class
 */
public abstract class Triangle implements Shape {
    private final Point start;
    private Point end;
    private Point thirdVertex;
    private String style;
    protected Color fillColour;
    protected Color outlineColour;
    private double strokeWidth;

    /**
     * Constructor for the triangle on the canvas
     * @param start the start point (where the mouse press event is recorded)
     * @param end the end point (where the mouse release event is recorded)
     * @param fillColour the fill color for the triangle
     * @param outlineColour the outline color for the triangle
     * @param style the style of the triangle (filled or outline)
     */
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

    /**
     *
     * @return the start point of the triangle
     */
    public Point getStart() {
        return start;
    }

    /**
     *
     * @return the end point (where the mouse is released)
     */
    public Point getEnd() {
        return end;
    }

    /**
     *
     * @return the final point of the triangle which is calculated accordingly for each type of triangle
     */
    public Point getThirdVertex() {
        return thirdVertex;
    }

    /**
     *
     * @return return all three vertices' x coordinates in an array for help with the fill color method
     */
    public double[] getXAllVertices() {
        double[] points = new double[3];
        points[0] = getStart().x;
        points[1] = getEnd().x;
        points[2] = getThirdVertex().x;
        return points;
    }
    /**
     *
     * @return return all three vertices' y coordinates in an array for help with the fill color method
     */
    public double[] getYAllVertices() {
        double[] points = new double[3];
        points[0] = getStart().y;
        points[1] = getEnd().y;
        points[2] = getThirdVertex().y;
        return points;
    }

    /**
     * Set the fill color
     * @param color the desired color
     */
    @Override
    public void setFillColour(Color color) {
        this.fillColour = color;
    }

    /**
     * Set the end point for the Triangle (when the mouse is released)
     * @param end
     */
    @Override
    public void setEndPoint(Point end) {
        this.end = end;
        setThirdVertex();
    }

    /**
     * Draw the triangle on the canvas
     * @param g2d
     */
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

    /**
     * Set the outline thickness for the triangle
     * @param width the desired width
     */
    public void setStrokeWidth(double width) {
        this.strokeWidth = width;
    }

    /**
     *
     * @return the outline thickness
     */
    public double getStrokeWidth() {
        return this.strokeWidth;
    }
}
