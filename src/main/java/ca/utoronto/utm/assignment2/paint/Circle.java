package ca.utoronto.utm.assignment2.paint;
import javafx.scene.paint.Color;

public class Circle extends Oval{
        private double diameter;

        public Circle(Point centre, Point end, Color fillColour, Color outlineColor, String style){
            super(centre, end, fillColour, outlineColor, style);
        }

        @Override
        public void calculateHeight(){
            calculateDiameter();
            height = diameter;
        }
        @Override
        public void calculateWidth(){
            width = height;
        }
        public double calculateDiameter(){
                double deltaX = getCentre().x - end.x;
                double deltaY = getCentre().y - end.y;
                diameter = 2*Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
                return diameter;
            }
}
