package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
    private String mode = "Circle";
    private String style = "Filled";
    private ArrayList<ArrayList<Point>> points = new ArrayList<ArrayList<Point>>();
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private Shape previewShape;
    private Color fillColor = Color.BURLYWOOD;
    private Color outlineColor = Color.BLACK;

    private double currStrokeWidth = 2.0;

    public PaintModel() {
        newLine();
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setCurrStrokeWidth(double width) {
        if (width > 0) {
            this.currStrokeWidth = width;
            setChanged();
            notifyObservers();
        }
    }

    public double getCurrStrokeWidth() {
        return currStrokeWidth;
    }

    public void addPoint(Point p) {
        if (points.isEmpty()) {
            newLine();
        }
        points.get(points.size() - 1).add(p);
        setChanged();
        notifyObservers();
    }

    public void newLine() {
        points.add(new ArrayList<Point>());
        setChanged();
        notifyObservers();
    }

    public ArrayList<ArrayList<Point>> getPoints() {
        return points;
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    private void addShape(Shape s) {
        if (s == null) return;
        shapes.add(s);
        setChanged();
        notifyObservers();
    }

    public void deleteMostRecentShape() {
        if (!shapes.isEmpty()) {
            shapes.removeLast();
            setChanged();
            notifyObservers();
        }
    }

    public void deleteAllShapes() {
        shapes.clear();
        points.clear();
        setChanged();
        notifyObservers();
    }

    public void draw(GraphicsContext gc) {
        for (Shape shape : shapes) {
            shape.draw(gc);
        }
    }

    public void commitShape(Point start, Point end) {
        Shape s = ShapeFactory.create(
                this.mode,
                start,
                end,
                this.fillColor,
                this.outlineColor,
                this.style,
                this.currStrokeWidth
        );
        addShape(s);
    }

    public Shape getPreviewShape() {
        return previewShape;
    }

    public void setPreviewShape(Shape s) {
        this.previewShape = s;
        setChanged();
        notifyObservers();
    }

    public void updatePreviewShape(Point endPoint) {
        if (previewShape != null) {
            previewShape.setEndPoint(endPoint);
            setChanged();
            notifyObservers();
        }
    }

    public Color getFillColor() {return fillColor;}

    public void setFillColor(Color color) {this.fillColor = color;}

    public Color getOutlineColor() {return outlineColor;}

    public void setOutlineColor(Color color) {this.outlineColor = color;}
}