package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class ColorPickerHandler implements EventHandler<ActionEvent> {
    PaintModel model;
    boolean isOutline;

    public ColorPickerHandler(PaintModel model, boolean isOutline) {
        this.model = model;
        this.isOutline = isOutline;
    }

    //new handler
    @Override
    public void handle(ActionEvent actionEvent) {
        ColorPicker colorPicker = (ColorPicker) actionEvent.getSource();
        Color color = colorPicker.getValue();

        if (isOutline) {
            model.setOutlineColor(color);
        }
        else{
            model.setFillColor(color);
        }
    }
}
