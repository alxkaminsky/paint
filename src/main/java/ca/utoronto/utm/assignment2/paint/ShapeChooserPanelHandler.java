package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class ShapeChooserPanelHandler implements EventHandler<ActionEvent> {
    private final ShapeChooserPanel shapeChooserPanel;

    public ShapeChooserPanelHandler(ShapeChooserPanel shapeChooserPanel) {
        this.shapeChooserPanel = shapeChooserPanel;
    }

    @Override
    public void handle(ActionEvent event) {
        Button clicked = (Button) event.getSource();
        String command = (String) clicked.getUserData();

        for (Node node : shapeChooserPanel.getChildren()) {
            if (node instanceof Button) {
                node.setStyle("-fx-background-color: transparent; -fx-background-radius: 5;");
            }
        }
        shapeChooserPanel.getModel().setMode(command);
        clicked.setStyle(shapeChooserPanel.HIGHLIGHT_STYLE);
        System.out.println(command);
    }
}
