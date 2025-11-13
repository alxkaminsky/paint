package ca.utoronto.utm.assignment2.paint;

import javafx.scene.paint.Color;

/**
 * A factory class responsible for creating Shape objects based on a requested
 * shape type. This class centralizes all construction logic for every Shape
 * implementation used in the application.
 */
public final class ShapeFactory {

    private static String norm(String s) {
        return (s == null) ? "" : s.toLowerCase().replaceAll("\\s+|_", "");
    }

    private static Color withAlpha(Color c, double a) {
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
        } else if (s instanceof Polyline po) {
            po.setStrokeWidth(strokeWidth);
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
     * @param style the style for a drawable (This works for Squiggle and Polyline as well)
     * @param strokeWidth the strokewidth for Squiggle and Polyline, also the outline width for shapes
     * @return the created shape
     */
    public static Shape create(String shapeType,
                               Point start,
                               Point end,
                               Color fillColor,
                               Color outlineColor,
                               String style,
                               double strokeWidth) {
        String key = norm(shapeType);
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

            default:
                throw new IllegalArgumentException("Unknown shape type: " + shapeType);
        }

        applyStrokeWidth(s, strokeWidth);
        return s;
    }

    /**
     * Create a new shape preview based on a provided shape type. The preview will be partially transparent.
     * This supports all drawable, meaning all the shapes and Squiggle and Polyline.
     *
     * @param shapeType the type of preview to create
     * @param start the starting point for a preview drawable
     * @param end the ending point for a preview drawable
     * @param fillColor the fill color for a preview drawable (This works for Squiggle and Polyline as well)
     * @param outlineColor the outline color for a preview drawable
     * @param style the style for a preview drawable (This works for Squiggle and Polyline as well)
     * @param strokeWidth the strokewidth for Squiggle and Polyline, also the outline width for shapes
     * @return the created preview shape
     */
    public static Shape createPreview(String shapeType,
                                      Point start,
                                      Point end,
                                      Color fillColor,
                                      Color outlineColor,
                                      String style,
                                      double strokeWidth) {
        fillColor = withAlpha(fillColor, 0.25*fillColor.getOpacity());
        outlineColor =  withAlpha(outlineColor, 0.5*outlineColor.getOpacity());

        return create(shapeType, start, end, fillColor, outlineColor, style, strokeWidth);
    }
}