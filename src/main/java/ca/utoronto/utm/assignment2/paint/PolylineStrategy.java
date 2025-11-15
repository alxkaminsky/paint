package ca.utoronto.utm.assignment2.paint;

import javafx.event.EventType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * Strategy for drawing polylines (multi-segment lines).
 * Allows users to click multiple points to create connected line segments.
 * Right-click to finish the polyline.
 *
 * @author kamins64
 */
public class PolylineStrategy implements ToolStrategy {
    private Polyline currentPolyline;

    /**
     * Handle mouse events for polyline drawing. Part of the Strategy pattern.
     * @param e the mouse event
     * @param model the paint model
     */
    @Override
    public void handle(MouseEvent e, PaintModel model) {
        EventType<? extends MouseEvent> type = e.getEventType();
        Point p = new Point(e.getX(), e.getY());

        if(p.y < 0 && currentPolyline != null){
            ArrayList<Point> points = currentPolyline.getPoints();
            model.updateDrawablePoint(currentPolyline, points.get(points.size()-2));
            currentPolyline = null;
        }

        if (currentPolyline == null && MouseEvent.MOUSE_CLICKED == type && MouseButton.PRIMARY == e.getButton()) {
            currentPolyline = new Polyline(p, p, model.getOutlineColor());
            currentPolyline.setStrokeWidth(model.getStrokeWidth());

            Command drawCmd = new DrawCommand(model, currentPolyline);
            model.getCommandHistory().executeCommand(drawCmd);
        }
        else  if(currentPolyline != null && MouseEvent.MOUSE_MOVED == type){
            model.updateDrawablePoint(currentPolyline, p);
        }
        else if(currentPolyline != null && MouseEvent.MOUSE_CLICKED == type && MouseButton.PRIMARY == e.getButton()){
            currentPolyline.getPoints().add(p);
            model.triggerRepaint();
        }
        else if(currentPolyline != null && MouseEvent.MOUSE_CLICKED == type && MouseButton.SECONDARY == e.getButton()){
            model.updateDrawablePoint(currentPolyline, p);
            currentPolyline = null;
        }
    }
}
