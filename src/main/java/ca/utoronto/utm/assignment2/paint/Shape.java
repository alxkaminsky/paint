package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape implements Drawable {
    protected Point start;
    protected Point end;
    protected Color fillColour;
    protected Color outlineColour;
    protected double strokeWidth;


    public Shape(Point start, Point end, Color fillColour, Color outlineColour) {
        this.start = start;
        this.end = end;
        this.fillColour = fillColour;
        this.outlineColour = outlineColour;
    }

    public void setFillColour(Color fillColour) {
        this.fillColour = fillColour;
    }

    public Color getFillColour() {
        return fillColour;
    }

    /**
     *
     * @return the start point of the triangle
     */
    public Point getStart() {return start;}

    /**
     *
     * @return the end point (where the mouse is released)
     */
    public Point getEnd() {
        return end;
    }

    public void setStart(Point start) {this.start = start;}

    public void setEnd(Point end) {this.end = end;}

    /**
     *
     * @return the final point of the triangle which is calculated accordingly for each type of triangle
     */

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public abstract boolean contains(Point p);
    public abstract void draw(GraphicsContext g);
    public abstract void drawSelectionOutline(GraphicsContext g);
    public abstract void move(double deltaX, double deltaY);
    public abstract boolean intersects(Shape other);
    public abstract void setEndPoint(Point end);
    public abstract void setOpacity(double fillOpacity, double outlineOpacity);
}