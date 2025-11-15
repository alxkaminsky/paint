package ca.utoronto.utm.assignment2.paint;

/**
 * Factory class for creating ToolStrategy instances based on the selected mode.
 * Uses the Factory pattern to centralize strategy object creation.
 *
 * @author kamins64
 */
public class ToolStrategyFactory {
    public static ToolStrategy createToolStrategy(String mode) {
        return switch(mode) {
            case "Squiggle" -> new SquiggleStrategy();
            case "Polyline" -> new PolylineStrategy();
            case "Move" -> new MoveStrategy();
            case "Bucket" -> new BucketStrategy();
            case "Select" -> new SelectStrategy();
            case "Circle", "Oval", "Rectangle", "Square",  "RightTriangle", "IsoscelesTriangle" -> new ShapeDrawingStrategy(mode);
            default -> throw new IllegalArgumentException("Unknown mode: " + mode);
        };
    }
}
