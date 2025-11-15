package ca.utoronto.utm.assignment2.paint;

/**
 * This class represent a point on the canvas. They make up the vertices of shapes, and a collection of them in an
 * arrayList create a Squiggle, or Polyline line
 */
public class Point {
        double x, y; // Available to our package

        /**
         * Create a new Point at the specified coordinates
         * @param x the x coordinate
         * @param y the y coordinate
         */
        Point(double x, double y){
                this.x=x; this.y=y;
        }

        /**
         * Create a deep copy of this point
         * @return a new Point with the same coordinates
         */
        public Point copy(){
            return new Point(x,y);
        }
}
