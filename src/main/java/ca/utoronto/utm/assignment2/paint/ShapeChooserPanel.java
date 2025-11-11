package ca.utoronto.utm.assignment2.paint;

import javafx.animation.PauseTransition;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

public class ShapeChooserPanel extends GridPane {

    private final PaintModel model;
    protected final String HIGHLIGHT_STYLE = "-fx-background-color: #0096C9; -fx-background-radius: 5;";
    private final String HOVER_STYLE = "-fx-background-color: rgba(0, 150, 201, 0.2); -fx-background-radius: 5;";
    private final String DEFAULT_STYLE = "-fx-background-color: transparent; -fx-background-radius: 5;";

    public ShapeChooserPanel(PaintModel model) {
        this.model = model;
        this.setStyle("-fx-background-color: lightgray; -fx-padding: 10; -fx-hgap: 5; -fx-vgap: 5;");

        String[] shapeNames = {"Circle", "Oval", "Rectangle", "Square", "IsoscelesTriangle", "RightTriangle", "Squiggle", "Polyline"};
        for (int i = 0; i < shapeNames.length; i++) {
            createShapeButton(shapeNames[i], i%2==0? i:i-1, i%2==0? 0:1);
        }
        getChildren().getFirst().setStyle(HIGHLIGHT_STYLE);
    }

    public PaintModel getModel() {
        return model;
    }

    private void createShapeButton(String shapeName, int gridColumn, int gridRow) {
        Node icon;
        Button button = new Button();
        ShapeChooserPanelHandler handler = new ShapeChooserPanelHandler(this);

        if (shapeName.equals("Squiggle") || shapeName.equals("Polyline")) {
            String iconPath = "/icons/" + shapeName.toLowerCase() + ".png";
            String imageUrl = getClass().getResource(iconPath).toExternalForm();
            Image image = new Image(imageUrl, 25, 25, true, true);
            icon = new ImageView(image);
        } else {
            icon = createShapeIcon(shapeName);
        }

        button.setGraphic(icon);
        button.setPadding(javafx.geometry.Insets.EMPTY);
        button.setStyle(DEFAULT_STYLE);
        button.setUserData(shapeName);
        button.setOnAction(handler);

        String tooltipText = shapeName;
        if (shapeName.equals("IsoscelesTriangle")) {
            tooltipText = "Isosceles\nTriangle";
        } else if (shapeName.equals("RightTriangle")) {
            tooltipText = "Right\nTriangle";
        }
        Tooltip tooltip = new Tooltip(tooltipText);

        PauseTransition pause = new PauseTransition(Duration.millis(100));
        pause.setOnFinished(event -> {
            Point2D p = button.localToScreen(button.getLayoutBounds().getMaxX(), button.getLayoutBounds().getMaxY());
            tooltip.show(button, p.getX(), p.getY());
        });

        button.setOnMouseEntered(e -> {
            if (!button.getStyle().equals(HIGHLIGHT_STYLE)) {
                button.setStyle(HOVER_STYLE);
            }
            pause.play();
        });

        button.setOnMouseExited(e -> {
            if (!button.getStyle().equals(HIGHLIGHT_STYLE)) {
                button.setStyle(DEFAULT_STYLE);
            }
            pause.stop();
            tooltip.hide();
        });

        this.add(button, gridColumn, gridRow);
    }

    private Node createShapeIcon(String shapeName) {
        SVGPath svgPath = new SVGPath();
        String content = switch (shapeName) {
            case "Circle" -> "M 12.5, 2.5 A 10,10 0 1 1 12.5,22.5 A 10,10 0 0 1 12.5,2.5 Z";
            case "Oval" -> "M 12.5, 5 A 10,7.5 0 1 1 12.5,20 A 10,7.5 0 0 1 12.5,5 Z";
            case "Rectangle" -> "M 2.5,5 L 22.5,5 L 22.5,20 L 2.5,20 Z";
            case "Square" -> "M 5,5 L 20,5 L 20,20 L 5,20 Z";
            case "IsoscelesTriangle" -> "M 12.5,5 L 20,20 L 5,20 Z";
            case "RightTriangle" -> "M 5,5 L 5,20 L 20,20 Z";
            default -> "";
        };
        svgPath.setContent(content);
        svgPath.setStyle("-fx-stroke: black; -fx-stroke-width: 1.5; -fx-fill: transparent;");

        // Wrap in a StackPane to control size and display the SVG
        StackPane stackPane = new StackPane(svgPath);
        stackPane.setPrefSize(25, 25);
        stackPane.setMinSize(25, 25);
        stackPane.setMaxSize(25, 25);
        return stackPane;
    }
}


