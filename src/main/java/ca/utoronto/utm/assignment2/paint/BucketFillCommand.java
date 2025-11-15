package ca.utoronto.utm.assignment2.paint;

import javafx.scene.paint.Color;

/**
 * Command for bucket fill operation - changing a shape's fill color.
 * Stores the old color to enable undo.
 *
 * @author kamins64
 */
public class BucketFillCommand implements Command {
    private Shape shape;
    private Color oldColor;
    private Color newColor;
    private PaintModel model;

    /**
     * Creates a new BucketFillCommand
     * @param model the PaintModel to modify
     * @param shape the shape whose color will be changed
     * @param newColor the new fill color
     */
    public BucketFillCommand(PaintModel model, Shape shape, Color newColor) {
        this.model = model;
        this.shape = shape;
        this.oldColor = shape.getFillColour();
        this.newColor = newColor;
    }

    @Override
    public void execute() {
        shape.setFillColour(newColor);
        model.triggerRepaint();
    }

    @Override
    public void undo() {
        shape.setFillColour(oldColor);
        model.triggerRepaint();
    }
}
