package ca.utoronto.utm.assignment2.paint;
import javafx.scene.paint.Color;

public class Circle extends Oval{
        private double diameter;
        public static Color base = Color.GREEN;

        public Circle(Point centre, Point end, Color colour){
            super(centre, end, colour);
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
