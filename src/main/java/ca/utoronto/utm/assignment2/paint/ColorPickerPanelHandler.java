package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

public class ColorPickerPanelHandler implements EventHandler<ActionEvent> {
    private ColorPickerPanel colorPickerPanel;

    public ColorPickerPanelHandler(ColorPickerPanel panel) {
        this.colorPickerPanel = panel;
    }

    @Override
    public void handle(ActionEvent event) {
        String colorName = colorPickerPanel.getSelectedColor();
        Color color;

        switch(colorName) {
            case "SADDLEBROWN": color = Color.SADDLEBROWN; break;
            case "CADETBLUE": color = Color.CADETBLUE; break;
            case "GOLDENROD": color = Color.GOLDENROD; break;
            case "SEAGREEN": color = Color.SEAGREEN; break;
            case "OLDLACE": color = Color.OLDLACE; break;
            case "INDIGO": color = Color.INDIGO; break;
            case "BURLYWOOD": color = Color.BURLYWOOD; break;
            default: color = Color.BLACK; break;
        }

        // Update the model with the new color
        colorPickerPanel.getModel().setCurrentColor(color);
    }
}
