package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static ca.utoronto.utm.assignment2.paint.ShapeFactory.withAlpha;

/**
 * Parent class for the triangle shape. Both isosceles and right triangle extends from this class
 */
public abstract class Triangle extends Shape {
    private Point thirdVertex;
    /**
     * Constructor for the triangle on the canvas
     *
     * @param start         the start point (where the mouse press event is recorded)
     * @param end           the end point (where the mouse release event is recorded)
     * @param fillColour    the fill color for the triangle
     * @param outlineColour the outline color for the triangle
     */
    public Triangle(Point start, Point end, Color fillColour, Color outlineColour) {
        super(start, end, fillColour, outlineColour);
        setThirdVertex();
    }

    protected void setThirdVertex() {
    } // Abstract method to be implemented in subclasses

    protected void updateThirdVertex(Point point) {
        this.thirdVertex = point;
    }

    public Point getThirdVertex() {
        return thirdVertex;
    }

    /**
     *
     * @return return all three vertices' x coordinates in an array for help with the fill color method
     */
    public double[] getXAllVertices() {
        double[] points = new double[3];
        points[0] = getStart().x;
        points[1] = getEnd().x;
        points[2] = getThirdVertex().x;
        return points;
    }
    /**
     *
     * @return return all three vertices' y coordinates in an array for help with the fill color method
     */
    public double[] getYAllVertices() {
        double[] points = new double[3];
        points[0] = getStart().y;
        points[1] = getEnd().y;
        points[2] = getThirdVertex().y;
        return points;
    }


    /**
     * Set the end point for the Triangle (when the mouse is released)
     * @param end
     */
    public void setEndPoint(Point end) {
        this.end = end;
        setThirdVertex();
    }

    /**
     * Set the outline thickness for the triangle
     * @param width the desired width
     */
    public void setStrokeWidth(double width) {
        this.strokeWidth = width;
    }

    /**
     *
     * @return the outline thickness
     */
    public double getStrokeWidth() {
        return this.strokeWidth;
    }

    @Override
    public boolean intersects(Shape other) {
        if (other instanceof Rectangle rect) {
            // Check if any of the triangle's vertices are inside the rectangle
            if (rect.getLeftCornerX() <= start.x && start.x <= rect.getLeftCornerX() + rect.getWidth() &&
                rect.getLeftCornerY() <= start.y && start.y <= rect.getLeftCornerY() + rect.getHeight()) {
                return true;
            }
            if (rect.getLeftCornerX() <= end.x && end.x <= rect.getLeftCornerX() + rect.getWidth() &&
                rect.getLeftCornerY() <= end.y && end.y <= rect.getLeftCornerY() + rect.getHeight()) {
                return true;
            }
            if (rect.getLeftCornerX() <= thirdVertex.x && thirdVertex.x <= rect.getLeftCornerX() + rect.getWidth() &&
                rect.getLeftCornerY() <= thirdVertex.y && thirdVertex.y <= rect.getLeftCornerY() + rect.getHeight()) {
                return true;
            }

            // Check if any of the rectangle's vertices are inside the triangle
            Point[] rectVertices = {
                new Point(rect.getLeftCornerX(), rect.getLeftCornerY()),
                new Point(rect.getLeftCornerX() + rect.getWidth(), rect.getLeftCornerY()),
                new Point(rect.getLeftCornerX(), rect.getLeftCornerY() + rect.getHeight()),
                new Point(rect.getLeftCornerX() + rect.getWidth(), rect.getLeftCornerY() + rect.getHeight())
            };
            for (Point v : rectVertices) {
                if (isInside(v)) {
                    return true;
                }
            }

            // Check for line segment intersection
            Point[] triVertices = {start, end, thirdVertex};
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    if (lineIntersect(triVertices[i], triVertices[(i + 1) % 3], rectVertices[j], rectVertices[(j + 1) % 4])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isInside(Point p) {
        // https://stackoverflow.com/a/2049593
        double d1, d2, d3;
        boolean has_neg, has_pos;

        d1 = sign(p, start, end);
        d2 = sign(p, end, thirdVertex);
        d3 = sign(p, thirdVertex, start);

        has_neg = (d1 < 0) || (d2 < 0) || (d3 < 0);
        has_pos = (d1 > 0) || (d2 > 0) || (d3 > 0);

        return !(has_neg && has_pos);
    }

    private double sign(Point p1, Point p2, Point p3) {
        return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
    }

    private boolean lineIntersect(Point p1, Point p2, Point p3, Point p4) {
        // https://stackoverflow.com/a/16725714
        double det = (p2.x - p1.x) * (p4.y - p3.y) - (p4.x - p3.x) * (p2.y - p1.y);
        if (det == 0) {
            return false;
        }
        double lambda = ((p4.y - p3.y) * (p4.x - p1.x) + (p3.x - p4.x) * (p4.y - p1.y)) / det;
        double gamma = ((p1.y - p2.y) * (p4.x - p1.x) + (p2.x - p1.x) * (p4.y - p1.y)) / det;
        return (0 < lambda && lambda < 1) && (0 < gamma && gamma < 1);
    }

    /**
     * Draw the triangle on the canvas
     * @param g2d
     */
    @Override
    public void draw(GraphicsContext g2d) {
        g2d.setFill(fillColour);
        g2d.fillPolygon(getXAllVertices(), getYAllVertices(), 3);
        g2d.setStroke(outlineColour);
        g2d.setLineWidth(getStrokeWidth());
        g2d.strokePolygon(getXAllVertices(), getYAllVertices(), 3);
    }

    @Override
    public void move(double deltaX, double deltaY) {
        start.x += deltaX;
        start.y += deltaY;
        end.x += deltaX;
        end.y += deltaY;
        thirdVertex.x += deltaX;
        thirdVertex.y += deltaY;
    }

    @Override
    public void setOpacity(double fillOpacity, double outlineOpacity) {
        fillColour = withAlpha(fillColour, fillOpacity*fillColour.getOpacity());
        outlineColour = withAlpha(outlineColour, outlineOpacity*outlineColour.getOpacity());
    }

    /**
     * This is a clever implementation using barycentric properties of triangles. I'm glad I studied Euclidean geometry
     * back in highschool.
     * @param p
     * @return true if the Point is inside of the triangle false otherwise
     */
    @Override
    public boolean contains(Point p) {
        Point a = getStart();
        Point b = getEnd();
        Point c = getThirdVertex();

        double px = p.x;
        double py = p.y;

        double ax = a.x, ay = a.y;
        double bx = b.x, by = b.y;
        double cx = c.x, cy = c.y;

        // Vectors
        double v0x = cx - ax;
        double v0y = cy - ay;
        double v1x = bx - ax;
        double v1y = by - ay;
        double v2x = px - ax;
        double v2y = py - ay;

        // Dot products
        double dot00 = v0x * v0x + v0y * v0y;
        double dot01 = v0x * v1x + v0y * v1y;
        double dot02 = v0x * v2x + v0y * v2y;
        double dot11 = v1x * v1x + v1y * v1y;
        double dot12 = v1x * v2x + v1y * v2y;

        // Compute barycentric coordinates
        double denom = (dot00 * dot11 - dot01 * dot01);
        if (denom == 0) return false; // degenerate triangle

        double invDenom = 1.0 / denom;
        double u = (dot11 * dot02 - dot01 * dot12) * invDenom;
        double v = (dot00 * dot12 - dot01 * dot02) * invDenom;

        return (u >= 0) && (v >= 0) && (u + v <= 1);
    }

    @Override
    public void drawSelectionOutline(GraphicsContext g) {
        g.setStroke(Color.DODGERBLUE);
        g.setLineWidth(1.0);
        g.setLineDashes(4, 4);

        double padding = 3.0;
        double[] xs = getXAllVertices();
        double[] ys = getYAllVertices();

        double minX = Math.min(xs[0], Math.min(xs[1], xs[2]));
        double maxX = Math.max(xs[0], Math.max(xs[1], xs[2]));
        double minY = Math.min(ys[0], Math.min(ys[1], ys[2]));
        double maxY = Math.max(ys[0], Math.max(ys[1], ys[2]));

        double x = minX - padding;
        double y = minY - padding;
        double w = (maxX - minX) + (2 * padding);
        double h = (maxY - minY) + (2 * padding);

        g.strokeRect(x, y, w, h);

        g.setLineDashes(0, 0);
    }
}
