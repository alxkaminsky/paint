package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;

/**
 * Strategy interface for handling different drawing tools.
 * Each tool (shapes, squiggle, polyline, select, move, bucket) implements this interface
 * to define its specific mouse event handling behavior.
 *
 * @author kamins64
 */
public interface ToolStrategy {
    void handle(MouseEvent e, PaintModel model);
}
