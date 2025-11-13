package ca.utoronto.utm.assignment2.paint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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

    public double getWidth(){return width;}
    public double getHeight(){return height;}
    public double getLeftCornerX(){return upLeftCorner.x;}
    public double getLeftCornerY(){return upLeftCorner.y;}

    public Point getStartPoint(){return startPoint;}
    public Point getEndPoint(){return endPoint;}

    private void calculateUpLeftCorner(){
        upLeftCorner = new Point(Math.min(startPoint.x, endPoint.x), Math.min(startPoint.y, endPoint.y));
    }

    @Override
    public void setFillColour(Color color) {
        this.fillColour = color;
    }

    @Override
    public void draw(GraphicsContext g2d) {
        if(style.equals("Filled")) {
            g2d.setFill(fillColour);
            g2d.fillRect(getLeftCornerX(), getLeftCornerY(), getWidth(), getHeight());
            g2d.setStroke(Color.BLACK);
        }
        g2d.setStroke(outlineColour);
        g2d.setLineWidth(getStrokeWidth());
        if(select){
            g2d.setLineDashes(3, 2.5);
        }
        g2d.strokeRect(getLeftCornerX(), getLeftCornerY(), getWidth(), getHeight());
        g2d.setLineDashes(0,0);
    }

    public void setStrokeWidth(double width) {
        this.strokeWidth = width;
    }

    public double getStrokeWidth() {
        return this.strokeWidth;
    }

    @Override
    public boolean intersects(Shape other) {
        if (other instanceof Rectangle otherRect) {
            return this.getLeftCornerX() < otherRect.getLeftCornerX() + otherRect.getWidth() &&
                   this.getLeftCornerX() + this.getWidth() > otherRect.getLeftCornerX() &&
                   this.getLeftCornerY() < otherRect.getLeftCornerY() + otherRect.getHeight() &&
                   this.getLeftCornerY() + this.getHeight() > otherRect.getLeftCornerY();
        }
        // TODO: Implement intersection logic for other shape types
        return false;
    }
}