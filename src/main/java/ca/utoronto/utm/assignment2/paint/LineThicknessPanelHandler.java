package ca.utoronto.utm.assignment2.paint;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/**
 * Handles value changes for the line thickness slider.
 * Updates both the model and the live display label when the slider value changes.
 * Snaps slider values to increments of 0.5.
 *
 * @author kamins64
 */
public class LineThicknessPanelHandler implements ChangeListener<Number> {
    private PaintModel model;
    private Label liveValueLabel;
    private Slider slider;

    /**
     * Creates a new LineThicknessPanelHandler
     * @param model the PaintModel to update
     * @param liveValueLabel the label to display the current value
     * @param slider the slider being controlled
     */
    public LineThicknessPanelHandler(PaintModel model, Label liveValueLabel, Slider slider) {
        this.model = model;
        this.liveValueLabel = liveValueLabel;
        this.slider = slider;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        // Snap to nearest 0.5 increment
        double snapped = Math.round(newValue.doubleValue() * 2) / 2.0;

        // Update slider value if needed (prevents continuous small changes)
        if (Math.abs(snapped - slider.getValue()) > 0.01) {
            slider.setValue(snapped);
        }

        // Update model and label with snapped value
        model.setStrokeWidth(snapped);
        liveValueLabel.setText(String.format("%.1f", snapped));
    }
}