package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;

import java.util.Objects;
import java.util.Optional;

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
        String buttonType = source.getText();

        if (Objects.equals(buttonType, "Outline")) {
            TextInputDialog dialog = new TextInputDialog("2");
            dialog.setTitle("Outline Thickness");
            dialog.setHeaderText("Enter thickness level (integer):");
            dialog.setContentText("Thickness:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(input -> {
                try {
                    double thickness = Double.parseDouble(input);
                    if (thickness <= 0) throw new NumberFormatException();
                    model.setCurrStrokeWidth(thickness);
                }
                catch (NumberFormatException nfe) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a positive number.");
                    alert.showAndWait();
                }
            });
        }

        model.setStyle(source.getText());

        source.setDisable(true);
        other.setDisable(false);
    }
}
