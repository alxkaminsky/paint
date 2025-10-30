package ca.utoronto.utm.assignment2.paint;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

public class PaintPanelHandler implements EventHandler<MouseEvent> {
    private PaintModel model;
    Circle circle;
    Rectangle rectangle;
    Square square;
    Oval oval;
    RightTriangle rtriangle;
    IsoscelesTriangle itriangle;

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
            case "Square":
                if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Square");
                    Point startPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
                    square = new Square(startPoint, startPoint);
                    model.setPreviewSquare(square);
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)){
                    model.updatePreviewSquare(new Point(mouseEvent.getX(), mouseEvent.getY()));
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)){
                    if (square != null){
                        model.addSquare(square);
                        System.out.println("Added Square");
                        model.setPreviewSquare(null);
                    }
                }
                break;

            case "RightTriangle":
                if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started RightTriangle");
                    Point startPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
                    rtriangle=new RightTriangle(startPoint, startPoint);
                    model.setPreviewRTriangle(rtriangle);
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    model.updatePreviewRTriangle(new Point(mouseEvent.getX(), mouseEvent.getY()));
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    if (rtriangle != null) {
                        model.addRTriangle(rtriangle);
                        System.out.println("Added RightTriangle");
                        model.setPreviewRTriangle(null);
                    }
                }
                break;

            case "IsoscelesTriangle":
                if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started IsoscelesTriangle");
                    Point startPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
                    itriangle=new IsoscelesTriangle(startPoint, startPoint);
                    model.setPreviewITriangle(itriangle);
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    model.updatePreviewITriangle(new Point(mouseEvent.getX(), mouseEvent.getY()));
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    if (itriangle != null) {
                        model.addITriangle(itriangle);
                        System.out.println("Added IsoscelesTriangle");
                        model.setPreviewITriangle(null);
                    }
                }
                break;

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
