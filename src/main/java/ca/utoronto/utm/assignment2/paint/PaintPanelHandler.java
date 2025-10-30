package ca.utoronto.utm.assignment2.paint;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

public class PaintPanelHandler implements EventHandler<MouseEvent> {
    private PaintModel model;
    Circle circle;
    Rectangle rectangle;
    Oval oval;
    Triangle triangle;

    public PaintPanelHandler(PaintModel model) {
        this.model = model;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();

        // "Circle", "Rectangle", "Square", "Squiggle", "Polyline"
        switch(model.getMode()){
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
            case "Oval":
                if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Oval");
                    Point centre = new Point(mouseEvent.getX(), mouseEvent.getY());
                    oval =new Oval(centre, centre);
                    model.setPreviewOval(oval);
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    Point end = new Point(mouseEvent.getX(), mouseEvent.getY());
                    model.updatePreviewOval(end);
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    if (oval != null) {
                        model.addOval(oval);
                        System.out.println("Added Oval");
                        model.setPreviewOval(null);
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

            case "Triangle":
                if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Triangle");
                    Point startPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
                    triangle=new Triangle(startPoint, startPoint);
                    model.setPreviewTriangle(triangle);
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    model.updatePreviewTriangle(new Point(mouseEvent.getX(), mouseEvent.getY()));
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    if (triangle != null) {
                        model.addTriangle(triangle);
                        System.out.println("Added Triangle");
                        model.setPreviewTriangle(null);
                    }
                }

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
}
