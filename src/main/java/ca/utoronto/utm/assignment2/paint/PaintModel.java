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
    private ArrayList<Drawable> selected = new ArrayList<Drawable>();
    private ArrayList<Drawable> clipboard = new ArrayList<Drawable>();
    private ArrayList<Drawable> drawables = new ArrayList<Drawable>();
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private Drawable select;
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
    public void setSelect(Drawable select) {
        this.select = select;
        setChanged();
        notifyObservers();
    }

    public Drawable getSelect(){return select;}

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


    public void updateDrawablePoint(Drawable d, Point p){
        if(drawables.contains(d)){
            d.setEndPoint(p);
            setChanged();
            notifyObservers();
        }
    }

    public void updateShapeColour(Shape s){
        s.setFillColour(fillColor);
        setChanged();
        notifyObservers();
    }

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

    public ArrayList<Drawable> getSelected() {return selected;}

    public void copy(){
        clipboard.clear();

        if(selected.isEmpty()){
            clipboard.addAll(drawables);
            return;
        }

        clipboard.addAll(selected);
    }

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

//    public void removeDrawable(Drawable d) {
//        drawables.remove(d);
//        setChanged();
//        notifyObservers();
//    }

    public ArrayList<Shape> getShapes() {return shapes;}
}