package ca.utoronto.utm.assignment2.paint;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.animation.Timeline;

/**
 * This class is responsible for handling user's interaction (Controller) of the paint program
 */
public class PaintPanel extends Canvas implements Observer {

    private final PaintModel model;
    private Timeline caretBlink;

    /**
     * Construct a PaintPanel object. This handles all of the mouse events for drawing properly, and the event of
     * resizing a the window, to trigger a redraw
     * @param model
     */
    public PaintPanel(PaintModel model) {
        this.model = model;

        PaintPanelHandler handler = new PaintPanelHandler(model);

        model.addObserver(this);

        //Mouse Click Events
        addEventHandler(MouseEvent.MOUSE_PRESSED, handler);
        addEventHandler(MouseEvent.MOUSE_DRAGGED, handler);
        addEventHandler(MouseEvent.MOUSE_RELEASED, handler);
        addEventHandler(MouseEvent.MOUSE_MOVED, handler);
        addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        addEventHandler(MouseEvent.MOUSE_EXITED, handler);

        // Listen to all of the changes of the window size. When a resize is detected,
        // update() is called to trigger a redraw for consistency
        widthProperty().addListener(evt -> refresh());
        heightProperty().addListener(evt -> refresh());
    }

    private void refresh(){update(null, null);}

    /**
     * The update method. Everytime setChanged() and notifyObservers() is called, the update() method is called,
     * and it wil trigger a redraw.
     *
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers} method.
     */
    @Override
    public void update(Observable o, Object arg) {

        GraphicsContext g = getGraphicsContext2D();

        g.clearRect(0, 0, getWidth(), getHeight());

        double strokeWidth = model.getStrokeWidth();
        g.setLineWidth(strokeWidth);


        for (Drawable d : model.getDrawables()) {
            d.draw(g);
            if (model.getSelected().contains(d))
                d.drawSelectionOutline(g);
        }

        Drawable select = model.getSelect();
        if (select != null){
            select.draw(g);
        }
    }
}