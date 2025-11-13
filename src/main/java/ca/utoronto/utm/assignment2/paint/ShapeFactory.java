package ca.utoronto.utm.assignment2.paint;

import javafx.scene.paint.Color;

public final class ShapeFactory {

    static Color withAlpha(Color c, double a) {
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), a);
    }

    private static void applyStrokeWidth(Shape s, double strokeWidth) {
        if (s == null || strokeWidth <= 0) return;

        if(s instanceof Rectangle r) {
            r.setStrokeWidth(strokeWidth);
        } else if (s instanceof Triangle t) {
            t.setStrokeWidth(strokeWidth);
        } else if (s instanceof Oval o) {
            o.setStrokeWidth(strokeWidth);
        } else if (s instanceof Square sq) {
            sq.setStrokeWidth(strokeWidth);
        } else if (s instanceof Squiggle squ) {
            squ.setStrokeWidth(strokeWidth);
        }
    }

    public static Shape create(String shapeType,
                               Point start,
                               Point end,
                               Color fillColor,
                               Color outlineColor,
                               String style,
                               double strokeWidth) {
        String key = shapeType.toLowerCase();
        Shape s;

        switch(key) {
            case "rectangle":
                s = new Rectangle(start, end, fillColor, outlineColor, style);
                break;

            case "circle":
                s = new Circle(start, end, fillColor, outlineColor, style);
                break;

            case "oval":
                s = new Oval(start, end, fillColor, outlineColor, style);
                break;

            case "square":
                s = new Square(start, end, fillColor, outlineColor, style);
                break;

            case "righttriangle":
                s = new RightTriangle(start, end, fillColor, outlineColor, style);
                break;

            case "isoscelestriangle":
                s = new IsoscelesTriangle(start, end, fillColor, outlineColor, style);
                break;

            case "polyline":
                s = new Polyline(start, end, fillColor, outlineColor, style);
                break;

            case "squiggle":
                s = new Squiggle(start, end, fillColor, outlineColor, style);
                break;

            case "select":
                s = new Rectangle(start, end, fillColor, outlineColor, style);
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