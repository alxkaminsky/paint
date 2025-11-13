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

    public String getStyle() {return this.style;}

    /**
     * This is a clever implementation using barycentric properties of triangles. I'm glad I studied Euclidean geometry
     * back in highschool.
     * @param p
     * @return true if the Point is inside of the triangle false otherwise
     */
    @Override
    public boolean contains(Point p) {
        Point a = getStart();
        Point b = getEnd();
        Point c = getThirdVertex();

        double px = p.x;
        double py = p.y;

        double ax = a.x, ay = a.y;
        double bx = b.x, by = b.y;
        double cx = c.x, cy = c.y;

        // Vectors
        double v0x = cx - ax;
        double v0y = cy - ay;
        double v1x = bx - ax;
        double v1y = by - ay;
        double v2x = px - ax;
        double v2y = py - ay;

        // Dot products
        double dot00 = v0x * v0x + v0y * v0y;
        double dot01 = v0x * v1x + v0y * v1y;
        double dot02 = v0x * v2x + v0y * v2y;
        double dot11 = v1x * v1x + v1y * v1y;
        double dot12 = v1x * v2x + v1y * v2y;

        // Compute barycentric coordinates
        double denom = (dot00 * dot11 - dot01 * dot01);
        if (denom == 0) return false; // degenerate triangle

        double invDenom = 1.0 / denom;
        double u = (dot11 * dot02 - dot01 * dot12) * invDenom;
        double v = (dot00 * dot12 - dot01 * dot02) * invDenom;

        return (u >= 0) && (v >= 0) && (u + v <= 1);
    }

}
