package ca.utoronto.utm.assignment2.paint;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox; // Import VBox
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Slider;


public class DrawSettingsPanel extends HBox {

    public DrawSettingsPanel(PaintModel model) {
        setStyle("-fx-background-color: lightgray; -fx-border-color: #C0C0C0; -fx-border-width: 0 0 1 0;");
        setPadding(new Insets(5));
        setSpacing(10);
        setAlignment(Pos.CENTER_LEFT);

        ShapeChooserPanel panel = new ShapeChooserPanel(model);

        Label fillLabel = new Label("Fill");
        ColorPickerPanel fillColourPicker = new ColorPickerPanel(model.getFillColor());

        fillColourPicker.setOnAction(new ColorPickerPanelHandler(model, false));

        VBox fillBox = new VBox(5, fillLabel, fillColourPicker);
        fillBox.setAlignment(Pos.CENTER);

        Label outlineLabel = new Label("Outline");
        ColorPickerPanel outlineColourPicker = new ColorPickerPanel(model.getOutlineColor());

        outlineColourPicker.setOnAction(new ColorPickerPanelHandler(model, true));

        VBox outlineBox = new VBox(5, outlineLabel, outlineColourPicker);
        outlineBox.setAlignment(Pos.CENTER);

        Label thicknessLabel = new Label("Thickness level");
        Slider thicknessSlider = new Slider(0, 50, 2);
        Label thicknessLiveValue = new Label(String.format("%.1f", thicknessSlider.getValue()));
        thicknessSlider.setShowTickLabels(true);
        thicknessSlider.setMajorTickUnit(5);
        thicknessSlider.setMinorTickCount(4);
        thicknessSlider.setBlockIncrement(1);
        thicknessSlider.setPrefWidth(450); // adjust width as needed
        thicknessSlider.setPadding(new Insets(0, 10, 0, 10));

        thicknessSlider.valueProperty().addListener((obs, oldVal, currVal) -> {
            model.setCurrStrokeWidth(currVal.doubleValue());
            thicknessLiveValue.setText(String.format("%.1f", currVal.doubleValue()));
        });

        VBox thicknessBox = new VBox(5, thicknessLabel, thicknessSlider, thicknessLiveValue);
        thicknessBox.setAlignment(Pos.CENTER);

        getChildren().addAll(panel,
                createCustomSeparator(),
                fillBox,
                createCustomSeparator(),
                outlineBox,
                createCustomSeparator(),
                thicknessBox,
                createCustomSeparator());
    }

    private Node createCustomSeparator() {
        Rectangle separator = new Rectangle(2, 75);
        separator.setFill(Color.web("#BDBDBD"));
        separator.setArcWidth(5);
        separator.setArcHeight(5);
        return separator;
    }
}
