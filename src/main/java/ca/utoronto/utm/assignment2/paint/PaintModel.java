package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Observable;

/**
 * The model class of the MVC pattern for the Paint application. This class holds all
 * information about the drawables drawn on the canvas. Whenever the model change, it will
 * notify the Observer to call the update() method.
 */
public class PaintModel extends Observable {
    private String mode = "Circle";
    private String style = "Filled";
    private ArrayList<Drawable> selected = new ArrayList<Drawable>();
    private ArrayList<Drawable> clipboard = new ArrayList<Drawable>();
    private ArrayList<Drawable> drawables = new ArrayList<Drawable>();
    private Drawable previewShape;
    private Shape select;
    private Color fillColor = Color.BURLYWOOD;
    private Color outlineColor = Color.BLACK;
    private double currStrokeWidth = 2.0;

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
     * @return the list of all created drawables
     */
    public ArrayList<Drawable> getDrawables() {return drawables;}

    /**
     * Add another shape to the model
     * @param d the drawable being added
     */
    public void addDrawable(Drawable d) {
        if (d == null) return;
        drawables.add(d);
        setChanged();
        notifyObservers();
    }

    /**
    Delete the most recently added shape in the array. This is useful for redo
     */
    public void deleteMostRecentShape() {
        if (!drawables.isEmpty()) {
            drawables.removeLast();
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Delete all drawables and lines on the canvas. This is for clear all feature
     */
    public void deleteAllShapes() {
        drawables.clear();
        setChanged();
        notifyObservers();
    }

    /**
     * Draw all of the drawables held in the model onto the Canvas
     * @param gc
     */
    public void draw(GraphicsContext gc) {
        for (Drawable drawable : drawables) {
            drawable.draw(gc);
        }
    }


    /**
     *
     * @return the preview shape. This is the shape that appears while the user is dragging, and have not yet,
     * released the mouse
     */
    public Drawable getPreviewShape() {
        return previewShape;
    }

    /**
     * Set the preview shape to whichever is currently being drawn
     * @param s
     */
    public void setPreviewShape(Drawable s) {
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

    public ArrayList<Drawable> getSelected() {return selected;}

    public void refresh(){
        setChanged();
        notifyObservers();
    }

    public void copy(){
        clipboard.clear();

        if(selected.isEmpty()){
            clipboard.addAll(drawables);
            return;
        }

        clipboard.addAll(selected);
    }

    public void paste(){
        drawables.addAll(clipboard);

        setChanged();
        notifyObservers();
    }

    public void deleteSelected(){
        drawables.removeAll(selected);
        selected.clear();
        setChanged();
        notifyObservers();
    }

    public void moveSelected(Point start, Point end) {
        double dx = end.x - start.x;
        double dy = end.y - start.y;
        for (Drawable d : selected) {
            d.move(dx, dy);
        }
        setChanged();
        notifyObservers();
    }

    public void setOpacitySelected(double fillOpacity, double outlineOpacity){
        for (Drawable d: selected) {
            d.setOpacity(fillOpacity, outlineOpacity);
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Trigger a repaint of the canvas by notifying observers
     */
    public void triggerRepaint() {
        setChanged();
        notifyObservers();
    }
}