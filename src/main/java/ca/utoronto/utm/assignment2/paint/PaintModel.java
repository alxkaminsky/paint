package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
        private ArrayList<ArrayList<Point>> points=new ArrayList<ArrayList<Point>>();
        private ArrayList<Circle> circles=new ArrayList<Circle>();
        private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();

        public PaintModel() {
            newLine();
        }

        public void addPoint(Point p){
            points.getLast().add(p);
            this.setChanged();
            this.notifyObservers();
        }
        public void newLine(){
            points.add(new ArrayList<Point>());
        }
        public ArrayList<ArrayList<Point>> getPoints(){
                return points;
        }

        public void addCircle(Circle c){
                this.circles.add(c);
                this.setChanged();
                this.notifyObservers();
        }

        public ArrayList<Circle> getCircles(){
        return circles;
    }

        public void addRectangle(Rectangle r){
            this.rectangles.add(r);
            this.setChanged();
            this.notifyObservers();
        }

        public ArrayList<Rectangle> getRectangles(){return rectangles;}
}
