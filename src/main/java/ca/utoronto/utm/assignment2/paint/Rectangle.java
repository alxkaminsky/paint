package ca.utoronto.utm.assignment2.paint;

public class Rectangle {
    private Point startPoint;
    private Point endPoint;
    private double leftCornerX;
    private double leftCornerY;
    private double width;
    private double height;

    public Rectangle(Point start, Point end) {
        this.startPoint = start;
        this.endPoint = end;
        this.width = Math.abs(this.endPoint.x - this.startPoint.x);
        this.height = Math.abs(this.endPoint.y - this.startPoint.y);
        this.leftCornerX = Math.min(this.startPoint.x, this.endPoint.x);
        this.leftCornerY = Math.min(this.startPoint.y, this.endPoint.y);
    }


    public void setStartPoint(Point start) {
        this.startPoint = start;
        updateHeight();
        updateWidth();
    }
    public void setEndPoint(Point end) {
        this.endPoint = end;
        updateWidth();
        updateHeight();
    }

    private void updateHeight(){
        this.height = Math.abs(this.endPoint.y - this.startPoint.y);
        this.leftCornerY = Math.min(this.startPoint.y, this.endPoint.y);
    }

    private void updateWidth(){
        this.width = Math.abs(this.endPoint.x - this.startPoint.x);
        this.leftCornerX = Math.min(this.startPoint.x, this.endPoint.x);
    }

    public double getWidth(){return this.width;}
    public double getHeight(){return this.height;}

    public double getLeftCornerX(){return this.leftCornerX;}
    public double getLeftCornerY(){return this.leftCornerY;}
}
