package ca.utoronto.utm.assignment2.paint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

    private View view;
    private final String HIGHLIGHT_STYLE = "-fx-background-color: rgb(0,174,255,0.25); -fx-text-fill: white;";

    public ShapeChooserPanel(View view) {

        this.view = view;
        String[] shapeNames = {"Circle", "Rectangle", "Square", "Squiggle", "Polyline"};
        for (int i = 0; i < shapeNames.length; i++) {
            createShapeButton(shapeNames[i], i);
        }
        getChildren().getFirst().setStyle(HIGHLIGHT_STYLE);
    }

    private void createShapeButton(String shapeName, int gridRow) {
        Button button = new Button();

        // Construct the path to the icon dynamically
        String iconPath = "/icons/" + shapeName.toLowerCase() + ".png";
        Image icon = new Image(getClass().getResourceAsStream(iconPath));
        ImageView iconView = new ImageView(icon);

        // Apply all the common settings
        iconView.setFitWidth(50);
        iconView.setFitHeight(50);
        button.setGraphic(iconView);
        button.setUserData(shapeName);
        button.setOnAction(this);

        this.add(button, 0, gridRow);
    }

    @Override
    public void handle(ActionEvent event) {
        Button clicked = (Button) event.getSource();
        String command = (String) clicked.getUserData();

        view.setMode(command);
        System.out.println(command);

        for (Node node : getChildren()) {
            if (node instanceof Button b) {
                b.setStyle("");
            }
        }
        clicked.setStyle(HIGHLIGHT_STYLE);
    }
}
