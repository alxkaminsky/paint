package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

public interface Drawable {
    public void draw(GraphicsContext g2d);
    public void setEndPoint(Point endPoint);
    public void setOpacity(double fillOpacity, double outlineOpacity);
    public boolean intersects(Shape other);
    public void move(double deltaX, double deltaY);
    public boolean contains(Point p);
    public Drawable copy(); //returns a deep copy of a given drawable
}
