package ca.utoronto.utm.assignment2.paint;

import javafx.scene.paint.Color;

public class Square extends Rectangle {

    private final Point refStartPoint;
    public static Color base = Color.SPRINGGREEN;

    public Square(Point start, Point end, Color colour, String style) {
        super(start, start, colour, style);
        refStartPoint = start;
        setEndPoint(end);
    }

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
}
