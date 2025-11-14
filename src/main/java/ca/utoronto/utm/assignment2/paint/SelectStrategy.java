package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SelectStrategy implements ToolStrategy{
    private Point start;
    private Drawable curr = null;

    @Override
    public void handle(MouseEvent e, PaintModel model) {
        if (e.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
            start = new Point(e.getX(), e.getY());
            curr = ShapeFactory.create(
                    "Select",
                    start,
                    start,
                    Color.TRANSPARENT,
                    Color.GRAY,
                    1);
            model.setSelect(curr);
        }
        else if (e.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
            if(model.getSelect() != null){
                model.updateSelect(new Point(e.getX(), e.getY()));
                model.getSelected().clear();

                for(Drawable d: model.getDrawables()){
                    if (d.intersects((Shape) model.getSelect())) {
                        model.getSelected().add(d);
                    }
                }
            }
            if (curr != null) {
                model.updateDrawablePoint(curr, new Point(e.getX(), e.getY()));
            }
        }
        else if (e.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
            model.setSelect(null);
            curr = null;
            start = null;
        }
    }
}
