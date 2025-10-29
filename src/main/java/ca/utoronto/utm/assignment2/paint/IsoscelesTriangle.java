package ca.utoronto.utm.assignment2.paint;

public class IsoscelesTriangle extends Triangle {
    public IsoscelesTriangle(Point firstVertex, Point secondVertex){
        super(firstVertex, secondVertex);
        setThirdVertex();
    }

    @Override
    protected void setThirdVertex() {
        // The final vertex that makes up the isosceles triangle has same y as
        // the second vertex and the x that mirrors the x of the second vertex over the
        // line of symmetry.

        // Mirror over this point's x
        double xCoorBasePoint = getFirstVertex().x;
        double xCoorThirdVertex = 2*xCoorBasePoint - getSecondVertex().x;
        Point finalVertex = new Point(xCoorThirdVertex, getSecondVertex().y);
        updateThirdVertex(finalVertex);
    }
}
