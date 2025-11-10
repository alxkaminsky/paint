package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class ColorPickerHandler implements EventHandler<ActionEvent> {
    PaintModel model;

    public ColorPickerHandler(PaintModel model) {
        this.model = model;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        ColorPicker colorPicker = (ColorPicker) actionEvent.getSource();
        Color color = colorPicker.getValue();

        model.setCurrentColor(color);
    }
}
