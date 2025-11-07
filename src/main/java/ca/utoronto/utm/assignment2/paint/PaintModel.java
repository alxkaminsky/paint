package ca.utoronto.utm.assignment2.paint;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
        private String mode = "Circle";
        private String style = "Filled";
        private ArrayList<ArrayList<Point>> points=new ArrayList<ArrayList<Point>>();
        private ArrayList<Shape> shapes = new ArrayList<Shape>();
        private Shape previewShape;

        private Color currentColor = Color.BURLYWOOD;

        public PaintModel() {
            newLine();
        }

        public String getMode() {return mode;}

        public void setMode(String mode) {this.mode = mode; System.out.println(this.mode);}

        public String getStyle() {return style;}

        public void setStyle(String style) {this.style = style;}

        public void addPoint(Point p){
            points.getLast().add(p);
            setChanged();
            notifyObservers();
        }
        public void newLine(){points.add(new ArrayList<Point>());}

        public ArrayList<ArrayList<Point>> getPoints(){
                return points;
        }

        public void addShape(Shape s){
                shapes.add(s);
                setChanged();
                notifyObservers();
        }

        public ArrayList<Shape> getShapes(){return shapes;}

        public Shape getPreviewShape(){return previewShape;}

        public void setPreviewShape(Shape s){
            previewShape = s;
            setChanged();
            notifyObservers();
        }

        public void updatePreviewShape(Point endPoint){
            previewShape.setEndPoint(endPoint);
            setChanged();
            notifyObservers();
        }

        public void setCurrentColor(Color color){
            this.currentColor = color;

            if (this.previewShape != null) {
                this.previewShape.setColour(color);
            }

            setChanged();
            notifyObservers();
        }

        public Color getCurrentColor() {return currentColor;}

}
