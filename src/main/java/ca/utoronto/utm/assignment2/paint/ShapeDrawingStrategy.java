package ca.utoronto.utm.assignment2.paint;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Strategy for drawing shapes (Circle, Rectangle, Square, Oval, Triangles).
 * Handles mouse events for creating and previewing shapes with visual feedback.
 *
 * @author kamins64
 */
public class ShapeDrawingStrategy implements ToolStrategy {
    String mode;
    Point start;
    Shape currentShape;

    /**
     * Create a new ShapeDrawingStrategy for the specified shape type
     * @param mode the type of shape to draw (Circle, Rectangle, Square, etc.)
     */
    public ShapeDrawingStrategy(String mode) {
        this.mode = mode;
    }

    /**
     * Handle mouse events for shape drawing. Part of the Strategy pattern.
     * @param e the mouse event
     * @param model the paint model
     */
    public void handle(MouseEvent e, PaintModel model) {
         EventType<? extends MouseEvent> type = e.getEventType();

        if (type.equals(MouseEvent.MOUSE_PRESSED)) {
            start = new Point(e.getX(), e.getY());

            Color fillColour = model.getFillColor();
            Color outlineColour = model.getOutlineColor();
            double strokeWidth = model.getStrokeWidth();

             currentShape = ShapeFactory.create(
                    mode,
                    start,
                    start,
                    fillColour,
                    outlineColour,
                    strokeWidth);

            // Use command pattern to add the shape
            Command drawCmd = new DrawCommand(model, currentShape);
            model.getCommandHistory().executeCommand(drawCmd);
            model.addShape(currentShape);

            model.updateDrawableOpacity(currentShape, 0.25, 0.5);
        }

        if (type.equals(MouseEvent.MOUSE_DRAGGED)) {
            model.updateDrawablePoint(currentShape, new Point(e.getX(), e.getY()));
            return;
        }

        if (type.equals(MouseEvent.MOUSE_RELEASED)) {
            if (start == null || mode.equals("Select")) {
                model.setSelect(null);
                currentShape = null;
                return;
            }

            if(currentShape !=null){
                currentShape.setOpacity(4, 2);
                model.setSelect(null);
            }
            start = null;
            currentShape = null;
        }
    }
}
