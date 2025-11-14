package ca.utoronto.utm.assignment2.paint;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class PaintPanelHandler implements EventHandler<MouseEvent> {
    private final PaintModel model;
    private ToolStrategy currentTool;
    private String currentMode;

    public PaintPanelHandler(PaintModel model) {
        this.model = model;
    }

    @Override
    public void handle(MouseEvent e) {
        String mode = model.getMode();

        if (!mode.equals(currentMode)) {
            currentTool = ToolStrategyFactory.createToolStrategy(mode);
            currentMode = mode;
        }
        currentTool.handle(e, model);
    }
}