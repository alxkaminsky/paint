package ca.utoronto.utm.assignment2.paint;
import javafx.scene.canvas.Canvas;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PaintPanel extends Canvas implements EventHandler<MouseEvent>, Observer {
    private String mode="Circle";
    private PaintModel model;

    public Circle circle; // This is VERY UGLY, should somehow fix this!!
    public Rectangle rectangle;

    public PaintPanel(PaintModel model) {
        super(300, 300);
        this.model=model;
        model.addObserver(this);

        addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        addEventHandler(MouseEvent.MOUSE_RELEASED, this);
        addEventHandler(MouseEvent.MOUSE_MOVED, this);
        addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        addEventHandler(MouseEvent.MOUSE_DRAGGED, this);
    }
    /**
     *  Controller aspect of this
     */
    public void setMode(String mode){
        this.mode=mode;
        System.out.println(mode);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        // Later when we learn about inner classes...
        // https://docs.oracle.com/javafx/2/events/DraggablePanelsExample.java.htm

        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();

        // "Circle", "Rectangle", "Square", "Squiggle", "Polyline"
        switch(mode){
            case "Circle":
                if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Circle");
                    Point centre = new Point(mouseEvent.getX(), mouseEvent.getY());
                    circle=new Circle(centre, centre);
                    model.setPreviewCircle(circle);
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    Point end = new Point(mouseEvent.getX(), mouseEvent.getY());
                    model.updatePreviewCircle(end);
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    if (circle != null) {
                        model.addCircle(circle);
                        System.out.println("Added Circle");
                        model.setPreviewCircle(null);
                    }
                }
                break;
            case "Rectangle":
                if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Rectangle");
                    Point startPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
                    rectangle=new Rectangle(startPoint, startPoint);
                    model.setPreviewRectangle(rectangle);
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)){
                     model.updatePreviewRectangle(new Point(mouseEvent.getX(), mouseEvent.getY()));
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)){
                    if(rectangle!=null){
                        model.addRectangle(rectangle);
                        System.out.println("Added Rectangle");
                        model.setPreviewRectangle(null);
                    }
                }
                break;
            case "Square": break;
            case "Squiggle":
                if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    model.addPoint(new Point(mouseEvent.getX(), mouseEvent.getY()));
                }
                if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    model.newLine();
                }
                break;
            case "Polyline": break;
            default: break;
        }
    }
    @Override
    public void update(Observable o, Object arg) {

        GraphicsContext g2d = getGraphicsContext2D();
        g2d.clearRect(0, 0, getWidth(), getHeight());

        ArrayList<ArrayList<Point>> lines = model.getPoints();
        ArrayList<Circle> circles = model.getCircles();
        ArrayList<Rectangle> rectangles = model.getRectangles();
        Rectangle previewRect = model.getPreviewRectangle();
        Circle previewCirc = model.getPreviewCircle();


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
    }
}