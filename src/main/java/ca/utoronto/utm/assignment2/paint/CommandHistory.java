package ca.utoronto.utm.assignment2.paint;

import java.util.Stack;

/**
 * Manages command history for undo/redo functionality using two stacks.
 * The undo stack stores executed commands, and the redo stack stores undone commands.
 *
 * @author kamins64
 */
public class CommandHistory {
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    /**
     * Execute a command and add it to the history.
     * Clears the redo stack since we're creating a new branch of history.
     * @param command the command to execute
     */
    public void executeCommand(Command command) {
        command.execute();
        undoStack.push(command);
        // Clear redo stack when new command is executed
        redoStack.clear();
    }

    /**
     * Add an already-executed command to the history without executing it.
     * Use this when the command's effects have already been applied to the model.
     * Clears the redo stack since we're creating a new branch of history.
     * @param command the already-executed command
     */
    public void addExecutedCommand(Command command) {
        undoStack.push(command);
        // Clear redo stack when new command is added
        redoStack.clear();
    }

    /**
     * Undo the most recent command by popping it from the undo stack,
     * calling its undo method, and pushing it to the redo stack.
     */
    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
            redoStack.push(command);
        }
    }

    /**
     * Redo the most recently undone command by popping it from the redo stack,
     * re-executing it, and pushing it back to the undo stack.
     */
    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        }
    }

    /**
     * Clear all command history
     */
    public void clear() {
        undoStack.clear();
        redoStack.clear();
    }
}
