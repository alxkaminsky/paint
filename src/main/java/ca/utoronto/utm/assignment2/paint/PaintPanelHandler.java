package ca.utoronto.utm.assignment2.paint;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * A separate handler class for PaintPanel. This class perform the appropriate actions for every recorded mouse events
 */
public class PaintPanelHandler implements EventHandler<MouseEvent> {

    private final PaintModel model;
    private Point start;
    private Polyline currentPolyline;
    private Squiggle currentSquiggle;

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

        if ("Bucket".equals(mode)) {
            handleBucket(e);
            return;
        }

        if (type.equals(MouseEvent.MOUSE_PRESSED)) {
            start = new Point(e.getX(), e.getY());

            Shape preview = ShapeFactory.createPreview(
                    mode,
                    start,
                    start,
                    model.getFillColor(),
                    model.getOutlineColor(),
                    model.getStyle(),
                    model.getCurrStrokeWidth());
            model.setPreviewShape(preview);
            return;
        }

        if (type.equals(MouseEvent.MOUSE_DRAGGED)) {
            if (model.getPreviewShape() != null) {
                model.updatePreviewShape(new Point(e.getX(), e.getY()));
            }
            return;
        }

        if (type.equals(MouseEvent.MOUSE_RELEASED)) {
            if (start == null) {
                return;
            }

            Point end = new Point(e.getX(), e.getY());

            model.commitShape(start, end);

            model.setPreviewShape(null);
            start = null;
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
                finishPolyline();
                return;
            }
        }

        if (type == MouseEvent.MOUSE_CLICKED && e.getButton() == MouseButton.PRIMARY) {
            Point p = new Point(e.getX(), e.getY());

            if (currentPolyline == null) {
                currentPolyline = (Polyline) ShapeFactory.create(
                        "Polyline",
                        p,
                        p,
                        model.getFillColor(),
                        model.getOutlineColor(),
                        model.getStyle(),
                        model.getCurrStrokeWidth()
                );
            } else {
                currentPolyline.setEndPoint(p);
                currentPolyline.addPoint(p);
            }

            model.setPreviewShape(currentPolyline);
            return;
        }

        if ((type == MouseEvent.MOUSE_MOVED || type == MouseEvent.MOUSE_DRAGGED)
                && currentPolyline != null) {

            currentPolyline.setEndPoint(new Point(e.getX(), e.getY()));
            model.setPreviewShape(currentPolyline);
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

        if (currentPolyline.getPoints().size() >= 2) {
            model.addShape(currentPolyline);
        }

        currentPolyline = null;
        model.setPreviewShape(null);
    }

    private void handleSquiggle(MouseEvent e) {
        EventType<? extends MouseEvent> type = e.getEventType();
        if (type == MouseEvent.MOUSE_PRESSED && e.getButton() == MouseButton.PRIMARY) {
            Point p = new Point(e.getX(), e.getY());
            currentSquiggle = new Squiggle(
                    p,
                    p,
                    model.getFillColor(),
                    model.getOutlineColor(),
                    model.getStyle()
            );
            currentSquiggle.setStrokeWidth(model.getCurrStrokeWidth());
            model.setPreviewShape(currentSquiggle);
            return;
        }

        if (type == MouseEvent.MOUSE_DRAGGED && currentSquiggle != null
                && e.getButton() == MouseButton.PRIMARY) {
            currentSquiggle.addPoint(new Point(e.getX(), e.getY()));
            model.setPreviewShape(currentSquiggle);
            return;
        }

        if (type == MouseEvent.MOUSE_RELEASED && currentSquiggle != null) {
            model.addShape(currentSquiggle);
            currentSquiggle = null;
            model.setPreviewShape(null);
        }
    }

    private void handleBucket(MouseEvent e) {
        if (e.getEventType() != MouseEvent.MOUSE_CLICKED) return;

        Point click = new Point(e.getX(), e.getY());
        var shapes = model.getShapes();

        // from topmost to bottom
        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape s = shapes.get(i);

            if (s.contains(click)) {

                // Only fill if shape is actually "Filled"
                if (!s.getStyle().equals("Outline")) {
                    s.setFillColour(model.getFillColor());
                }

                return; // stop after topmost match
            }
        }
    }


}