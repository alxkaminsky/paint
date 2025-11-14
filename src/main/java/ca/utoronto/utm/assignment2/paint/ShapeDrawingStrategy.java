package ca.utoronto.utm.assignment2.paint;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ShapeDrawingStrategy implements ToolStrategy {
    String mode;
    Point start;
    Shape curr;

    public ShapeDrawingStrategy(String mode) {
        this.mode = mode;
    }
    public void handle(MouseEvent e, PaintModel model) {
         EventType<? extends MouseEvent> type = e.getEventType();

        if (type.equals(MouseEvent.MOUSE_PRESSED)) {
            start = new Point(e.getX(), e.getY());

            Color fillColour = model.getFillColor();
            Color outlineColour = model.getOutlineColor();
            double strokeWidth = model.getCurrStrokeWidth();

             curr = ShapeFactory.create(
                    mode,
                    start,
                    start,
                    fillColour,
                    outlineColour,
                    strokeWidth);

            model.addDrawable(curr);
            model.updateDrawableOpacity(curr, 0.25, 0.5);
        }

        if (type.equals(MouseEvent.MOUSE_DRAGGED)) {
            model.updateDrawablePoint(curr, new Point(e.getX(), e.getY()));
            return;
        }

        if (type.equals(MouseEvent.MOUSE_RELEASED)) {
            if (start == null || mode.equals("Select")) {
                model.setSelect(null);
                curr = null;
                return;
            }

            if(curr!=null){
                model.updateDrawableOpacity(curr, 4, 2);
            }
            model.addShape(curr);
            start = null;
            curr = null;
        }
    }
}
