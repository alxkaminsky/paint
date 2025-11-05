package ca.utoronto.utm.assignment2.paint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface Shape {
    public void draw(GraphicsContext g2d);
    public void setEndPoint(Point endPoint);
    public void setColour(Color color);
}
