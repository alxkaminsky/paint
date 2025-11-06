package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;
import javafx.scene.paint.Color;

public class PaintModel extends Observable {
    private String mode = "Circle";
    private String style = "Filled";
    private ArrayList<ArrayList<Point>> points = new ArrayList<ArrayList<Point>>();
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private Shape previewShape;

    private Color currentColor = Color.BLACK;

    public PaintModel() {
        newLine();
    }

    public String getMode() { return mode; }

    public void setMode(String mode) { this.mode = mode; System.out.println(this.mode); }

    public String getStyle() { return style; }

    public void setStyle(String style) { this.style = style; }

    public void setColour(Color c) { if (c != null) this.currentColor = c; }

    public Color getColour() { return this.currentColor; }


    public void addPoint(Point p) {
        points.get(points.size()-1).add(p);
        setChanged(); notifyObservers();
    }

    public void newLine() {
        points.add(new ArrayList<Point>());
        setChanged(); notifyObservers();
    }

    public ArrayList<ArrayList<Point>> getPoints() { return points; }

    public ArrayList<Shape> getShapes() { return shapes; }

    private void addShape(Shape s) {
        shapes.add(s);
        setChanged(); notifyObservers();
    }

    public void commitShape(Point start, Point end) {
        Shape s = ShapeFactory.create(
                this.mode,
                start,
                end,
                this.style
        );
        addShape(s);
    }

    public Shape getPreviewShape() { return previewShape; }

    public void setPreviewShape(Shape s) {
        previewShape = s;
        setChanged(); notifyObservers();
    }

    public void updatePreviewShape(Point endPoint) {
        if (previewShape != null) {
            previewShape.setEndPoint(endPoint);
            setChanged(); notifyObservers();
        }
    }
}