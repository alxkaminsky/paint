package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
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

        double strokeWidth = model.getCurrStrokeWidth();
        g.setLineWidth(strokeWidth);

        for (Drawable d : model.getDrawables()) {
            d.draw(g);
        }


        Drawable select = model.getSelect();
        if (select != null){
            select.draw(g);
        }

        ArrayList<Drawable> selected = model.getSelected();
        if (selected != null && !selected.isEmpty()) {
            g.setStroke(Color.BLACK);
            g.setLineWidth(1.0);
            g.setLineDashes(4, 4);

            for (Drawable d: selected) {
                drawSelectionOutline(g, d);
            }

            g.setLineDashes(0, 0);
        }
    }

    private void drawSelectionOutline(GraphicsContext g, Drawable d) {
        double padding = 3.0;
        double x, y, w, h;

        if (d instanceof Rectangle) {
            Rectangle r = (Rectangle) d;
            x = r.getLeftCornerX();
            y = r.getLeftCornerY();
            w = r.getWidth();
            h = r.getHeight();

        } else if (d instanceof Oval) {
            Oval o = (Oval) d;
            Point corner = o.getUpLeftCorner();
            x = corner.x;
            y = corner.y;
            w = o.getWidth();
            h = o.getHeight();

        } else if (d instanceof Triangle) {
            Triangle t = (Triangle) d;

            double[] xs = t.getXAllVertices();
            double[] ys = t.getYAllVertices();

            double minX = Math.min(xs[0], Math.min(xs[1], xs[2]));
            double maxX = Math.max(xs[0], Math.max(xs[1], xs[2]));
            double minY = Math.min(ys[0], Math.min(ys[1], ys[2]));
            double maxY = Math.max(ys[0], Math.max(ys[1], ys[2]));

            x = minX;
            y = minY;
            w = maxX - minX;
            h = maxY - minY;

        } else if (d instanceof Polyline) {
            Polyline p = (Polyline) d;
            java.util.List<Point> pts = p.getPoints();
            if (pts.isEmpty()) {
                return;
            }
            double minX = pts.get(0).x;
            double maxX = pts.get(0).x;
            double minY = pts.get(0).y;
            double maxY = pts.get(0).y;
            for (Point pt : pts) {
                if (pt.x < minX) minX = pt.x;
                if (pt.x > maxX) maxX = pt.x;
                if (pt.y < minY) minY = pt.y;
                if (pt.y > maxY) maxY = pt.y;
            }
            x = minX;
            y = minY;
            w = maxX - minX;
            h = maxY - minY;

        } else if (d instanceof Squiggle) {
            Squiggle s = (Squiggle) d;
            java.util.List<Point> pts = s.getPoints();
            if (pts.isEmpty()) {
                return;
            }
            double minX = pts.get(0).x;
            double maxX = pts.get(0).x;
            double minY = pts.get(0).y;
            double maxY = pts.get(0).y;
            for (Point pt : pts) {
                if (pt.x < minX) minX = pt.x;
                if (pt.x > maxX) maxX = pt.x;
                if (pt.y < minY) minY = pt.y;
                if (pt.y > maxY) maxY = pt.y;
            }
            x = minX;
            y = minY;
            w = maxX - minX;
            h = maxY - minY;

        } else {
            return;
        }

        x -= padding;
        y -= padding;
        w += 2 * padding;
        h += 2 * padding;

        g.strokeRect(x, y, w, h);
    }
}