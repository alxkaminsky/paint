package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;

/**
 * Command for moving drawables.
 * Stores the delta values to enable both execute and undo operations.
 *
 * @author kamins64
 */
public class MoveCommand implements Command {
    private PaintModel model;
    private Point start;
    private Point end;
    private ArrayList<Drawable> movedItems;

    /**
     * Creates a new MoveCommand
     * @param model the PaintModel to modify
     * @param items the drawables to move
     * @param start the initial point of the move
     * @param end the termination point of the move
     */
    public MoveCommand(PaintModel model, ArrayList<Drawable> items, Point start, Point end) {
        this.model = model;
        this.movedItems = new ArrayList<>(items);
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute() {
        model.move(movedItems, start, end);
    }

    @Override
    public void undo() {
        // Move back by reversing the start and end points
        model.move(movedItems, end, start);
    }
}
