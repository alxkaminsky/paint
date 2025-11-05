package ca.utoronto.utm.assignment2.paint;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PaintPanel extends Canvas implements Observer {

    private PaintModel model;
    private PaintPanelHandler paintPanelHandler;

    public PaintPanel(PaintModel model) {
        super(300, 300);
        this.model = model;
        paintPanelHandler = new PaintPanelHandler(model);
        model.addObserver(this);

        addEventHandler(MouseEvent.MOUSE_PRESSED, paintPanelHandler);
        addEventHandler(MouseEvent.MOUSE_RELEASED, paintPanelHandler);
        addEventHandler(MouseEvent.MOUSE_MOVED, paintPanelHandler);
        addEventHandler(MouseEvent.MOUSE_CLICKED, paintPanelHandler);
        addEventHandler(MouseEvent.MOUSE_DRAGGED, paintPanelHandler);
    }
    @Override
    public void update(Observable o, Object arg) {

        GraphicsContext g2d = getGraphicsContext2D();
        g2d.clearRect(0, 0, getWidth(), getHeight());

        ArrayList<ArrayList<Point>> lines = model.getPoints();
        ArrayList<Shape> shapes = model.getShapes();
        Shape preview = model.getPreviewShape();


        for(Shape shape: shapes){
            shape.draw(g2d);
        }

        if (preview!= null)
            preview.draw(g2d);

        g2d.setFill(Color.RED);
        for(ArrayList<Point> line: lines){
            for(int i=0;i<line.size()-1; i++) {
                Point p1=line.get(i);
                Point p2=line.get(i+1);
                g2d.strokeLine(p1.x,p1.y,p2.x,p2.y);
            }
        }
    }
}