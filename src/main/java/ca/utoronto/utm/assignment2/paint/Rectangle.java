package ca.utoronto.utm.assignment2.paint;

public class Rectangle {
    private Point startPoint;
    private Point endPoint;
    private Point upLeftCorner;
    private double width;
    private double height;

    public Rectangle(Point start, Point end) {
        startPoint = start;
        endPoint = end;
        width = Math.abs(endPoint.x - startPoint.x);
        height = Math.abs(endPoint.y - startPoint.y);
        upLeftCorner = new Point(Math.min(startPoint.x, endPoint.x), Math.min(startPoint.y, endPoint.y));
    }


    public void setStartPoint(Point start) {
        startPoint = start;
        updateHeight();
        updateWidth();
    }
    public void setEndPoint(Point end) {
        endPoint = end;
        updateWidth();
        updateHeight();
    }

    private void updateHeight(){
        height = Math.abs(endPoint.y - startPoint.y);
        double currX = upLeftCorner.x;
        upLeftCorner = new Point(currX, Math.min(startPoint.y, endPoint.y));
    }

    private void updateWidth(){
        width = Math.abs(endPoint.x - startPoint.x);
        double currY = upLeftCorner.y;
        upLeftCorner = new Point(Math.min(startPoint.x, endPoint.x), currY);
    }

    public double getWidth(){return width;}
    public double getHeight(){return height;}

    public double getLeftCornerX(){return upLeftCorner.x;}
    public double getLeftCornerY(){return upLeftCorner.y;}
}