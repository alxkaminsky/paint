package ca.utoronto.utm.assignment2.paint;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PaintPanel extends Canvas implements Observer {

    private final PaintModel model;

    public PaintPanel(PaintModel model) {
        super(1000, 650);
        this.model = model;

        PaintPanelHandler handler = new PaintPanelHandler(model);

        model.addObserver(this);

        addEventHandler(MouseEvent.MOUSE_PRESSED, handler);
        addEventHandler(MouseEvent.MOUSE_DRAGGED, handler);
        addEventHandler(MouseEvent.MOUSE_RELEASED, handler);
        addEventHandler(MouseEvent.MOUSE_MOVED, handler);
        addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
    }

    @Override
    public void update(Observable o, Object arg) {
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
    }
}