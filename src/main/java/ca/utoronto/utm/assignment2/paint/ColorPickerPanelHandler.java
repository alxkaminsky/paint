package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

/**
 * The event handler for when a user choose a color, triggered by ColorPickerPanel
 */
public class ColorPickerPanelHandler implements EventHandler<ActionEvent> {
    PaintModel model;
    boolean isOutline;

    public ColorPickerPanelHandler(PaintModel model, boolean isOutline) {
        this.model = model;
        this.isOutline = isOutline;
    }

    /**
     * Extract the value from the color the user selected, set either the outline or the fill color accordingly
     * @param actionEvent the event is triggered when the user picks a color from the ColorPicker
     */
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
