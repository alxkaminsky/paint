package ca.utoronto.utm.assignment2.paint;

public class RightTriangle extends Triangle {
    
    public RightTriangle(Point firstVertex, Point secondVertex){
        super(firstVertex, secondVertex);
        setThirdVertex();
    }

    @Override
    protected void setThirdVertex() {
        // The final vertex that makes up the right triangle has same x as
        // the first vertex and same y as the second vertex.
        Point basePoint = new Point(getFirstVertex().x, getSecondVertex().y);
        updateThirdVertex(basePoint);  // Use the parent class method to set the third vertex
    }
}