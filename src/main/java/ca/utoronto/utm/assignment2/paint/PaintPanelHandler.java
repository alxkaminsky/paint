package ca.utoronto.utm.assignment2.paint;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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
                    circle=new Circle(centre, centre, Color.rgb(0, 255, 0, 0.25));
                    model.setPreviewShape(circle);
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    Point end = new Point(mouseEvent.getX(), mouseEvent.getY());
                    model.updatePreviewShape(end);
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    if (circle != null) {
                        circle.setColour(Circle.base);
                        model.addShape(circle);
                        System.out.println("Added Circle");
                        model.setPreviewShape(null);
                    }
                }
                break;
            case "Oval":
                if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Oval");
                    Point centre = new Point(mouseEvent.getX(), mouseEvent.getY());
                    oval =new Oval(centre, centre, Color.rgb(255, 140, 0, 0.25));
                    model.setPreviewShape(oval);
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    Point end = new Point(mouseEvent.getX(), mouseEvent.getY());
                    model.updatePreviewShape(end);
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    if (oval != null) {
                        oval.setColour(Oval.base);
                        model.addShape(oval);
                        System.out.println("Added Oval");
                        model.setPreviewShape(null);
                    }
                }
                break;
            case "Rectangle":
                if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Rectangle");
                    Point startPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
                    rectangle=new Rectangle(startPoint, startPoint, Color.rgb(0, 0, 255, 0.25));
                    model.setPreviewShape(rectangle);
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)){
                    model.updatePreviewShape(new Point(mouseEvent.getX(), mouseEvent.getY()));
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)){
                    if(rectangle!=null){
                        rectangle.setColour(Rectangle.base);
                        model.addShape(rectangle);
                        System.out.println("Added Rectangle");
                        model.setPreviewShape(null);
                    }
                }
                break;
            case "Square":
                if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Square");
                    Point startPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
                    square = new Square(startPoint, startPoint, Color.rgb(0, 255, 127, 0.25));
                    model.setPreviewShape(square);
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)){
                    model.updatePreviewShape(new Point(mouseEvent.getX(), mouseEvent.getY()));
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)){
                    if (square != null){
                        square.setColour(Square.base);
                        model.addShape(square);
                        System.out.println("Added Square");
                        model.setPreviewShape(null);
                    }
                }
                break;

            case "RightTriangle":
                if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started RightTriangle");
                    Point startPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
                    rtriangle=new RightTriangle(startPoint, startPoint, Color.rgb(250, 128, 114, 0.25));
                    model.setPreviewShape(rtriangle);
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    model.updatePreviewShape(new Point(mouseEvent.getX(), mouseEvent.getY()));
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    if (rtriangle != null) {
                        rtriangle.setColour(RightTriangle.base);
                        model.addShape(rtriangle);
                        System.out.println("Added RightTriangle");
                        model.setPreviewShape(null);
                    }
                }
                break;

            case "IsoscelesTriangle":
                if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started IsoscelesTriangle");
                    Point startPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
                    itriangle=new IsoscelesTriangle(startPoint, startPoint, Color.rgb(250, 128, 114, 0.25));
                    model.setPreviewShape(itriangle);
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    model.updatePreviewShape(new Point(mouseEvent.getX(), mouseEvent.getY()));
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    if (itriangle != null) {
                        itriangle.setColour(IsoscelesTriangle.base);
                        model.addShape(itriangle);
                        System.out.println("Added IsoscelesTriangle");
                        model.setPreviewShape(null);
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
