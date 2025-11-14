package ca.utoronto.utm.assignment2.paint;

import javafx.event.EventType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class PolylineStrategy implements ToolStrategy {
    private Polyline polyline;
    @Override
    public void handle(MouseEvent e, PaintModel model) {
        EventType<? extends MouseEvent> type = e.getEventType();
        Point p = new Point(e.getX(), e.getY());

        if(p.y < 0 && polyline != null){
            ArrayList<Point> points = polyline.getPoints();
            model.updateDrawablePoint(polyline, points.get(points.size()-2));
            polyline = null;
        }

        if (polyline == null && MouseEvent.MOUSE_CLICKED == type && MouseButton.PRIMARY == e.getButton()) {
            polyline = new Polyline(p, p, model.getOutlineColor());
            model.addDrawable(polyline);
        }
        else  if(polyline != null && MouseEvent.MOUSE_MOVED == type){
            model.updateDrawablePoint(polyline, p);
        }
        else if(polyline != null && MouseEvent.MOUSE_CLICKED == type && MouseButton.PRIMARY == e.getButton()){
            polyline.getPoints().add(p);
            model.triggerRepaint();
        }
        else if(polyline != null && MouseEvent.MOUSE_CLICKED == type && MouseButton.SECONDARY == e.getButton()){
            model.updateDrawablePoint(polyline, p);
            polyline = null;
        }
    }
}
