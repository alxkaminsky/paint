package ca.utoronto.utm.assignment2.paint;


public class Circle {
        private final Point centre;
        private Point end;
        private double radius;

        public Circle(Point centre, Point end){
                this.centre = centre;
                this.end = end;
                calculateRadius();
        }

        public Point getCentre() {
                return centre;
        }

        public double getRadius() {
                return radius;
        }

        public void setEndPoint(Point end){
            this.end = end;
            calculateRadius();
        }

        private void calculateRadius(){
            double deltaX = getCentre().x - end.x;
            double deltaY = getCentre().y - end.y;
            this.radius = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        }

}
