package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;

public class BucketStrategy implements ToolStrategy{
    @Override
    public void handle(MouseEvent e, PaintModel model) {
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
