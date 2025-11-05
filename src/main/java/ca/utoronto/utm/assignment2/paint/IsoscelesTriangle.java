package ca.utoronto.utm.assignment2.paint;
import javafx.scene.paint.Color;

public class IsoscelesTriangle extends Triangle {
    public static Color base = Color.RED;

    public IsoscelesTriangle(Point firstVertex, Point secondVertex, Color colour, String style) {
        super(firstVertex, secondVertex, colour, style);
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
}
