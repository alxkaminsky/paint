package ca.utoronto.utm.assignment2.paint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The shape interface which all of the shapes have to implement. This is to ensure that all shape classes have to have
 * the draw(), setEndPoint(), and setFillColor() methods.
 */
public interface Shape {
    public void draw(GraphicsContext g2d);
    public void setEndPoint(Point endPoint);
    public void setFillColour(Color color);
    public boolean intersects(Shape other);
}
