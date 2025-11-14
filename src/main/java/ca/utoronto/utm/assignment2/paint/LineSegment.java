package ca.utoronto.utm.assignment2.paint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import static ca.utoronto.utm.assignment2.paint.ShapeFactory.withAlpha;

public abstract class LineSegment implements Drawable{
    protected final ArrayList<Point> points = new ArrayList<>();
    protected Color outlineColour;
    protected double strokeWidth = 2.0;

    /**
     * Constructor for the Squiggle
     * @param start start point of the squiggle (this is where the mouse press is recorded)
     * @param end end point for the squiggle ( this is where a mouse release is recorded)
     * @param outlineColor the color of the line
     */
    public LineSegment(Point start, Point end, Color outlineColor) {
        this.outlineColour = outlineColor;

        if (start != null) {
            points.add(start);
        }
        if (end != null && (end.x != start.x || end.y != start.y)) {
            points.add(end);
        }
    }

    //Overloading used to help with the copy method
    public LineSegment(Color outlineColor){
        this.outlineColour = outlineColor;
    }

    /**
     * Add another point to the current arraylist of point to extend the Squiggle line
     * @param p
     */
    public void addPoint(Point p) {
        if (p != null) {
            points.add(p);
        }
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    /**
     * Set the end point of the squiggle line
     * @param endPoint
     */
    @Override
    public void setEndPoint(Point endPoint) {
        if (endPoint == null) return;

        if (points.isEmpty()) {
            points.add(endPoint);
        } else {
            points.set(points.size() - 1, endPoint);
        }
    }

    /**
     * Set the stroke width for the squiggle
     * @param strokeWidth
     */
    public void setStrokeWidth(double strokeWidth) {
        if (strokeWidth > 0) {
            this.strokeWidth = strokeWidth;
        }
    }

    /**
     * Draw the squiggle on the canvas
     * @param g2d
     */
    public void draw(GraphicsContext g2d) {
        if (points.size() < 2) {
            return;
        }

        g2d.setStroke(outlineColour);
        g2d.setLineWidth(strokeWidth);

        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g2d.strokeLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    public boolean intersects(Shape other) {
        if (!(other instanceof Rectangle rect)) {
            return false;
        }

        double x = rect.getLeftCornerX();
        double y = rect.getLeftCornerY();
        double w = rect.getWidth();
        double h = rect.getHeight();

        // Check if any point lies inside rectangle
        for (Point p : points) {
            if (p.x >= x && p.x <= x + w && p.y >= y && p.y <= y + h) {
                return true;
            }
        }

        // Check if any segment intersects rectangle edges
        Point[] rectCorners = {
                new Point(x, y),
                new Point(x + w, y),
                new Point(x + w, y + h),
                new Point(x, y + h)
        };

        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);

            for (int j = 0; j < 4; j++) {
                Point r1 = rectCorners[j];
                Point r2 = rectCorners[(j + 1) % 4];

                if (segmentsIntersect(p1, p2, r1, r2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if two line segments intersect using parametric line equations
     */
    private boolean segmentsIntersect(Point p1, Point p2, Point p3, Point p4) {
        double dx1 = p2.x - p1.x;
        double dy1 = p2.y - p1.y;
        double dx2 = p4.x - p3.x;
        double dy2 = p4.y - p3.y;

        double det = dx1 * dy2 - dy1 * dx2;

        // Parallel or collinear
        if (Math.abs(det) < 1e-10) {
            return false;
        }

        double dx3 = p3.x - p1.x;
        double dy3 = p3.y - p1.y;

        double t = (dx3 * dy2 - dy3 * dx2) / det;
        double u = (dx3 * dy1 - dy3 * dx1) / det;

        return t >= 0 && t <= 1 && u >= 0 && u <= 1;
    }

    public void move(double deltaX, double deltaY) {
        for (Point p : points) {
            p.x += deltaX;
            p.y += deltaY;
        }
    }

    public void setOpacity(double fillOpacity, double outlineOpacity) {
        outlineColour = withAlpha(outlineColour, outlineOpacity* outlineColour.getOpacity());
    }

    public void drawSelectionOutline(GraphicsContext g) {
        g.setStroke(Color.DODGERBLUE);
        g.setLineWidth(1.0);
        g.setLineDashes(4, 4);

        if (points.isEmpty()) {
            return;
        }

        double padding = 3.0;
        double minX = points.getFirst().x;
        double maxX = points.getFirst().x;
        double minY = points.getFirst().y;
        double maxY = points.getFirst().y;

        for (Point pt : points) {
            if (pt.x < minX) minX = pt.x;
            if (pt.x > maxX) maxX = pt.x;
            if (pt.y < minY) minY = pt.y;
            if (pt.y > maxY) maxY = pt.y;
        }

        double x = minX - padding;
        double y = minY - padding;
        double w = (maxX - minX) + (2 * padding);
        double h = (maxY - minY) + (2 * padding);

        g.strokeRect(x, y, w, h);

        g.setLineDashes(0, 0);
    }

    @Override
    public Drawable copy() {
        LineSegment copy = createInstance(outlineColour);

        for (Point p : points) {
            copy.addPoint(p.copy());
        }
        copy.setStrokeWidth(strokeWidth);
        return copy;
    }

    public abstract LineSegment createInstance(Color outlineColour);
}
