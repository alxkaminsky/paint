package ca.utoronto.utm.assignment2.paint;
import javafx.scene.paint.Color;

/**
 * This class represent a Circle on the canvas, extending from the Oval class
 */
public class Circle extends Oval{
        private double diameter;

    /**
     * Constructor for Circle
     * @param start The center point of the circle
     * @param end The end point of the circle, this is the point which the mouse is released
     * @param fillColour The fill color of the circle
     * @param outlineColor The outline color of the circle
     */
        public Circle(Point start, Point end, Color fillColour, Color outlineColor){
            super(start, end, fillColour, outlineColor);
        }

        /**
         * Return the height of the circle
         */
        @Override
        public void calculateHeight(){
                calculateDiameter();
                height = diameter;
        }

    /**
     * Return the width of the circle
     */
    @Override
    public void calculateWidth(){
            width = height;
        }
        public double calculateDiameter(){
                double deltaX = getStart().x - end.x;
                double deltaY = getStart().y - end.y;
                diameter = 2*Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
                return diameter;
            }
}
