package ca.utoronto.utm.assignment2.paint;

public class Triangle {
    private final Point firstVertex;
    private Point secondVertex;
    private Point thirdVertex;

    public Triangle(Point firstVertex, Point secondVertex){
        this.firstVertex = firstVertex; // Registered on mouse click
        this.secondVertex = secondVertex; // Registered on mouse release
        setThirdVertex(); // Abstract method for thirdVertex
    }

    protected void setThirdVertex(){} // Abstract method to be implemented in subclasses

    protected void updateThirdVertex(Point point) {
        this.thirdVertex = point;
    }

    public Point getFirstVertex() {
        return firstVertex;
    }

    public Point getSecondVertex() {
        return secondVertex;
    }

    public Point getThirdVertex() {
        return thirdVertex;
    }
}
