package ca.utoronto.utm.assignment2.paint;
import javafx.scene.paint.Color;

public class RightTriangle extends Triangle {
    public static Color base = Color.SALMON;

    public  RightTriangle(Point firstVertex, Point secondVertex, Color colour, String style) {
        super(firstVertex, secondVertex, colour, style);
        setThirdVertex();
    }

    @Override
    protected void setThirdVertex() {
        // The final vertex that makes up the right triangle has same x as
        // the first vertex and same y as the second vertex.
        Point basePoint = new Point(getStart().x, getEnd().y);
        updateThirdVertex(basePoint); 
    }
}