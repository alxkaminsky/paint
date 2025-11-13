package ca.utoronto.utm.assignment2.paint;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

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

        this.setFocusTraversable(true);

        this.addEventHandler(KeyEvent.KEY_TYPED, e -> {
            TextBox tb = model.getActiveTextBox();
            if (tb == null) return;
            String ch = e.getCharacter();
            if ("\r".equals(ch) || "\n".equals(ch)) {
                tb.insertAtCaret("\n");
            } else if (ch.length() > 0 && ch.charAt(0) >= 32) {
                tb.insertAtCaret(ch);
            }
            tb.setCaretVisible(true);
            restartCaretBlink();
            model.refresh();
        });

        this.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            TextBox tb = model.getActiveTextBox();
            if (tb == null) return;
            switch (e.getCode()) {
                case BACK_SPACE -> tb.backspaceAtCaret();
                case LEFT -> tb.moveCaretLeft();
                case RIGHT -> tb.moveCaretRight();
                case HOME -> tb.moveCaretHome();
                case END -> tb.moveCaretEnd();
                case UP -> tb.moveCaretUp();
                case DOWN -> tb.moveCaretDown();
                case ESCAPE -> { model.setActiveTextBox(null); stopCaretBlink(); }
                default -> { return; }
            }
            tb.setCaretVisible(true);
            restartCaretBlink();
            model.refresh();
        });
    }

    private void refresh(){update(null, null);}

    /**
     * The update method. Everytime setChanged() and notifyObservers() is called, the update() method is called,
     * and it wil trigger a redraw.
     *
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (model.getActiveTextBox() != null) {
            requestFocus();
            restartCaretBlink();
        } else {
            stopCaretBlink();
        }

        GraphicsContext g = getGraphicsContext2D();

        g.clearRect(0, 0, getWidth(), getHeight());

        double strokeWidth = model.getCurrStrokeWidth();
        g.setLineWidth(strokeWidth);

        for (Shape s : model.getShapes()) {
            s.draw(g);
        }

        Shape preview = model.getPreviewShape();
        if (preview != null) {
            preview.draw(g);
        }

        Shape select = model.getSelect();
        if (select != null){
            select.draw(g);
        }
    }

    private void restartCaretBlink() {
        stopCaretBlink();
        caretBlink = new Timeline(new KeyFrame(Duration.millis(500), ev -> {
            TextBox tb = model.getActiveTextBox();
            if (tb != null) { tb.toggleCaret(); model.refresh(); }
        }));
        caretBlink.setCycleCount(Timeline.INDEFINITE);
        caretBlink.play();
    }

    private void stopCaretBlink() {
        if (caretBlink != null) { caretBlink.stop(); caretBlink = null; }
    }
}