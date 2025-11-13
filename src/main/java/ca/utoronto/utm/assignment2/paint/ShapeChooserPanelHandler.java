package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 * Handles user interactions with the ShapeChooserPanel.
 *
 * When the user clicks one of the shape-selection buttons, this handler
 * updates the model's drawing mode accordingly and visually highlights
 * the selected button while clearing the highlight from the others.
 */
public class ShapeChooserPanelHandler implements EventHandler<ActionEvent> {
    private final ShapeChooserPanel shapeChooserPanel;

    public ShapeChooserPanelHandler(ShapeChooserPanel shapeChooserPanel) {
        this.shapeChooserPanel = shapeChooserPanel;
    }

    /**
     * Respond to a specific button click in ShapePanelChooser
     * @param event
     */
    @Override
    public void handle(ActionEvent event) {
        Button clicked = (Button) event.getSource();
        String command = (String) clicked.getUserData();

        for (Node node : shapeChooserPanel.getChildren()) {
            if (node instanceof Button) {
                node.setStyle("-fx-background-color: transparent; -fx-background-radius: 5;");
            }
        }
        PaintModel model = shapeChooserPanel.getModel();
        model.setMode(command);

        if (!"Select".equals(command) && !"Move".equals(command)) {
            model.getSelected().clear();
            model.setSelect(null);
        }

        clicked.setStyle(shapeChooserPanel.HIGHLIGHT_STYLE);
        System.out.println(command);
    }
}
