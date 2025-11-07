package ca.utoronto.utm.assignment2.paint;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

public class PaintPanelHandler implements EventHandler<MouseEvent> {

    private final PaintModel model;
    private Point start;

    public PaintPanelHandler(PaintModel model) {
        this.model = model;
    }

    @Override
    public void handle(MouseEvent e) {
        EventType<? extends MouseEvent> type = e.getEventType();
        String mode = model.getMode();

        if ("Squiggle".equals(mode)) {
            if (type.equals(MouseEvent.MOUSE_PRESSED)) {
                model.newLine();
            } else if (type.equals(MouseEvent.MOUSE_DRAGGED)) {
                model.addPoint(new Point(e.getX(), e.getY()));
            } else if (type.equals(MouseEvent.MOUSE_RELEASED)) {
                model.newLine();
            }
            return;
        }

        if ("Polyline".equals(mode)) {
            return;
        }

        if (type.equals(MouseEvent.MOUSE_PRESSED)) {
            start = new Point(e.getX(), e.getY());

            Shape preview = ShapeFactory.createPreview(
                    mode,
                    start,
                    start,
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
}