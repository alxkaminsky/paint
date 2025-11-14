package ca.utoronto.utm.assignment2.paint;

import javafx.event.EventType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class SquiggleStrategy implements ToolStrategy {
    private Squiggle currentSquiggle;
    @Override
    public void handle(MouseEvent e, PaintModel model) {
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
}
