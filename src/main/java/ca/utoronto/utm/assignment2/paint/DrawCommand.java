package ca.utoronto.utm.assignment2.paint;

/**
 * Command for adding a drawable to the model.
 * This command can be undone by removing the drawable from the model.
 *
 * @author kamins64
 */
public class DrawCommand implements Command {
    private PaintModel model;
    private Drawable drawable;

    /**
     * Creates a new DrawCommand
     * @param model the PaintModel to modify
     * @param drawable the drawable to add
     */
    public DrawCommand(PaintModel model, Drawable drawable) {
        this.model = model;
        this.drawable = drawable;
    }

    @Override
    public void execute() {
        model.addDrawable(drawable);
    }

    @Override
    public void undo() {
        model.getDrawables().remove(drawable);
        model.triggerRepaint();
    }
}
