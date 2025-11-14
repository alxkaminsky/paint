package ca.utoronto.utm.assignment2.paint;

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
