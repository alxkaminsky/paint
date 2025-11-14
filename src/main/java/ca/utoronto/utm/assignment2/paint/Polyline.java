package ca.utoronto.utm.assignment2.paint;

import javafx.scene.paint.Color;

/**
 * This class represents the Polyline on the canvas. A polyline is a connected sequence of straight line segments made
 * up from a list of Point
 */
public class Polyline extends LineSegment{

    /**
     * Constructor for the Polyline
     * @param start The first point of the polyline
     * @param end The second point of the polyline
     * @param outlineColour The color of the Polyline
     */
    public Polyline(Point start, Point end, Color outlineColour) {
        super(start, end, outlineColour);
    }

    //Overloading
    public Polyline(Color outlineColour) {
        super(outlineColour);
    }

    @Override
    public LineSegment createInstance(Color outlineColour) {
        return new Polyline(outlineColour);
    }

}
