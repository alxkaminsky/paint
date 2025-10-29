package ca.utoronto.utm.assignment2.paint;

public class Oval {
    private final Point centre;
    private Point end;
    private Point upLeftCorner;
    private double width;
    private double height;

    public Oval(Point centre, Point end) {
        this.centre = centre;
        this.end = end;
        calculateWidthHeight();
        calculateUpLeftPoint();
    }

    public void calculateWidthHeight(){
        width = 2 * Math.abs((end.x - centre.x));
        height = 2 * Math.abs((end.y - centre.y));
    }

    public void calculateUpLeftPoint() {
        upLeftCorner = new Point(centre.x - width / 2, centre.y - height / 2);
    }

    public void setEndPoint(Point end){
        this.end = end;
        calculateWidthHeight();
        calculateUpLeftPoint();
    }

    public Point getCentre() {return centre;}
    public double getWidth() {return width;}
    public double getHeight() {return height;}
    public Point getUpLeftCorner() {return upLeftCorner;}
}
