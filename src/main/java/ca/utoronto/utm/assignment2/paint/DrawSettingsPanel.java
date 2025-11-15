package ca.utoronto.utm.assignment2.paint;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Create the draw setting panel to control the Color of the fill, outline, and outline thickness of the shapes. This
 * sits on the top panel together with the menu bar and the shape chooser panel
 */
public class DrawSettingsPanel extends HBox {
    /**
     * Create the draw setting model with the shape chooser panel, the color picker for the fill color, the outline,
     * and the line thickness selector
     * @param model the paint model
     */
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

        LineThicknessPanel thicknessPanel = new LineThicknessPanel(model);

        getChildren().addAll(panel,
                createCustomSeparator(),
                fillBox,
                createCustomSeparator(),
                outlineBox,
                createCustomSeparator(),
                thicknessPanel,
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
