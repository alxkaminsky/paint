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

        shapeChooserPanel.getModel().setMode(command);
        System.out.println(command);

        for (Node node : shapeChooserPanel.getChildren()) {
            if (node instanceof Button b) {
                b.setStyle("");
            }
        }
        clicked.setStyle(shapeChooserPanel.HIGHLIGHT_STYLE);
    }
}
