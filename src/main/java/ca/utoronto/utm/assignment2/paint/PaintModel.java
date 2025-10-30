package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
        private String mode = "Circle";
        private ArrayList<ArrayList<Point>> points=new ArrayList<ArrayList<Point>>();
        private ArrayList<Circle> circles=new ArrayList<Circle>();
        private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
        private ArrayList<Square> squares = new ArrayList<Square>();
        private ArrayList<Oval> ovals = new ArrayList<Oval>();
        private ArrayList<RightTriangle> rtriangles = new ArrayList<RightTriangle>();
        private ArrayList<IsoscelesTriangle> itriangles = new ArrayList<IsoscelesTriangle>();

        private Circle previewCircle;
        private Oval previewOval;
        private Rectangle previewRectangle;
        private Square previewSquare;
        private RightTriangle previewRTriangle;
        private IsoscelesTriangle previewITriangle;


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

        public void addCircle(Circle c){
                circles.add(c);
                setChanged();
                notifyObservers();
        }

        public ArrayList<Circle> getCircles(){return circles;}

        public Circle getPreviewCircle(){return previewCircle;}

        public void setPreviewCircle(Circle c){
            previewCircle = c;
            setChanged();
            notifyObservers();
        }

        public void updatePreviewCircle(Point endPoint){
            previewCircle.setEndPoint(endPoint);
            setChanged();
            notifyObservers();
        }

        public void addRectangle(Rectangle r){
            rectangles.add(r);
            setChanged();
            notifyObservers();
        }

        public ArrayList<Rectangle> getRectangles(){return rectangles;}

        public Rectangle getPreviewRectangle(){return previewRectangle;}

        public void setPreviewRectangle(Rectangle r){
            previewRectangle = r;
            setChanged();
            notifyObservers();
        }

        public void updatePreviewRectangle(Point endPoint) {
            previewRectangle.setEndPoint(endPoint);
            setChanged();
            notifyObservers();
        }

        public void addSquare(Square s){
            squares.add(s);
            setChanged();
            notifyObservers();
        }

        public ArrayList<Square> getSquares(){return squares;}

        public Square getPreviewSquare(){return previewSquare;}

        public void setPreviewSquare(Square s){
            previewSquare = s;
            setChanged();
            notifyObservers();
        }

        public void updatePreviewSquare(Point endPoint) {
            previewSquare.setEndPoint(endPoint);
            setChanged();
            notifyObservers();
        }

        public void addOval(Oval o){
            ovals.add(o);
            setChanged();
            notifyObservers();
        }

        public ArrayList<Oval> getOvals(){return ovals;}

        public Oval getPreviewOval(){return previewOval;}

        public void setPreviewOval(Oval o){
            previewOval = o;
            setChanged();
            notifyObservers();
        }

        public void updatePreviewOval(Point endPoint) {
            previewOval.setEndPoint(endPoint);
            setChanged();
            notifyObservers();
        }

        public void addRTriangle(RightTriangle tg){
            rtriangles.add(tg);
            setChanged();
            notifyObservers();
        }

        public ArrayList<RightTriangle> getRTriangles(){return rtriangles;}

        public RightTriangle getPreviewRTriangle(){return previewRTriangle;}

        public void setPreviewRTriangle(RightTriangle tg){
            previewRTriangle=tg;
            setChanged();
            notifyObservers();
        }

        public void updatePreviewRTriangle(Point endPoint) {
            previewRTriangle.setEndPoint(endPoint);
            setChanged();
            notifyObservers();
        }

        public void addITriangle(IsoscelesTriangle tg){
            itriangles.add(tg);
            setChanged();
            notifyObservers();
        }

        public ArrayList<IsoscelesTriangle> getITriangles(){return itriangles;}

        public IsoscelesTriangle getPreviewITriangle(){return previewITriangle;}

        public void setPreviewITriangle(IsoscelesTriangle tg){
            previewITriangle=tg;
            setChanged();
            notifyObservers();
        }

        public void updatePreviewITriangle(Point endPoint) {
            previewITriangle.setEndPoint(endPoint);
            setChanged();
            notifyObservers();
        }
}
