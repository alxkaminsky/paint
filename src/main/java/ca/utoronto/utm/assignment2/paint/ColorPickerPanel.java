package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorPickerPanel extends StackPane {

    private final ColorPicker hiddenColorPicker;
    private final Rectangle visibleColorSquare;
    private final GridPane checkerboardNode;

    public ColorPickerPanel(Color initialColor) {
        final String HOVER_STYLE = "-fx-opacity: 0.8;";
        final String NORMAL_STYLE = "-fx-opacity: 1.0;";

        hiddenColorPicker = new ColorPicker(initialColor);
        hiddenColorPicker.setVisible(false);
        hiddenColorPicker.setManaged(false);

        visibleColorSquare = new Rectangle(30, 30);
        visibleColorSquare.setArcWidth(5);
        visibleColorSquare.setArcHeight(5);
        visibleColorSquare.setStroke(Color.web("#000000"));
        visibleColorSquare.setStrokeWidth(1);

        checkerboardNode = createCheckerboardPatternNode();
        checkerboardNode.setMouseTransparent(true);

        checkerboardNode.setPrefSize(30, 30);
        checkerboardNode.setMinSize(30, 30);
        checkerboardNode.setMaxSize(30, 30);

        this.setOnMouseClicked(event -> {
            hiddenColorPicker.show();
        });

        getChildren().addAll(checkerboardNode, visibleColorSquare, hiddenColorPicker);

        visibleColorSquare.setStyle(NORMAL_STYLE);
        visibleColorSquare.fillProperty().bind(hiddenColorPicker.valueProperty());

        this.setOnMouseEntered(e -> visibleColorSquare.setStyle(HOVER_STYLE));
        this.setOnMouseExited(e -> visibleColorSquare.setStyle(NORMAL_STYLE));

        this.setStyle("-fx-cursor: hand;");
    }

    private GridPane createCheckerboardPatternNode() {
        int squareSize = 5;
        int gridCells = 6;

        GridPane grid = new GridPane();
        grid.setPrefSize(30, 30);

        Color color1 = Color.web("#cccccc");
        Color color2 = Color.WHITE;

        for (int row = 0; row < gridCells; row++) {
            for (int col = 0; col < gridCells; col++) {
                Rectangle r = new Rectangle(squareSize, squareSize);
                if ((row + col) % 2 == 0) {
                    r.setFill(color1);
                } else {
                    r.setFill(color2);
                }
                grid.add(r, col, row);
            }
        }
        return grid;
    }

    public void setOnAction(EventHandler<ActionEvent> handler) {
        this.hiddenColorPicker.setOnAction(handler);
    }
}