package ca.utoronto.utm.assignment2.paint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static ca.utoronto.utm.assignment2.paint.ShapeFactory.withAlpha;

/**
 * This class represent a rectangle on the canvas.
 */
public class Rectangle implements Shape{
    private final Point startPoint;
    private boolean select = false;
    private Point endPoint;
    private Point upLeftCorner;
    private double width;
    private String style;
    private double height;
    protected Color fillColour;
    protected Color outlineColour;
    private double strokeWidth;

    /**
     * Constructor for a rectangle defined by a start and end point
     * @param start the start point of the rectangle. This is where a mouse press is recorded
     * @param end the end point, there the user release the mouse
     * @param fillColour the fill color for the rectangle
     * @param outlineColour the outline color for the rectangle
     * @param style style of the rectangle (filled or outline)
     */
    public Rectangle(Point start, Point end, Color fillColour, Color outlineColour, String style) {
        startPoint = start;
        endPoint = end;
        width = Math.abs(endPoint.x - startPoint.x);
        height = Math.abs(endPoint.y - startPoint.y);
        this.fillColour = fillColour;
        this.outlineColour = outlineColour;
        this.style = style;
        calculateUpLeftCorner();
    }

    /**
     * Set the endpoint for a Rectangle
     * @param end This is where the user release the mouse
     */
    @Override
    public void setEndPoint(Point end) {
        endPoint = end;
        calculateUpLeftCorner();
        updateWidth();
        updateHeight();
    }

    public void setSelect(boolean select) {this.select = select;}

    private void updateHeight(){
        height = Math.abs(endPoint.y - startPoint.y);
    }

    private void updateWidth(){
        width = Math.abs(endPoint.x - startPoint.x);
    }

    /**
     *
     * @return the width of the rectangle
     */
    public double getWidth(){return width;}

    /**
     *
     * @return the height of the rectangle
     */
    public double getHeight(){return height;}

    /**
     *
     * @return the left corner x coordinate
     */
    public double getLeftCornerX(){return upLeftCorner.x;}

    /**
     *
     * @return the left corner y coordinate
     */
    public double getLeftCornerY(){return upLeftCorner.y;}

    public Point getStartPoint(){return startPoint;}
    public Point getEndPoint(){return endPoint;}

    private void calculateUpLeftCorner(){
        upLeftCorner = new Point(Math.min(startPoint.x, endPoint.x), Math.min(startPoint.y, endPoint.y));
    }


    /**
     * Draw the circle on the canvas
     * @param g2d
     */
    @Override
    public void draw(GraphicsContext g2d) {
        if(style.equals("Filled")) {
            g2d.setFill(fillColour);
            g2d.fillRect(upLeftCorner.x, upLeftCorner.y, width, height);
            g2d.setStroke(Color.BLACK);
        }
        g2d.setStroke(outlineColour);
        g2d.setLineWidth(strokeWidth);
        if(select){
            g2d.setLineDashes(3, 2.5);
        }
        g2d.strokeRect(upLeftCorner.x, upLeftCorner.y, width, height);
        g2d.setLineDashes(0,0);
    }

    /**
     * Set the stroke width for the outline of the circle
     * @param width
     */
    public void setStrokeWidth(double width) {
        this.strokeWidth = width;
    }

    @Override
    public boolean intersects(Shape other) {
        if (other instanceof Rectangle otherRect) {
            return this.getLeftCornerX() < otherRect.getLeftCornerX() + otherRect.getWidth() &&
                   this.getLeftCornerX() + this.getWidth() > otherRect.getLeftCornerX() &&
                   this.getLeftCornerY() < otherRect.getLeftCornerY() + otherRect.getHeight() &&
                   this.getLeftCornerY() + this.getHeight() > otherRect.getLeftCornerY();
        }
        return false;
    }

    @Override
    public void move(double deltaX, double deltaY) {
        upLeftCorner.x += deltaX;
        upLeftCorner.y += deltaY;
    }

    @Override
    public void setOpacity(double fillOpacity, double outlineOpacity) {
        fillColour = withAlpha(fillColour, fillOpacity*fillColour.getOpacity());
        outlineColour = withAlpha(outlineColour, outlineOpacity*outlineColour.getOpacity());
    }
}