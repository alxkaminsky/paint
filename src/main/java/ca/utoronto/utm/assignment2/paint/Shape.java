package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Abstract base class for all shapes in the paint application.
 * Defines common properties and behaviors for geometric shapes.
 *
 * @author kamins64
 */
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

    /**
     * Set the fill color of this shape
     * @param fillColour the new fill color
     */
    public void setFillColour(Color fillColour) {
        this.fillColour = fillColour;
    }

    /**
     * Get the fill color of this shape
     * @return the fill color
     */
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

    /**
     * Set the start point of this shape
     * @param start the new start point
     */
    public void setStart(Point start) {this.start = start;}

    /**
     * Set the end point of this shape
     * @param end the new end point
     */
    public void setEnd(Point end) {this.end = end;}

    /**
     *
     * @return the final point of the triangle which is calculated accordingly for each type of triangle
     */

    /**
     * Set the stroke width of this shape's outline
     * @param strokeWidth the new stroke width
     */
    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    /**
     * Get the stroke width of this shape's outline
     * @return the stroke width
     */
    public double getStrokeWidth() {
        return strokeWidth;
    }

    /**
     * Check if this shape contains the given point
     * @param p the point to check
     * @return true if the point is inside this shape
     */
    public abstract boolean contains(Point p);

    /**
     * Draw this shape on the canvas
     * @param g the graphics context to draw on
     */
    public abstract void draw(GraphicsContext g);

    /**
     * Draw a selection outline around this shape
     * @param g the graphics context to draw on
     */
    public abstract void drawSelectionOutline(GraphicsContext g);

    /**
     * Move this shape by the specified deltas
     * @param deltaX the amount to move in the x direction
     * @param deltaY the amount to move in the y direction
     */
    public abstract void move(double deltaX, double deltaY);

    /**
     * Check if this shape intersects with another shape
     * @param other the shape to check intersection with
     * @return true if they intersect, false otherwise
     */
    public abstract boolean intersects(Shape other);

    /**
     * Set the end point of this shape
     * @param end the new end point
     */
    public abstract void setEndPoint(Point end);

    /**
     * Set the opacity of fill and outline colors
     * @param fillOpacity the fill opacity value
     * @param outlineOpacity the outline opacity value
     */
    public abstract void setOpacity(double fillOpacity, double outlineOpacity);
}