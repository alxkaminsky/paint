package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class DrawSettingsPanelHandler implements EventHandler<ActionEvent> {
    Button other;
    PaintModel model;
    public DrawSettingsPanelHandler(Button other, PaintModel model) {
        this.other = other;
        this.model = model;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        Button source = (Button) actionEvent.getSource();

        model.setStyle(source.getText());

        source.setDisable(true);
        other.setDisable(false);
    }
}
