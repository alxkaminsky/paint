package ca.utoronto.utm.assignment2.paint;

import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

public class MoveStrategy implements ToolStrategy {
    private Point start;

    @Override
    public void handle(MouseEvent e, PaintModel model) {
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
}
