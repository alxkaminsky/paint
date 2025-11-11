package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Oval implements Shape {
    private final Point centre;
    protected Point end;
    private Point upLeftCorner;
    private String style;
    protected double width;
    protected double height;
    protected Color fillColour;
    protected Color outlineColour;
    private double strokeWidth;

    public Oval(Point centre, Point end, Color fillColour, Color outlineColour, String style) {
        this.centre = centre;
        this.end = end;
        this.style = style;
        this.fillColour = fillColour;
        this.outlineColour = outlineColour;
        calculateHeight();
        calculateWidth();
        calculateUpLeftPoint();
    }

    public void calculateWidth(){width = 2 * Math.abs((end.x - centre.x));}

    public void calculateHeight(){height = 2 * Math.abs((end.y - centre.y));}

    public void calculateUpLeftPoint() {
        upLeftCorner = new Point(centre.x - width / 2, centre.y - height / 2);
    }

    @Override
    public void setEndPoint(Point end){
        this.end = end;
        calculateHeight();
        calculateWidth();
        calculateUpLeftPoint();
    }

    public Point getCentre() {return centre;}
    public double getWidth() {return width;}
    public double getHeight() {return height;}
    public Point getUpLeftCorner() {return upLeftCorner;}

    @Override
    public void setFillColour(Color color) {
        this.fillColour = color;
    }

    @Override
    public void draw(GraphicsContext g2d) {
        if(style.equals("Filled")) {
            g2d.setFill(fillColour);
            g2d.fillOval(getUpLeftCorner().x, getUpLeftCorner().y, getWidth(), getHeight());
        }
        g2d.setStroke(outlineColour);
        g2d.setLineWidth(getStrokeWidth());
        g2d.strokeOval(getUpLeftCorner().x, getUpLeftCorner().y, getWidth(), getHeight());
    }

    public void setStrokeWidth(double width) {
        this.strokeWidth = width;
    }

    public double getStrokeWidth() {
        return this.strokeWidth;
    }
}
