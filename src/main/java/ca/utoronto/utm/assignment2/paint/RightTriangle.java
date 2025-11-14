package ca.utoronto.utm.assignment2.paint;
import javafx.scene.paint.Color;

/**
 * This class represent a right triangle on the canvas
 */
public class RightTriangle extends Triangle {
    public  RightTriangle(Point firstVertex, Point secondVertex, Color fillColour, Color outlineColour) {
        super(firstVertex, secondVertex, fillColour, outlineColour);
        setThirdVertex();
    }

    @Override
    protected void setThirdVertex() {
        // The final vertex that makes up the right triangle has same x as
        // the first vertex and same y as the second vertex.
        Point basePoint = new Point(getStart().x, getEnd().y);
        updateThirdVertex(basePoint); 
    }

    @Override
    public Drawable copy() {
        return new RightTriangle(start.copy(), end.copy(), fillColour, outlineColour);
    }
}