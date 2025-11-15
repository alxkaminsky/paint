package ca.utoronto.utm.assignment2.paint;

import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * Strategy for moving selected drawables on the canvas.
 * Allows users to drag selected items to reposition them.
 *
 * @author kamins64
 */
public class MoveStrategy implements ToolStrategy {
    private Point originalStart;
    private Point lastDragPoint;

    @Override
    public void handle(MouseEvent e, PaintModel model) {
        EventType<? extends MouseEvent> type = e.getEventType();
        Point p = new Point(e.getX(), e.getY());

        if (type == MouseEvent.MOUSE_PRESSED) {
            originalStart = p;
            lastDragPoint = p;
            model.setOpacitySelected(0.25, 0.5);
        } else if (type == MouseEvent.MOUSE_DRAGGED) {
            if (lastDragPoint != null && !model.getSelected().isEmpty()) {
               model.move(model.getSelected(), lastDragPoint, p);
               lastDragPoint = p;
            }
        } else if (type == MouseEvent.MOUSE_RELEASED) {
            if (originalStart != null && lastDragPoint != null && !model.getSelected().isEmpty()) {
                // Only create command if there was actual movement
                if (originalStart.x != lastDragPoint.x || originalStart.y != lastDragPoint.y) {
                    Command moveCmd = new MoveCommand(model,
                        new ArrayList<>(model.getSelected()),
                        originalStart,
                        lastDragPoint);
                    model.getCommandHistory().addExecutedCommand(moveCmd);
                }
            }
            originalStart = null;
            lastDragPoint = null;
            model.setOpacitySelected(4, 2);
        }
    }
}
