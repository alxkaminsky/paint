package ca.utoronto.utm.assignment2.paint;

public class Triangle {
    private final Point start;
    private Point end;
    private Point thirdVertex;

    public Triangle(Point start, Point end){
        this.start = start; // Registered on mouse click
        this.end = end; // Registered on mouse release
        setThirdVertex(); // Abstract method for thirdVertex
    }

    protected void setThirdVertex(){} // Abstract method to be implemented in subclasses

    protected void updateThirdVertex(Point point) { // Setter
        this.thirdVertex = point;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public Point getThirdVertex() {
        return thirdVertex;
    }

    public double[] getXAllVertices() {
        double[] points = new double[3];
        points[0] = getStart().x;
        points[1] = getEnd().x;
        points[2] = getThirdVertex().x;
        return points;
    }

    public double[] getYAllVertices() {
        double[] points = new double[3];
        points[0] = getStart().y;
        points[1] = getEnd().y;
        points[2] = getThirdVertex().y;
        return points;
    }

    public void setEndPoint(Point end) {
        this.end = end;
    }
}
