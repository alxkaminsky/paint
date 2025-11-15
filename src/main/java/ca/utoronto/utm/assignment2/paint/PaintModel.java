package ca.utoronto.utm.assignment2.paint;

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
    private ArrayList<Drawable> selected = new ArrayList<Drawable>();
    private ArrayList<Drawable> clipboard = new ArrayList<Drawable>();
    private ArrayList<Drawable> drawables = new ArrayList<Drawable>();
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private Drawable select;
    private Color fillColor = Color.BURLYWOOD;
    private Color outlineColor = Color.BLACK;
    private double strokeWidth = 2.0;
    private CommandHistory commandHistory = new CommandHistory();

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
     * Set the strokeWidth of the outline
     * @param width desired width thickness
     */
    public void setStrokeWidth(double width) {
        if (width > 0) {
            this.strokeWidth = width;
            setChanged();
            notifyObservers();
        }
    }

    /**
     *
     * @return the stroke width
     */
    public double getStrokeWidth() {
        return strokeWidth;
    }

    /**
     * Add a new point to the current Squiggle line. If the line is not existed, create it
     */
    public void setSelect(Drawable select) {
        this.select = select;
        setChanged();
        notifyObservers();
    }

    /**
     * Get the currently selected drawable
     * @return the current selection
     */
    public Drawable getSelect(){return select;}

    /**
     * Update the end point of the current selection
     * @param end the new end point
     */
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
     * Add a shape to the model
     * @param s the shape to add
     */
    public void addShape(Shape s) {
        if (s == null) return;
        shapes.add(s);
        setChanged();
        notifyObservers();
    }

    /**
     * Update the end point of a drawable
     * @param d the drawable to update
     * @param p the new end point
     */
    public void updateDrawablePoint(Drawable d, Point p){
        if(drawables.contains(d)){
            d.setEndPoint(p);
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Update the opacity of a drawable
     * @param d the drawable to update
     * @param fillOpacity the fill opacity value
     * @param outlineOpacity the outline opacity value
     */
    public void updateDrawableOpacity(Drawable d, double fillOpacity, double outlineOpacity){
        if(drawables.contains(d)){
            d.setOpacity(fillOpacity, outlineOpacity);
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

    /**
     * Get the list of currently selected drawables
     * @return the list of selected drawables
     */
    public ArrayList<Drawable> getSelected() {return selected;}

    /**
     * Copy selected drawables to the clipboard
     */
    public void copy(){
        clipboard.clear();

        if(selected.isEmpty()){
            clipboard.addAll(drawables);
            return;
        }

        clipboard.addAll(selected);
    }

    /**
     * Paste clipboard contents as new drawables
     */
    public void paste(){
        selected.clear();

        for (Drawable drawable : clipboard) {
            Drawable copy =  drawable.copy();
            drawables.add(copy);
            selected.add(copy);
        }

        setChanged();
        notifyObservers();
    }

    /**
     * Delete all currently selected drawables
     */
    public void deleteSelected(){
        drawables.removeAll(selected);
        selected.clear();
        setChanged();
        notifyObservers();
    }

    /**
     * Move drawables from start point to end point
     * @param toMove the list of drawables to move
     * @param start the starting point
     * @param end the ending point
     */
    public void move(ArrayList<Drawable> toMove, Point start, Point end) {
        double dx = end.x - start.x;
        double dy = end.y - start.y;
        for (Drawable d : toMove) {
            d.move(dx, dy);
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Set the opacity of all selected drawables
     * @param fillOpacity the fill opacity value
     * @param outlineOpacity the outline opacity value
     */
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

    /**
     * Get the list of all shapes
     * @return the list of shapes
     */
    public ArrayList<Shape> getShapes() {return shapes;}

    /**
     * Get the command history for undo/redo operations
     * @return the CommandHistory instance
     */
    public CommandHistory getCommandHistory() {return commandHistory;}

    /**
     * Get the clipboard containing copied drawables
     * @return the clipboard ArrayList
     */
    public ArrayList<Drawable> getClipboard() {return clipboard;}
}