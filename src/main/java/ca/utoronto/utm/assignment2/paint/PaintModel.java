package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
        private ArrayList<ArrayList<Point>> points=new ArrayList<ArrayList<Point>>();
        private ArrayList<Circle> circles=new ArrayList<Circle>();
        private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
        private ArrayList<RightTriangle> rightTriangles = new ArrayList<RightTriangle>();
        private RightTriangle previewRightTriangle;
        private Rectangle previewRectangle;
        private Circle previewCircle;

        public PaintModel() {
            newLine();
        }

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

        public void addRightTriangle(RightTriangle rt){
            rightTriangles.add(rt);
            setChanged();
            notifyObservers();
        }

        public ArrayList<RightTriangle> getRightTriangles(){return rightTriangles;}

        public void setPreviewRightTriangle(RightTriangle rt){
            previewRightTriangle=rt;
            setChanged();
            notifyObservers();
        }
        
        public void updatePreviewRightTriangle(Point endPoint) {
            previewRightTriangle.setEndPoint(endPoint); // !!IMPLEMENT THIS
            setChanged();
            notifyObservers();
        }
        
}
