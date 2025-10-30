package ca.utoronto.utm.assignment2.paint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class ShapeChooserPanel extends GridPane{

    private PaintModel model;
    protected final String HIGHLIGHT_STYLE = "-fx-background-color: rgb(0,174,255,0.25); -fx-text-fill: white;";

    public ShapeChooserPanel(PaintModel model) {
        this.model = model;
        String[] shapeNames = {"Circle", "Rectangle", "Square", "Triangle", "Squiggle", "Polyline"};
        for (int i = 0; i < shapeNames.length; i++) {
            createShapeButton(shapeNames[i], i);
        }
        getChildren().getFirst().setStyle(HIGHLIGHT_STYLE);
    }

    public PaintModel getModel() {return model;}

    private void createShapeButton(String shapeName, int gridRow) {
        Button button = new Button();

        // Construct the path to the icon dynamically
        String iconPath = "/icons/" + shapeName.toLowerCase() + ".png";
        Image icon = new Image(getClass().getResourceAsStream(iconPath));
        ImageView iconView = new ImageView(icon);
        ShapeChooserPanelHandler handler = new ShapeChooserPanelHandler(this);

        // Apply all the common settings
        iconView.setFitWidth(50);
        iconView.setFitHeight(50);
        button.setGraphic(iconView);
        button.setUserData(shapeName);
        button.setOnAction(handler);

        this.add(button, 0, gridRow);
    }
}
