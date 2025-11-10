package ca.utoronto.utm.assignment2.paint;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;

public class DrawSettingsPanel extends HBox {

    public DrawSettingsPanel(PaintModel model) {
        ShapeChooserPanel panel = new ShapeChooserPanel(model);
        ColorPicker colorPicker = new ColorPicker(model.getCurrentColor());
        Button filled = new Button("Filled");
        Button outline = new Button("Outline");

        DrawSettingsPanelHandler handler1 = new DrawSettingsPanelHandler(outline, model);
        DrawSettingsPanelHandler handler2 = new DrawSettingsPanelHandler(filled, model);
        ColorPickerHandler colorPickerHandler = new ColorPickerHandler(model);

        colorPicker.setOnAction(colorPickerHandler);

        filled.setOnAction(handler1);
        outline.setOnAction(handler2);

        filled.setDisable(true);

        getChildren().addAll(panel,filled, outline,  colorPicker);
    }
}
