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
        ArrayList<Circle> circles = model.getCircles();
        ArrayList<Rectangle> rectangles = model.getRectangles();
        ArrayList<Oval> ovals = model.getOvals();
        Rectangle previewRect = model.getPreviewRectangle();
        Circle previewCirc = model.getPreviewCircle();
        Oval previewOval = model.getPreviewOval();


        g2d.setFill(Color.RED);
        for(ArrayList<Point> line: lines){
            for(int i=0;i<line.size()-1; i++) {
                Point p1=line.get(i);
                Point p2=line.get(i+1);
                g2d.strokeLine(p1.x,p1.y,p2.x,p2.y);
            }
        }

        g2d.setFill(Color.GREEN);
        for(Circle c: circles){
            double radius = c.getRadius();
            double x = c.getCentre().x - radius;
            double y = c.getCentre().y - radius;
            g2d.fillOval(x, y, 2 * radius, 2 * radius);
        }
        if (previewCirc != null) {
            double radius = previewCirc.getRadius();
            double x = previewCirc.getCentre().x - radius;
            double y = previewCirc.getCentre().y - radius;
            g2d.strokeOval(x, y,2*radius, 2*radius);
            g2d.setFill(Color.rgb(0, 255, 0, 0.25));
            g2d.fillOval(x, y, 2 * radius, 2 * radius);
        }

        g2d.setFill(Color.BLUE);
        for(Rectangle r: rectangles){
            g2d.fillRect(r.getLeftCornerX(), r.getLeftCornerY(), r.getWidth(), r.getHeight());
        }
        if(previewRect !=null){
            g2d.strokeRect(previewRect.getLeftCornerX(), previewRect.getLeftCornerY(), previewRect.getWidth(), previewRect.getHeight());

            g2d.setFill(Color.rgb(0, 0, 255, 0.25));
            g2d.fillRect(previewRect.getLeftCornerX(), previewRect.getLeftCornerY(), previewRect.getWidth(), previewRect.getHeight());
        }

        g2d.setFill(Color.ORANGE);
        for(Oval oval : ovals){
            g2d.fillOval(oval.getUpLeftCorner().x, oval.getUpLeftCorner().y, oval.getWidth(), oval.getHeight());
        }
        if(previewOval !=null){
            g2d.strokeOval(previewOval.getUpLeftCorner().x,previewOval.getUpLeftCorner().y, previewOval.getWidth(), previewOval.getHeight());

            g2d.setFill(Color.rgb(255, 140, 0, 0.25));
            g2d.fillOval(previewOval.getUpLeftCorner().x, previewOval.getUpLeftCorner().y, previewOval.getWidth(), previewOval.getHeight());
        }
    }
}