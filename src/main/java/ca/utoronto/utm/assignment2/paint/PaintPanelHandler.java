package ca.utoronto.utm.assignment2.paint;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * A separate handler class for PaintPanel. This class perform the appropriate actions for every recorded mouse events
 */
public class PaintPanelHandler implements EventHandler<MouseEvent> {

    private final PaintModel model;
    private Point start;
    private Squiggle currentSquiggle;
    private Drawable curr = null;

    /**
     * The constructor that creates the Handler
     * @param model
     */
    public PaintPanelHandler(PaintModel model) {this.model = model;}

    /**
     * Handles all events MOUSR_PRESSED, MOUSE_CLICKED, and MOUSE_RELEASED and draws appropriately according to the
     * current selected mode (Squiggle, PolyLine, or all of the shapes)
     * @param e
     */
    @Override
    public void handle(MouseEvent e) {
        EventType<? extends MouseEvent> type = e.getEventType();
        String mode = model.getMode();

        if ("Squiggle".equals(mode)) {
            handleSquiggle(e);
            return;
        }

        if ("Polyline".equals(mode)) {
            handlePolyline(e);
            return;
        }

        if("Move". equals(mode)) {
            handleMove(e);
            return;
        }

        if ("Bucket".equals(mode)) {
            handleBucket(e);
            return;
        }

        if (type.equals(MouseEvent.MOUSE_PRESSED)) {
            start = new Point(e.getX(), e.getY());

            Color fillColour = model.getFillColor();
            Color outlineColour = model.getOutlineColor();
            double strokeWidth;

            if(mode.equals("Select")){
                fillColour = Color.TRANSPARENT;
                outlineColour = Color.GRAY;
                strokeWidth = 1;
            }
            else{
                strokeWidth = model.getCurrStrokeWidth();
            }
             curr = ShapeFactory.create(
                    mode,
                    start,
                    start,
                    fillColour,
                    outlineColour,
                    strokeWidth);
            if (mode.equals("Select")){
                model.setSelect(curr);
                return;
            }
            model.addDrawable(curr);
            model.updateDrawableOpacity(curr, 0.25, 0.5);
        }

        if (type.equals(MouseEvent.MOUSE_DRAGGED)) {
            if(model.getSelect() != null && mode.equals("Select")){
                model.updateSelect(new Point(e.getX(), e.getY()));
                model.getSelected().clear();

                for(Drawable d: model.getDrawables()){
                    if (d.intersects((Shape) model.getSelect())) {
                        model.getSelected().add(d);
                    }
                }
            }
            model.updateDrawablePoint(curr, new Point(e.getX(), e.getY()));
            return;
        }

        if (type.equals(MouseEvent.MOUSE_RELEASED)) {
            if (start == null || mode.equals("Select")) {
                model.setSelect(null);
                curr = null;
                return;
            }

            if(curr!=null){
                model.updateDrawableOpacity(curr, 4, 2);
            }
            model.addShape((Shape) curr);
            start = null;
            curr = null;
        }
    }
    private void handleMove(MouseEvent e) {
        EventType<? extends MouseEvent> type = e.getEventType();
        Point p = new Point(e.getX(), e.getY());

        if (type == MouseEvent.MOUSE_PRESSED) {
            start = p;
            model.setOpacitySelected(0.25, 0.5);
        } else if (type == MouseEvent.MOUSE_DRAGGED) {
            if (start != null && !model.getSelected().isEmpty()) {
               model.moveSelected(start, p);
               start = p;
            }
        } else if (type == MouseEvent.MOUSE_RELEASED) {
            start = null;
            model.setOpacitySelected(4,2);
        }
    }

    private void handlePolyline(MouseEvent e) {
        EventType<? extends MouseEvent> type = e.getEventType();
        Point p = new Point(e.getX(), e.getY());

        if(p.y < 0 && curr != null){
            Polyline poly = (Polyline) curr;
            ArrayList<Point> points = poly.getPoints();
            model.updateDrawablePoint(poly, points.get(points.size()-2));
            curr = null;
        }

        if (curr == null && MouseEvent.MOUSE_CLICKED == type && MouseButton.PRIMARY == e.getButton()) {
            curr = new Polyline(p, p, model.getOutlineColor());
            model.addDrawable(curr);
        }
        else  if(curr != null && MouseEvent.MOUSE_MOVED == type){
            model.updateDrawablePoint(curr, p);
        }
        else if(curr != null && MouseEvent.MOUSE_CLICKED == type && MouseButton.PRIMARY == e.getButton()){
            Polyline poly = (Polyline) curr;
            poly.getPoints().add(p);
            model.triggerRepaint();
        }
        else if(curr != null && MouseEvent.MOUSE_CLICKED == type && MouseButton.SECONDARY == e.getButton()){
            model.updateDrawablePoint(curr, p);
            curr = null;
        }

    }

    private void handleSquiggle(MouseEvent e) {
        EventType<? extends MouseEvent> type = e.getEventType();
        if (type == MouseEvent.MOUSE_PRESSED && e.getButton() == MouseButton.PRIMARY) {
            Point p = new Point(e.getX(), e.getY());
            currentSquiggle = new Squiggle(p, p, model.getOutlineColor());
            currentSquiggle.setStrokeWidth(model.getCurrStrokeWidth());
            model.addDrawable(currentSquiggle);
            return;
        }

        if (type == MouseEvent.MOUSE_DRAGGED && currentSquiggle != null
                && e.getButton() == MouseButton.PRIMARY) {
            currentSquiggle.addPoint(new Point(e.getX(), e.getY()));
            model.triggerRepaint();
            return;
        }

        if (type == MouseEvent.MOUSE_RELEASED && currentSquiggle != null) {
            currentSquiggle = null;
        }
    }

    private void handleBucket(MouseEvent e) {
        if (e.getEventType() != MouseEvent.MOUSE_CLICKED) return;

        Point click = new Point(e.getX(), e.getY());
        var shapes = model.getShapes();

        // from newest (on top layer) shape to bottom
        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape s = shapes.get(i);

            if(s.contains(click)) {
                model.updateShapeColour(s);
                return;
            }
        }
    }
}