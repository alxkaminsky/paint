package ca.utoronto.utm.assignment2.paint;
import javafx.scene.paint.Color;

/**
 * Represent an Isosceles Triangle on the canvas.
 */
public class IsoscelesTriangle extends Triangle {
    public IsoscelesTriangle(Point firstVertex, Point secondVertex, Color fillColour, Color outlineColor) {
        super(firstVertex, secondVertex, fillColour, outlineColor);
        setThirdVertex();
    }

    @Override
    protected void setThirdVertex() {
        // The final vertex that makes up the isosceles triangle has same y as
        // the second vertex and the x that mirrors the x of the second vertex over the
        // line of symmetry.

        // Mirror over this point's x
        double xCoorBasePoint = getStart().x;
        double xCoorThirdVertex = 2*xCoorBasePoint - getEnd().x;
        Point finalVertex = new Point(xCoorThirdVertex, getEnd().y);
        updateThirdVertex(finalVertex);
    }

    @Override
    public Drawable copy() {
        return new IsoscelesTriangle(start.copy(), end.copy(), fillColour, outlineColour);
    }
}
