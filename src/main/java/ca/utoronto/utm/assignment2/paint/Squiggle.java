package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

import static ca.utoronto.utm.assignment2.paint.ShapeFactory.withAlpha;

/**
 * This class represent the Squiggle drawn on the canvas
 */
public class Squiggle extends LineSegment{
    /**
     * Constructor for the Polyline
     * @param start The first point of the polyline
     * @param end The second point of the polyline
     * @param outlineColour The color of the Polyline
     */
    public Squiggle(Point start, Point end, Color outlineColour) {
        super(start, end, outlineColour);
    }

    /**
     * Constructor for creating a Squiggle with only a color
     * @param outlineColour The color of the Squiggle
     */
    public Squiggle(Color outlineColour) {
        super(outlineColour);
    }

    /**
     * Factory method to create a new Squiggle instance
     * @param outlineColour The color for the new Squiggle
     * @return a new Squiggle instance
     */
    @Override
    public LineSegment createInstance(Color outlineColour) {
        return new Squiggle(outlineColour);
    }

}
