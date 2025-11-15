package ca.utoronto.utm.assignment2.paint;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

/**
 * Handles user actions for the Paint application.
 * Provides public methods for undo, redo, copy, paste, cut, and delete operations.
 * Can be used by both keyboard shortcuts and menu items.
 *
 * @author kamins64
 */
public class ActionHandler implements EventHandler<KeyEvent> {
    private PaintModel model;

    /**
     * Creates a new ActionHandler
     * @param model the PaintModel to modify
     */
    public ActionHandler(PaintModel model) {
        this.model = model;
    }

    /**
     * Undo the most recent command
     */
    public void undo() {
        model.getCommandHistory().undo();
    }

    /**
     * Redo the most recently undone command
     */
    public void redo() {
        model.getCommandHistory().redo();
    }

    /**
     * Copy selected items (or all items if none selected) to clipboard
     */
    public void copy() {
        model.copy();
    }

    /**
     * Paste items from clipboard
     */
    public void paste() {
        Command pasteCmd = new PasteCommand(model);
        model.getCommandHistory().executeCommand(pasteCmd);
    }

    /**
     * Cut selected items (copy then delete)
     */
    public void cut() {
        model.copy();
        if (model.getSelected().isEmpty()) {
            model.getSelected().addAll(model.getDrawables());
        }
        Command deleteCmd = new DeleteCommand(model,
                new ArrayList<>(model.getSelected()));
        model.getCommandHistory().executeCommand(deleteCmd);
    }

    /**
     * Delete selected items
     */
    public void delete() {
        if (!model.getSelected().isEmpty()) {
            Command deleteCmd = new DeleteCommand(model,
                new ArrayList<>(model.getSelected()));
            model.getCommandHistory().executeCommand(deleteCmd);
        }
    }

    public void clear() {
        model.getDrawables().clear();
        model.triggerRepaint();
    }

    @Override
    public void handle(KeyEvent e) {
        switch (e.getCode()) {
            case Z:
                if (e.isControlDown()) {
                    undo();
                }
                break;
            case Y:
                if (e.isControlDown()) {
                    redo();
                }
                break;
            case C:
                if (e.isControlDown()) {
                    copy();
                }
                break;
            case V:
                if (e.isControlDown()) {
                    paste();
                }
                break;
            case X:
                if (e.isControlDown()) {
                    cut();
                }
                break;
            case BACK_SPACE:
                delete();
                break;
        }
    }
}
