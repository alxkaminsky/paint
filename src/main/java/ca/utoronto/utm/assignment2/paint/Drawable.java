package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

/**
 * Interface for all drawable objects in the paint application.
 * Part of the Strategy pattern for rendering different types of drawables.
 */
public interface Drawable {
    /**
     * Draw this drawable on the canvas
     * @param g2d the graphics context to draw on
     */
    public void draw(GraphicsContext g2d);

    /**
     * Draw a selection outline around this drawable
     * @param g the graphics context to draw on
     */
    public void drawSelectionOutline(GraphicsContext g);

    /**
     * Update the end point of this drawable
     * @param endPoint the new end point
     */
    public void setEndPoint(Point endPoint);

    /**
     * Set the opacity of fill and outline colors
     * @param fillOpacity the fill opacity value
     * @param outlineOpacity the outline opacity value
     */
    public void setOpacity(double fillOpacity, double outlineOpacity);

    /**
     * Check if this drawable intersects with another shape
     * @param other the shape to check intersection with
     * @return true if they intersect, false otherwise
     */
    public boolean intersects(Shape other);

    /**
     * Move this drawable by the specified deltas
     * @param deltaX the amount to move in the x direction
     * @param deltaY the amount to move in the y direction
     */
    public void move(double deltaX, double deltaY);

    /**
     * Create a deep copy of this drawable
     * @return a new drawable instance with the same properties
     */
    public Drawable copy();
}
