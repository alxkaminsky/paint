package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class ShapeChooserPanelHandler implements EventHandler<ActionEvent> {
    ShapeChooserPanel shapeChooserPanel;

    public ShapeChooserPanelHandler(ShapeChooserPanel shapeChooserPanel) {
        this.shapeChooserPanel = shapeChooserPanel;
    }

    @Override
    public void handle(ActionEvent event) {
        Button clicked = (Button) event.getSource();
        String command = (String) clicked.getUserData();

        for (Node node : shapeChooserPanel.getChildren()) {
            if (node instanceof Button b) b.setStyle("");
        }

        if ("Circle".equals(command)) {
            javafx.scene.control.Alert alert =
                    new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
            alert.setTitle("Choose shape");
            alert.setHeaderText(null);
            alert.setContentText("Draw a Circle or an Oval?");

            javafx.scene.control.ButtonType circleBtn =
                    new javafx.scene.control.ButtonType("Circle");
            javafx.scene.control.ButtonType ovalBtn =
                    new javafx.scene.control.ButtonType("Oval");
            javafx.scene.control.ButtonType cancelBtn =
                    new javafx.scene.control.ButtonType("Cancel", javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(circleBtn, ovalBtn, cancelBtn);

            java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() != cancelBtn) {
                String chosen = (result.get() == circleBtn) ? "Circle" : "Oval";
                shapeChooserPanel.getModel().setMode(chosen);
                clicked.setStyle(shapeChooserPanel.HIGHLIGHT_STYLE);
                System.out.println(chosen);
            } else {
            }
        }

        else if ("Triangle".equals(command)) {
            javafx.scene.control.Alert alert =
                    new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
            alert.setTitle("Choose shape");
            alert.setHeaderText(null);
            alert.setContentText("Draw a Right Triangle or an Isosceles Triangle?");

            javafx.scene.control.ButtonType rightTriangleBtn =
                    new javafx.scene.control.ButtonType("Right Triangle");
            javafx.scene.control.ButtonType isoscelesTriangleBtn =
                    new javafx.scene.control.ButtonType("Isosceles Triangle");
            javafx.scene.control.ButtonType cancelBtn =
                    new javafx.scene.control.ButtonType("Cancel", javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(rightTriangleBtn, isoscelesTriangleBtn, cancelBtn);

            java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() != cancelBtn) {
                String chosen = (result.get() == rightTriangleBtn) ? "RightTriangle" : "IsoscelesTriangle";
                shapeChooserPanel.getModel().setMode(chosen);
                clicked.setStyle(shapeChooserPanel.HIGHLIGHT_STYLE);
                System.out.println(chosen);
            } else {
            }
        }

        else {
            shapeChooserPanel.getModel().setMode(command);
            clicked.setStyle(shapeChooserPanel.HIGHLIGHT_STYLE);
            System.out.println(command);
        }
    }
}
