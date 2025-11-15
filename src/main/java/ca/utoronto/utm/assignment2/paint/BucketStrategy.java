package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;

/**
 * Strategy for bucket fill tool.
 * Allows users to click on a shape to fill it with the selected color.
 *
 * @author kamins64
 */
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
                // Use command pattern for bucket fill
                Command bucketCmd = new BucketFillCommand(model, s, model.getFillColor());
                model.getCommandHistory().executeCommand(bucketCmd);
                return;
            }
        }
    }
}
