package ca.utoronto.utm.assignment2.paint;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class DrawSettingsPanel extends HBox {

    public DrawSettingsPanel(PaintModel model) {
        Button filled = new Button("Filled");
        Button outline = new Button("Outline");

        DrawSettingsPanelHandler handler1 = new DrawSettingsPanelHandler(outline, model);
        DrawSettingsPanelHandler handler2 = new DrawSettingsPanelHandler(filled, model);

        filled.setOnAction(handler1);
        outline.setOnAction(handler2);

        filled.setDisable(true);

        getChildren().addAll(filled, outline);
    }
}
