package ca.utoronto.utm.assignment2.paint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle implements Shape{
    private final Point startPoint;
    private Point endPoint;
    private Point upLeftCorner;
    private double width;
    private String style;
    private double height;
    protected Color colour;
    public static Color base = Color.BLUE;

    public Rectangle(Point start, Point end, Color colour, String style) {
        startPoint = start;
        endPoint = end;
        width = Math.abs(endPoint.x - startPoint.x);
        height = Math.abs(endPoint.y - startPoint.y);
        this.colour = colour;
        this.style = style;
        calculateUpLeftCorner();
    }

    @Override
    public void setEndPoint(Point end) {
        endPoint = end;
        calculateUpLeftCorner();
        updateWidth();
        updateHeight();
    }

    private void updateHeight(){
        height = Math.abs(endPoint.y - startPoint.y);
    }

    private void updateWidth(){
        width = Math.abs(endPoint.x - startPoint.x);
    }

    public double getWidth(){return width;}
    public double getHeight(){return height;}
    public double getLeftCornerX(){return upLeftCorner.x;}
    public double getLeftCornerY(){return upLeftCorner.y;}

    private void calculateUpLeftCorner(){
        upLeftCorner = new Point(Math.min(startPoint.x, endPoint.x), Math.min(startPoint.y, endPoint.y));
    }

    @Override
    public void setColour(Color color) {
        this.colour = color;
    }

    @Override
    public void draw(GraphicsContext g2d) {
        if(style.equals("Filled")) {
            g2d.setFill(colour);
            g2d.fillRect(getLeftCornerX(), getLeftCornerY(), getWidth(), getHeight());
        }
        else{
            g2d.setStroke(colour);
            g2d.strokeRect(getLeftCornerX(), getLeftCornerY(), getWidth(), getHeight());
        }
    }
}