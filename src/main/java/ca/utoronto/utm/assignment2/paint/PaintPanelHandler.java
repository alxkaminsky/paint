package ca.utoronto.utm.assignment2.paint;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import static ca.utoronto.utm.assignment2.paint.ShapeFactory.withAlpha;

/**
 * A separate handler class for PaintPanel. This class perform the appropriate actions for every recorded mouse events
 */
public class PaintPanelHandler implements EventHandler<MouseEvent> {

    private final PaintModel model;
    private Point start;
    private Polyline currentPolyline;
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
            model.updateDrawable(curr, new Point(e.getX(), e.getY()));
            return;
        }

        if (type.equals(MouseEvent.MOUSE_RELEASED)) {
            if (start == null || mode.equals("Select")) {
                model.setSelect(null);
                return;
            }

            if(curr!=null){
                model.updateDrawableOpacity(curr, 4, 2);
            }
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

        if (type == MouseEvent.MOUSE_EXITED && currentPolyline != null) {
            if (e.getY() < 0) {
                currentPolyline.setEndPoint(currentPolyline.getPoints().get(currentPolyline.getPoints().size() - 2));
                finishPolyline();
            }
            return;
        }

        if (currentPolyline != null) {
            boolean finish =
                    (type == MouseEvent.MOUSE_CLICKED &&
                            (e.getButton() == MouseButton.SECONDARY || e.getClickCount() == 2))
                            || (type == MouseEvent.MOUSE_PRESSED && e.getButton() == MouseButton.SECONDARY);

            if (finish) {
                currentPolyline.setOpacity(4, 2);
                finishPolyline();
                return;
            }
        }

        if (type == MouseEvent.MOUSE_CLICKED && e.getButton() == MouseButton.PRIMARY) {
            Point p = new Point(e.getX(), e.getY());

            if (currentPolyline == null) {
                currentPolyline = new Polyline(start, start, model.getOutlineColor());
                currentPolyline.setOpacity(0.25, 0.5);
            } else {
                currentPolyline.setEndPoint(p);
                currentPolyline.addPoint(p);
            }
            model.addDrawable(currentPolyline);
            return;
        }

        if ((type == MouseEvent.MOUSE_MOVED || type == MouseEvent.MOUSE_DRAGGED)
                && currentPolyline != null) {

            model.updateDrawable(currentPolyline, new Point(e.getX(), e.getY()));

        }
    }

    private void finishPolyline() {
        if (currentPolyline == null) return;

        if (currentPolyline.getPoints().size() >= 2) {
            int last = currentPolyline.getPoints().size() - 1;
            Point pLast = currentPolyline.getPoints().get(last);
            Point pPrev = currentPolyline.getPoints().get(last - 1);
            if (pLast.x == pPrev.x && pLast.y == pPrev.y) {
                currentPolyline.getPoints().remove(last);
            }
        }

        currentPolyline = null;
    }

    private void handleSquiggle(MouseEvent e) {
        EventType<? extends MouseEvent> type = e.getEventType();
        if (type == MouseEvent.MOUSE_PRESSED && e.getButton() == MouseButton.PRIMARY) {
            Point p = new Point(e.getX(), e.getY());
            currentSquiggle = new Squiggle(
                    p,
                    p,
                    model.getOutlineColor()
            );
            currentSquiggle.setStrokeWidth(model.getCurrStrokeWidth());
            return;
        }

        if (type == MouseEvent.MOUSE_DRAGGED && currentSquiggle != null
                && e.getButton() == MouseButton.PRIMARY) {
            currentSquiggle.addPoint(new Point(e.getX(), e.getY()));
            return;
        }

        if (type == MouseEvent.MOUSE_RELEASED && currentSquiggle != null) {
            model.addDrawable(currentSquiggle);
            currentSquiggle = null;
        }
    }

    private void handleBucket(MouseEvent e) {
        if (e.getEventType() != MouseEvent.MOUSE_CLICKED) return;

        Point click = new Point(e.getX(), e.getY());
        var shapes = model.getDrawables();

        // from newest (on top layer) shape to bottom
        for (int i = shapes.size() - 1; i >= 0; i--) {
            Drawable d = shapes.get(i);

            if ((d instanceof Shape) && (d.contains(click))) {
                Shape s = (Shape) d;
                s.setFillColour(model.getFillColor());
                //TODO fix this code
                model.triggerRepaint(); // We don't want to add notifier to model.setFillColour()
                return;
            }

        }
    }
}