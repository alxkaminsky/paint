package ca.utoronto.utm.assignment2.paint;

import javafx.scene.paint.Color;

/**
 * A factory class responsible for creating Shape objects based on a requested
 * shape type. This class centralizes all construction logic for every Shape
 * implementation used in the application.
 */
public final class ShapeFactory {

    static Color withAlpha(Color c, double a) {
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), a);
    }

    private static void applyStrokeWidth(Shape s, double strokeWidth) {
        if (s == null || strokeWidth <= 0) return;

        switch (s) {
            case Rectangle r -> r.setStrokeWidth(strokeWidth);
            case Triangle t -> t.setStrokeWidth(strokeWidth);
            case Oval o -> o.setStrokeWidth(strokeWidth);
            default -> {}
        }
    }

    /**
     * Create a new shape instance based on a provided shape type. This supports all drawable, meaning all the shapes
     * and Squiggle and Polyline
     * @param shapeType the type of shape to create
     * @param start the starting point for a drawable
     * @param end the ending point for a drawable
     * @param fillColor the fill color for a drawable (This works for Squiggle and Polyline as well)
     * @param outlineColor the outline color for a drawable
     * @param strokeWidth the strokewidth for Squiggle and Polyline, also the outline width for shapes
     * @return the created shape
     */
    public static Shape create(String shapeType,
                               Point start,
                               Point end,
                               Color fillColor,
                               Color outlineColor,
                               double strokeWidth) {
        String key = shapeType.toLowerCase();
        Shape s;

        switch(key) {
            case "rectangle":
                s = new Rectangle(start, end, fillColor, outlineColor);
                break;

            case "circle":
                s = new Circle(start, end, fillColor, outlineColor);
                break;

            case "oval":
                s = new Oval(start, end, fillColor, outlineColor);
                break;

            case "square":
                s = new Square(start, end, fillColor, outlineColor);
                break;

            case "righttriangle":
                s = new RightTriangle(start, end, fillColor, outlineColor);
                break;

            case "isoscelestriangle":
                s = new IsoscelesTriangle(start, end, fillColor, outlineColor);
                break;

            case "select":
                s = new Rectangle(start, end, fillColor, outlineColor);
                Rectangle select = (Rectangle) s;
                select.setSelect(true);
                break;
            default:
                throw new IllegalArgumentException("Unknown shape type: " + shapeType);
        }
        applyStrokeWidth(s, strokeWidth);
        return s;
    }
}