package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Observable;

/**
 * The model class of the MVC pattern for the Paint application. This class holds all
 * information about the shapes drawn on the canvas. Whenever the model change, it will
 * notify the Observer to call the update() method.
 */
public class PaintModel extends Observable {
    private String mode = "Circle";
    private String style = "Filled";
    private ArrayList<Shape> selected = new ArrayList<Shape>();
    private ArrayList<Shape> clipboard = new ArrayList<Shape>();
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private Shape previewShape;
    private Shape select;
    private Color fillColor = Color.BURLYWOOD;
    private Color outlineColor = Color.BLACK;
    private double currStrokeWidth = 2.0;
    private TextBox activeTextBox;


    /**
     *
     * @return the current drawing mode
     */
    public String getMode() {
        return mode;
    }

    /**
     *
     * @param mode set the drawing mode (circle, oval,...)
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     *
     * @return the current style (filled, outline)
     */
    public String getStyle() {
        return style;
    }

    /**
     * Set the desired style (filled, outline)
     * @param style filled or outline
     */
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * Set the strokeWidth of the outline
     * @param width desired width thickness
     */
    public void setCurrStrokeWidth(double width) {
        if (width > 0) {
            this.currStrokeWidth = width;
            setChanged();
            notifyObservers();
        }
    }

    /**
     *
     * @return the stroke width
     */
    public double getCurrStrokeWidth() {
        return currStrokeWidth;
    }

    /**
     * Add a new point to the current Squiggle line. If the line is not existed, create it
     */
    public void setSelect(Shape select) {
        this.select = select;
        setChanged();
        notifyObservers();
    }

    public Shape getSelect(){return select;}

    public void updateSelect(Point end){
        if (select != null) {
            select.setEndPoint(end);
            setChanged();
            notifyObservers();
        }
    }

    /**
     *
     * @return the list of all created shapes
     */
    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    /**
     * Add another shape to the model
     * @param s the shape being added
     */
    public void addShape(Shape s) {
        if (s == null) return;
        shapes.add(s);
        setChanged();
        notifyObservers();
    }

    /**
    Delete the most recently added shape in the array. This is useful for redo
     */
    public void deleteMostRecentShape() {
        if (!shapes.isEmpty()) {
            shapes.removeLast();
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Delete all shapes and lines on the canvas. This is for clear all feature
     */
    public void deleteAllShapes() {
        shapes.clear();
        setChanged();
        notifyObservers();
    }

    /**
     * Draw all of the shapes held in the model onto the Canvas
     * @param gc
     */
    public void draw(GraphicsContext gc) {
        for (Shape shape : shapes) {
            shape.draw(gc);
        }
    }


    /**
     *
     * @return the preview shape. This is the shape that appears while the user is dragging, and have not yet,
     * released the mouse
     */
    public Shape getPreviewShape() {
        return previewShape;
    }

    /**
     * Set the preview shape to whichever is currently being drawn
     * @param s
     */
    public void setPreviewShape(Shape s) {
        this.previewShape = s;
        setChanged();
        notifyObservers();
    }

    /**
     * Update the preview shape. This is needed, and is called, as the user is dragging the mouse to create constant
     * preview of the shape
     * @param endPoint the current point that the mouse is at
     */
    public void updatePreviewShape(Point endPoint) {
        if (previewShape != null) {
            previewShape.setEndPoint(endPoint);
            setChanged();
            notifyObservers();
        }
    }

    /**
     *
     * @return the fill color of a shape
     */
    public Color getFillColor() {return fillColor;}

    /**
     * Set the fill color of the shape
     * @param color the desired color
     */
    public void setFillColor(Color color) {this.fillColor = color;}

    /**
     *
     * @return the outline color of a shape
     */
    public Color getOutlineColor() {return outlineColor;}

    /**
     * Set the outline color for a shape
     * @param color the desired color
     */
    public void setOutlineColor(Color color) {this.outlineColor = color;}

    public ArrayList<Shape> getSelected() {return selected;}

    public TextBox getActiveTextBox(){
        return activeTextBox;
    }

    public void setActiveTextBox(TextBox tb){
        activeTextBox = tb;
        setChanged();
        notifyObservers();
    }

    public void refresh(){
        setChanged();
        notifyObservers();
    }

    public void copy(){
        clipboard.clear();

        if(selected.isEmpty()){
            clipboard.addAll(shapes);
            return;
        }

        clipboard.addAll(selected);
    }

    public void paste(){
        shapes.addAll(clipboard);
        setChanged();
        notifyObservers();
    }

    public void deleteSelected(){
        shapes.removeAll(selected);
        selected.clear();
        setChanged();
        notifyObservers();
    }

    public void moveSelected(Point start, Point end) {
        double dx = end.x - start.x;
        double dy = end.y - start.y;
        for (Shape s : selected) {
            s.move(dx, dy);
        }
        setChanged();
        notifyObservers();
    }

    public void setOpacitySelected(double fillOpacity, double outlineOpacity){
        for (Shape s: selected) {
            s.setOpacity(fillOpacity, outlineOpacity);
        }
        setChanged();
        notifyObservers();
    }

    public ArrayList<Shape> getClipboard() {return clipboard;}
}