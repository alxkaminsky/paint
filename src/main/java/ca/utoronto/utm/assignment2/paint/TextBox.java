package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.List;

public class TextBox implements Shape {
    private final Point start;
    private Point end;
    private String text = "";
    private Color outlineColour;
    private double strokeWidth = 1.5;

    private int caretIndex = 0;
    private boolean caretVisible = false;
    private Integer caretPrefColumn = null;

    private final double padX = 4;
    private final double lineHeight = 14;

    public TextBox(Point start, Point end, Color fillColour, Color outlineColour, String style) {
        this.start = start;
        this.end = end;
        this.outlineColour = outlineColour;
        this.caretIndex = 0;
        this.caretVisible = false;
    }

    public void setText(String text) {
        this.text = (text == null) ? "" : text;
        this.caretIndex = Math.min(this.caretIndex, this.text.length());
    }

    public String getText() { return text; }

    public void insertAtCaret(String s) {
        if (s == null || s.isEmpty()) return;
        text = text.substring(0, caretIndex) + s + text.substring(caretIndex);
        caretIndex += s.length();
        caretPrefColumn = null;
    }

    public void backspaceAtCaret() {
        if (caretIndex == 0) return;
        text = text.substring(0, caretIndex - 1) + text.substring(caretIndex);
        caretIndex--;
        caretPrefColumn = null;
    }

    public void moveCaretLeft() {
        if (caretIndex > 0) caretIndex--;
        caretPrefColumn = null;
    }

    public void moveCaretRight() {
        if (caretIndex < text.length()) caretIndex++;
        caretPrefColumn = null;
    }

    public void moveCaretHome() {
        Font f = Font.getDefault();
        Layout lay = layout(f, width() - 2 * padX);
        Pos p = indexToLineCol(lay, caretIndex);
        caretIndex = lineColToIndex(lay, p.line, 0);
        caretPrefColumn = 0;
    }

    public void moveCaretEnd() {
        Font f = Font.getDefault();
        Layout lay = layout(f, width() - 2 * padX);
        Pos p = indexToLineCol(lay, caretIndex);
        caretIndex = lineColToIndex(lay, p.line, lay.lines.get(p.line).length());
        caretPrefColumn = lay.lines.get(p.line).length();
    }

    public void moveCaretUp() {
        Font f = Font.getDefault();
        Layout lay = layout(f, width() - 2 * padX);
        Pos p = indexToLineCol(lay, caretIndex);
        int targetLine = Math.max(0, p.line - 1);
        int col = (caretPrefColumn != null) ? caretPrefColumn : p.col;
        col = Math.min(col, lay.lines.get(targetLine).length());
        caretIndex = lineColToIndex(lay, targetLine, col);
        caretPrefColumn = col;
    }

    public void moveCaretDown() {
        Font f = Font.getDefault();
        Layout lay = layout(f, width() - 2 * padX);
        Pos p = indexToLineCol(lay, caretIndex);
        int targetLine = Math.min(lay.lines.size() - 1, p.line + 1);
        int col = (caretPrefColumn != null) ? caretPrefColumn : p.col;
        col = Math.min(col, lay.lines.get(targetLine).length());
        caretIndex = lineColToIndex(lay, targetLine, col);
        caretPrefColumn = col;
    }

    public void setCaretVisible(boolean v) {
        caretVisible = v;
    }

    public void toggleCaret() {
        caretVisible = !caretVisible;
    }

    public void setStrokeWidth(double width) {
        if (width > 0) this.strokeWidth = width;
    }

    private double left()  { return Math.min(start.x, end.x); }
    private double top()   { return Math.min(start.y, end.y); }
    private double width() { return Math.abs(end.x - start.x); }
    private double height(){ return Math.abs(end.y - start.y); }

    @Override
    public void setEndPoint(Point endPoint) {
        this.end = endPoint;
    }

    @Override
    public void setFillColour(Color color) {}

    @Override
    public void draw(GraphicsContext g2d) {
        g2d.setStroke(outlineColour);
        g2d.setLineWidth(strokeWidth);
        g2d.strokeRect(left(), top(), width(), height());

        if (width() <= 6 || height() <= 8) return;

        Font font = g2d.getFont();
        Layout lay = layout(font, width() - 2 * padX);
        g2d.setFill(outlineColour);

        double y = top() + lineHeight;
        for (int i = 0; i < lay.lines.size(); i++) {
            if (y > top() + height()) break;
            g2d.fillText(lay.lines.get(i), left() + padX, y);
            y += lineHeight;
        }

        if (caretVisible) {
            Pos p = indexToLineCol(lay, caretIndex);
            double cx = left() + padX + measure(lay.lines.get(p.line).substring(0, p.col), font);
            double cyBottom = Math.min(top() + (p.line + 1) * lineHeight, top() + height());
            double cyTop = cyBottom - (lineHeight - 2);
            if (cx > left() + width() - 2) cx = left() + width() - 2;
            g2d.strokeLine(cx, cyTop, cx, cyBottom - 2);
        }
    }

    private record Layout(List<String> lines, int[] starts) {}
    private record Pos(int line, int col) {}

    private Layout layout(Font font, double maxWidth) {
        List<String> lines = new ArrayList<>();
        List<Integer> starts = new ArrayList<>();
        int globalIndex = 0;

        String[] paras = text.split("\\R", -1);
        for (int pi = 0; pi < paras.length; pi++) {
            String para = paras[pi];
            int i = 0;
            String line = "";
            int lineStart = globalIndex;

            while (i < para.length()) {
                char c = para.charAt(i);
                String candidate = line + c;
                if (measure(candidate, font) <= maxWidth || line.isEmpty()) {
                    line = candidate;
                    i++;
                    globalIndex++;
                } else {
                    lines.add(line);
                    starts.add(lineStart);
                    line = "";
                    lineStart = globalIndex;
                }
            }
            lines.add(line);
            starts.add(lineStart);
            if (pi < paras.length - 1) {
                lines.add("");
                starts.add(globalIndex);
                globalIndex++;
            }
        }

        if (lines.isEmpty()) { lines.add(""); starts.add(0); }
        int[] arr = new int[starts.size()];
        for (int k = 0; k < starts.size(); k++) arr[k] = starts.get(k);
        return new Layout(lines, arr);
    }

    private Pos indexToLineCol(Layout lay, int idx) {
        for (int i = 0; i < lay.starts.length; i++) {
            int start = lay.starts[i];
            int end = (i + 1 < lay.starts.length) ? lay.starts[i + 1] : text.length();
            if (idx >= start && idx <= end) {
                int col = Math.max(0, idx - start);
                col = Math.min(col, lay.lines.get(i).length());
                return new Pos(i, col);
            }
        }
        return new Pos(lay.starts.length - 1, lay.lines.get(lay.starts.length - 1).length());
    }

    private int lineColToIndex(Layout lay, int line, int col) {
        line = Math.max(0, Math.min(line, lay.lines.size() - 1));
        col = Math.max(0, Math.min(col, lay.lines.get(line).length()));
        return lay.starts[line] + col;
    }

    private double measure(String s, Font font) {
        Text t = new Text(s);
        t.setFont(font);
        return t.getLayoutBounds().getWidth();
    }
}
