package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;

public interface ToolStrategy {
    void handle(MouseEvent e, PaintModel model);
}
