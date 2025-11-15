package ca.utoronto.utm.assignment2.paint;

/**
 * Command interface following the Command design pattern.
 * Each command encapsulates an action that can be executed and undone.
 * This allows for proper undo/redo functionality in the paint application.
 *
 * @author kamins64
 */
public interface Command {
    /**
     * Execute the command, performing the action on the model
     */
    void execute();

    /**
     * Undo the command, reverting the model to its previous state
     */
    void undo();
}
