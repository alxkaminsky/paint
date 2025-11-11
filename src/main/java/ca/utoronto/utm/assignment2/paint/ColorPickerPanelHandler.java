package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class ColorPickerPanelHandler implements EventHandler<ActionEvent> {
    PaintModel model;
    boolean isOutline;

    public ColorPickerPanelHandler(PaintModel model, boolean isOutline) {
        this.model = model;
        this.isOutline = isOutline;
    }

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
