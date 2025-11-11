package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorPickerPanel extends StackPane {

    private final ColorPicker hiddenColorPicker;
    private final Rectangle visibleColorSquare;

    public ColorPickerPanel(Color initialColor) {

        hiddenColorPicker = new ColorPicker(initialColor);
        hiddenColorPicker.setVisible(false);
        hiddenColorPicker.setManaged(false);

        visibleColorSquare = new Rectangle(30, 30);
        visibleColorSquare.setArcWidth(5);
        visibleColorSquare.setArcHeight(5);
        visibleColorSquare.setStroke(Color.web("#000000"));
        visibleColorSquare.setStrokeWidth(1);

        visibleColorSquare.fillProperty().bind(hiddenColorPicker.valueProperty());

        this.setOnMouseClicked(event -> {
            hiddenColorPicker.show();
        });

        getChildren().addAll(visibleColorSquare, hiddenColorPicker);

        final String HOVER_STYLE = "-fx-opacity: 0.8;";
        final String NORMAL_STYLE = "-fx-opacity: 1.0;";

        visibleColorSquare.setStyle(NORMAL_STYLE);
        this.setOnMouseEntered(e -> visibleColorSquare.setStyle(HOVER_STYLE));
        this.setOnMouseExited(e -> visibleColorSquare.setStyle(NORMAL_STYLE));

        this.setStyle("-fx-cursor: hand;");
    }

    public void setOnAction(EventHandler<ActionEvent> handler) {
        this.hiddenColorPicker.setOnAction(handler);
    }
}