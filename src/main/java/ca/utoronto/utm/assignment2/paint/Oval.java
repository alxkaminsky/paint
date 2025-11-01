package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Oval implements Shape {
    private final Point centre;
    protected Point end;
    private Point upLeftCorner;
    protected double width;
    protected double height;
    protected Color colour;
    public static Color base = Color.ORANGE;

    public Oval(Point centre, Point end, Color colour) {
        this.centre = centre;
        this.end = end;
        this.colour = base;
        calculateHeight();
        calculateWidth();
        calculateUpLeftPoint();
        this.colour = colour;
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
    public void setColour(Color color) {
        this.colour = color;
    }

    @Override
    public void draw(GraphicsContext g2d, boolean preview) {
        g2d.setFill(colour);
        g2d.fillOval(getUpLeftCorner().x, getUpLeftCorner().y, getWidth(), getHeight());

        if (preview)
            g2d.strokeOval(getUpLeftCorner().x, getUpLeftCorner().y, getWidth(), getHeight());
    }
}
