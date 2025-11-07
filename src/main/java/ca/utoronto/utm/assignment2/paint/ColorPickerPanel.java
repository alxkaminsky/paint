package ca.utoronto.utm.assignment2.paint;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ColorPickerPanel extends VBox{

    private PaintModel model;
    private ComboBox<String> colorComboBox;

    public ColorPickerPanel(PaintModel model) {
        this.model = model;

        Label label = new Label("Choose color");

        colorComboBox = new ComboBox<>();
        colorComboBox.getItems().addAll(
                "SADDLEBROWN",
                "CADETBLUE",
                "GOLDENROD",
                "SEAGREEN",
                "OLDLACE",
                "INDIGO",
                "BURLYWOOD"
        );
        colorComboBox.setValue("BURLYWOOD");

        ColorPickerPanelHandler handler = new ColorPickerPanelHandler(this);
        colorComboBox.setOnAction(handler);

        getChildren().addAll(label, colorComboBox);
    }

    public PaintModel getModel() {
        return model;
    }

    public String getSelectedColor() {
        return colorComboBox.getValue();
    }
}
