package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
        private String mode = "Circle";
        private ArrayList<ArrayList<Point>> points=new ArrayList<ArrayList<Point>>();
        private ArrayList<Circle> circles=new ArrayList<Circle>();
        private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
        private ArrayList<Oval> ovals = new ArrayList<Oval>();
        private ArrayList<Triangle> Triangles = new ArrayList<Triangle>();

        private Circle previewCircle;
        private Oval previewOval;
        private Rectangle previewRectangle;
        private Triangle previewTriangle;


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
            previewRectangle=r;
            setChanged();
            notifyObservers();
        }

        public void updatePreviewRectangle(Point endPoint) {
            previewRectangle.setEndPoint(endPoint);
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

        public void addTriangle(Triangle tg){
            Triangles.add(tg);
            setChanged();
            notifyObservers();
        }

        public ArrayList<Triangle> getTriangles(){return Triangles;}

        public void setPreviewTriangle(Triangle tg){
            previewTriangle=tg;
            setChanged();
            notifyObservers();
        }

        public void updatePreviewTriangle(Point endPoint) {
            // previewTriangle.setEndPoint(endPoint); // !!IMPLEMENT THIS
            setChanged();
            notifyObservers();
        }

}
