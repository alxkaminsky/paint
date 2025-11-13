package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Represent an Oval on the Canvas
 */
public class Oval implements Shape {
    private final Point centre;
    protected Point end;
    private Point upLeftCorner;
    private String style;
    protected double width;
    protected double height;
    protected Color fillColour;
    protected Color outlineColour;
    private double strokeWidth;

    /**
     * Constructor for Oval
     * @param centre The center point of the Oval
     * @param end The end point of the Oval, this is the point which the mouse is released
     * @param fillColour The fill color of the Oval
     * @param outlineColour The outline color of the Oval
     * @param style If the Oval is filled or drawn with outline
     */
    public Oval(Point centre, Point end, Color fillColour, Color outlineColour, String style) {
        this.centre = centre;
        this.end = end;
        this.style = style;
        this.fillColour = fillColour;
        this.outlineColour = outlineColour;
        calculateHeight();
        calculateWidth();
        calculateUpLeftPoint();
    }

    /**
     * Calculate the width of the Oval
     */
    public void calculateWidth(){width = 2 * Math.abs((end.x - centre.x));}

    /**
     * Calculate the Height of the Oval
     */
    public void calculateHeight(){height = 2 * Math.abs((end.y - centre.y));}

    /**
     * Calculate the coordinate upper left point of the bounding box
     */
    public void calculateUpLeftPoint() {
        upLeftCorner = new Point(centre.x - width / 2, centre.y - height / 2);
    }

    /**
     * Set the end point of the Oval
     * @param end point of the oval. This is where the mouse is released
     */
    @Override
    public void setEndPoint(Point end){
        this.end = end;
        calculateHeight();
        calculateWidth();
        calculateUpLeftPoint();
    }

    /**
     *
     * @return The center of the Oval
     */
    public Point getCentre() {return centre;}

    /**
     *
     * @return the width of the Oval
     */
    public double getWidth() {return width;}

    /**
     *
     * @return the height of the Oval
     */
    public double getHeight() {return height;}

    /**
     *
     * @return the upper left corner of the bounding box
     */
    public Point getUpLeftCorner() {return upLeftCorner;}

    /**
     * Set the color of the Oval
     * @param color the color we want to set as
     */
    @Override
    public void setFillColour(Color color) {
        this.fillColour = color;
    }

    /**
     * Draw the Oval on the canvas
     * @param g2d GraphicsContext object to draw all properties of the shape
     */
    @Override
    public void draw(GraphicsContext g2d) {
        if(style.equals("Filled")) {
            g2d.setFill(fillColour);
            g2d.fillOval(getUpLeftCorner().x, getUpLeftCorner().y, getWidth(), getHeight());
        }
        g2d.setStroke(outlineColour);
        g2d.setLineWidth(getStrokeWidth());
        g2d.strokeOval(getUpLeftCorner().x, getUpLeftCorner().y, getWidth(), getHeight());
    }

    /**
     * Set the width of the outline
     * @param width the desired width
     */
    public void setStrokeWidth(double width) {
        this.strokeWidth = width;
    }

    /**
     * Return the stroke width of the shape
     * @return the stroke width
     */
    public double getStrokeWidth() {
        return this.strokeWidth;
    }
}
