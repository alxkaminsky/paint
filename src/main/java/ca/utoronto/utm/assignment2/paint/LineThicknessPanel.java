package ca.utoronto.utm.assignment2.paint;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

/**
 * Panel containing a slider for adjusting line thickness.
 * Displays the current thickness value and allows adjustment from 0 to 50 pixels.
 *
 * @author kamins64
 */
public class LineThicknessPanel extends VBox {
    private Slider thicknessSlider;
    private Label thicknessLiveValue;

    /**
     * Creates a new LineThicknessPanel
     * @param model the PaintModel to update when thickness changes
     */
    public LineThicknessPanel(PaintModel model) {
        super(5); // spacing of 5
        setAlignment(Pos.CENTER);

        Label thicknessLabel = new Label("Line Thickness");

        thicknessSlider = new Slider(0, 20, 2);
        thicknessSlider.setShowTickLabels(true);
        thicknessSlider.setShowTickMarks(true);
        thicknessSlider.setMajorTickUnit(5);
        thicknessSlider.setMinorTickCount(9);
        thicknessSlider.setSnapToTicks(true);
        thicknessSlider.setBlockIncrement(0.5);
        thicknessSlider.setPrefWidth(250);
        thicknessSlider.setPadding(new Insets(0, 10, 0, 10));

        thicknessLiveValue = new Label(String.format("%.1f", thicknessSlider.getValue()));

        // Attach handler
        thicknessSlider.valueProperty().addListener(
            new LineThicknessPanelHandler(model, thicknessLiveValue, thicknessSlider)
        );

        getChildren().addAll(thicknessLabel, thicknessSlider, thicknessLiveValue);
    }

    /**
     * Get the thickness slider
     * @return the Slider component
     */
    public Slider getThicknessSlider() {
        return thicknessSlider;
    }

    /**
     * Get the live value label
     * @return the Label displaying the current value
     */
    public Label getThicknessLiveValue() {
        return thicknessLiveValue;
    }
}
