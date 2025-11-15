package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;

/**
 * Command for pasting drawables from the clipboard.
 * Stores the pasted items to enable undo by removing them.
 *
 * @author kamins64
 */
public class PasteCommand implements Command {
    private PaintModel model;
    private ArrayList<Drawable> pastedItems;

    /**
     * Creates a new PasteCommand
     * @param model the PaintModel to modify
     */
    public PasteCommand(PaintModel model) {
        this.model = model;
        this.pastedItems = new ArrayList<>();
    }

    @Override
    public void execute() {
        ArrayList<Drawable> clipboard = model.getClipboard();

        // Clear previous selection
        model.getSelected().clear();

        // Create copies of clipboard items and add them
        for (Drawable d : clipboard) {
            Drawable copy = d.copy();
            pastedItems.add(copy);
            model.getDrawables().add(copy);
            model.getSelected().add(copy);
        }

        model.triggerRepaint();
    }

    @Override
    public void undo() {
        // Remove all pasted items
        model.getDrawables().removeAll(pastedItems);
        model.getSelected().clear();
        model.triggerRepaint();
    }
}
