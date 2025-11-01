package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
        private String mode = "Circle";
        private ArrayList<ArrayList<Point>> points=new ArrayList<ArrayList<Point>>();
        private ArrayList<Shape> shapes = new ArrayList<Shape>();
        private Shape previewShape;


        public PaintModel() {
            newLine();
        }

        public String getMode() {return mode;}

        public void setMode(String mode) {this.mode = mode;}

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

//        public Rectangle getPreviewRectangle(){return previewRectangle;}
//
//        public void setPreviewRectangle(Rectangle r){
//            previewRectangle = r;
//            setChanged();
//            notifyObservers();
//        }
//
//        public void updatePreviewRectangle(Point endPoint) {
//            previewRectangle.setEndPoint(endPoint);
//            setChanged();
//            notifyObservers();
//        }
//
//        public Square getPreviewSquare(){return previewSquare;}
//
//        public void setPreviewSquare(Square s){
//            previewSquare = s;
//            setChanged();
//            notifyObservers();
//        }
//
//        public void updatePreviewSquare(Point endPoint) {
//            previewSquare.setEndPoint(endPoint);
//            setChanged();
//            notifyObservers();
//        }
//
//        public Oval getPreviewOval(){return previewOval;}
//
//        public void setPreviewOval(Oval o){
//            previewOval = o;
//            setChanged();
//            notifyObservers();
//        }
//
//        public void updatePreviewOval(Point endPoint) {
//            previewOval.setEndPoint(endPoint);
//            setChanged();
//            notifyObservers();
//        }
//
//        public RightTriangle getPreviewRTriangle(){return previewRTriangle;}
//
//        public void setPreviewRTriangle(RightTriangle tg){
//            previewRTriangle=tg;
//            setChanged();
//            notifyObservers();
//        }
//
//        public void updatePreviewRTriangle(Point endPoint) {
//            previewRTriangle.setEndPoint(endPoint);
//            setChanged();
//            notifyObservers();
//        }
//
//        public IsoscelesTriangle getPreviewITriangle(){return previewITriangle;}
//
//        public void setPreviewITriangle(IsoscelesTriangle tg){
//            previewITriangle=tg;
//            setChanged();
//            notifyObservers();
//        }
//
//        public void updatePreviewITriangle(Point endPoint) {
//            previewITriangle.setEndPoint(endPoint);
//            setChanged();
//            notifyObservers();
//        }
}
