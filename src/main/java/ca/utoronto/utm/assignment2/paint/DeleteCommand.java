package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;

/**
 * Command for deleting drawables from the model.
 * Stores the deleted items and their original positions to enable undo.
 *
 * @author kamins64
 */
public class DeleteCommand implements Command {
    private PaintModel model;
    private ArrayList<Drawable> deletedItems;
    private ArrayList<Integer> originalIndices;

    /**
     * Creates a new DeleteCommand
     * @param model the PaintModel to modify
     * @param itemsToDelete the drawables to delete
     */
    public DeleteCommand(PaintModel model, ArrayList<Drawable> itemsToDelete) {
        this.model = model;
        this.deletedItems = new ArrayList<>(itemsToDelete);
        this.originalIndices = new ArrayList<>();

        // Store original indices to restore items in correct positions during undo
        for (Drawable d : itemsToDelete) {
            originalIndices.add(model.getDrawables().indexOf(d));
        }
    }

    @Override
    public void execute() {
        model.getDrawables().removeAll(deletedItems);
        model.getSelected().clear();
        model.triggerRepaint();
    }

    @Override
    public void undo() {
        // Restore items in their original positions
        for (int i = 0; i < deletedItems.size(); i++) {
            int index = originalIndices.get(i);
            // Clamp index to valid range in case list size changed
            index = Math.min(index, model.getDrawables().size());
            model.getDrawables().add(index, deletedItems.get(i));
        }
        model.triggerRepaint();
    }
}
