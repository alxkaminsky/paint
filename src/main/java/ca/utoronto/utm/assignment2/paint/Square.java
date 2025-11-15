package ca.utoronto.utm.assignment2.paint;

import javafx.scene.paint.Color;

/**
 * This class represent a square on the canvas.
 */
public class Square extends Rectangle {

    private final Point refStartPoint;

    /**
     * Constructor for a square defined by a start and end point
     * @param start the start point of the square. This is where a mouse press is recorded
     * @param end the end point, there the user release the mouse
     * @param fillColour the fill color for the square
     * @param outlineColour the outline color for the square
     */
    public Square(Point start, Point end, Color fillColour, Color outlineColour) {
        super(start, start, fillColour, outlineColour);
        refStartPoint = start;
        setEndPoint(end);
    }

    /**
     * Set the endpoint for a Rectangle
     * @param endPoint This is where the user release the mouse
     */
    @Override
    public void setEndPoint(Point endPoint) {
        double changeInX = endPoint.x - refStartPoint.x;
        double changeInY = endPoint.y - refStartPoint.y;
        double side = Math.max(Math.abs(changeInX), Math.abs(changeInY));
        double newX = refStartPoint.x;
        double newY = refStartPoint.y;

        if (changeInX >= 0 && changeInY >= 0) {
            newX = refStartPoint.x + side;
            newY = refStartPoint.y + side;
        }
        else if (changeInX >= 0 && changeInY < 0) {
            newX = refStartPoint.x + side;
            newY = refStartPoint.y - side;
        }
        else if (changeInX < 0 && changeInY >= 0) {
            newX = refStartPoint.x - side;
            newY = refStartPoint.y + side;
        }
        else if (changeInX < 0 && changeInY < 0) {
            newX = refStartPoint.x - side;
            newY = refStartPoint.y - side;
        }

        Point adjustedEnd = new Point(newX, newY);
        super.setEndPoint(adjustedEnd);
    }

    @Override
    public Drawable copy() {
        Square copy = new Square(start.copy(), end.copy(), fillColour, outlineColour);
        copy.setStrokeWidth(this.strokeWidth);
        return copy;
    }
}
