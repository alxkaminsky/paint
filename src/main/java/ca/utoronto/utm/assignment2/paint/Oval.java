package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static ca.utoronto.utm.assignment2.paint.ShapeFactory.withAlpha;

/**
 * Represent an Oval on the Canvas
 */
public class Oval extends Shape {
    private Point upLeftCorner;
    protected double width;
    protected double height;

    /**
     * Constructor for Oval
     *
     * @param start         The center point of the Oval
     * @param end           The end point of the Oval, this is the point which the mouse is released
     * @param fillColour    The fill color of the Oval
     * @param outlineColour The outline color of the Oval
     */
    public Oval(Point start, Point end, Color fillColour, Color outlineColour) {
        super(start, end, fillColour, outlineColour);

        calculateHeight();
        calculateWidth();
        calculateUpLeftPoint();
    }

    /**
     * Calculate the width of the Oval
     */
    public void calculateWidth(){width = 2 * Math.abs((end.x - start.x));}

    /**
     * Calculate the Height of the Oval
     */
    public void calculateHeight(){height = 2 * Math.abs((end.y - start.y));}

    /**
     * Calculate the coordinate upper left point of the bounding box
     */
    public void calculateUpLeftPoint() {
        upLeftCorner = new Point(start.x - width / 2, start.y - height / 2);
    }

    /**
     * Set the end point of the Oval
     *
     * @param end point of the oval. This is where the mouse is released
     */
    @Override
    public void setEndPoint(Point end) {
        this.end = end;
        calculateHeight();
        calculateWidth();
        calculateUpLeftPoint();
    }

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
     * Draw the Oval on the canvas
     *
     * @param g2d GraphicsContext object to draw all properties of the shape
     */
    @Override
    public void draw(GraphicsContext g2d) {
        g2d.setFill(fillColour);
        g2d.fillOval(getUpLeftCorner().x, getUpLeftCorner().y, getWidth(), getHeight());
        g2d.setStroke(outlineColour);
        g2d.setLineWidth(getStrokeWidth());
        g2d.strokeOval(getUpLeftCorner().x, getUpLeftCorner().y, getWidth(), getHeight());
    }

    @Override
    public boolean intersects(Shape other) {
        if (other instanceof Rectangle rect) {
            double closestX = Math.max(rect.getLeftCornerX(), Math.min(start.x, rect.getLeftCornerX() + rect.getWidth()));
            double closestY = Math.max(rect.getLeftCornerY(), Math.min(start.y, rect.getLeftCornerY() + rect.getHeight()));

            double distanceX = start.x - closestX;
            double distanceY = start.y - closestY;

            return (Math.pow(distanceX / (getWidth() / 2), 2) + Math.pow(distanceY / (getHeight() / 2), 2)) <= 1;
        }
        return false;
    }

    @Override
    public void move(double deltaX, double deltaY) {
        start.x += deltaX;
        start.y += deltaY;
        end.x += deltaX;
        end.y += deltaY;
        calculateUpLeftPoint();
    }

    /**
     *
     * @param p
     * @return true if a point is inside of this oval, false otherwise
     */
    @Override
    public boolean contains(Point p) {
        // Ellipse center
        double cx = start.x;
        double cy = start.y;

        // Radii
        double rx = width / 2.0;
        double ry = height / 2.0;

        if (rx == 0 || ry == 0) return false;

        // Normalized ellipse equation
        double dx = p.x - cx;
        double dy = p.y - cy;

        return (dx * dx) / (rx * rx) + (dy * dy) / (ry * ry) <= 1.0;
    }

    @Override
    public Drawable copy() {
        return new Oval(start.copy(), end.copy(), fillColour, outlineColour);
    }

    @Override
    public void setOpacity(double fillOpacity, double outlineOpacity) {
        fillColour = withAlpha(fillColour, fillOpacity*fillColour.getOpacity());
        outlineColour = withAlpha(outlineColour, outlineOpacity*outlineColour.getOpacity());
    }
}