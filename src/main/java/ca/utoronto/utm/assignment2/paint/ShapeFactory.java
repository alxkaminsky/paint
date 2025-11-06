package ca.utoronto.utm.assignment2.paint;

import javafx.scene.paint.Color;

public final class ShapeFactory {

    private ShapeFactory() { }

    private static String norm(String s) {
        return (s == null) ? "" : s.toLowerCase().replaceAll("\\s+|_", "");
    }

    private static Color withAlpha(Color c, double a) {
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), a);
    }

    public static Shape create(
            String shapeType,
            Point start,
            Point end,
            String style
    ) {
        String key = norm(shapeType);

        switch (key) {
            case "rectangle" -> {
                return new Rectangle(start, end, Rectangle.base, style);
            }
            case "circle" -> {
                return new Circle(start, end, Circle.base, style);
            }
            case "oval" -> {
                return new Oval(start, end, Oval.base, style);
            }
            case "square" -> {
                return new Square(start, end, Square.base, style);
            }
            case "righttriangle", "righttri" -> {
                return new RightTriangle(start, end, RightTriangle.base, style);
            }
            case "isoscelestriangle", "isosceles" -> {
                return new IsoscelesTriangle(start, end, IsoscelesTriangle.base, style);
            }
            default -> throw new IllegalArgumentException("Unknown shape type: " + shapeType);
        }
    }

    public static Shape createPreview(
            String shapeType,
            Point start,
            Point end,
            String style
    ) {
        String key = norm(shapeType);

        switch (key) {
            case "rectangle" -> {
                return new Rectangle(start, end, withAlpha(Rectangle.base, 0.25), style);
            }
            case "circle" -> {
                return new Circle(start, end, withAlpha(Circle.base, 0.25), style);
            }
            case "oval" -> {
                return new Oval(start, end, withAlpha(Oval.base, 0.25), style);
            }
            case "square" -> {
                return new Square(start, end, withAlpha(Square.base, 0.25), style);
            }
            case "righttriangle", "righttri" -> {
                return new RightTriangle(start, end, withAlpha(RightTriangle.base, 0.25), style);
            }
            case "isoscelestriangle", "isosceles" -> {
                return new IsoscelesTriangle(start, end, withAlpha(IsoscelesTriangle.base, 0.25), style);
            }
            default -> throw new IllegalArgumentException("Unknown shape type: " + shapeType);
        }
    }
}