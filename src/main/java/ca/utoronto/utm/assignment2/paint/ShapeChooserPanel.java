package ca.utoronto.utm.assignment2.paint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

        private View view;

        public ShapeChooserPanel(View view) {

                this.view = view;

                Button circleButton = new Button();
                Image circleIcon = new Image(getClass().getResourceAsStream("/icons/circle.png"));
                ImageView circleIconView = new ImageView(circleIcon);
                circleIconView.setFitWidth(50);
                circleIconView.setFitHeight(50);
                circleButton.setGraphic(circleIconView);
                this.add(circleButton, 0, 0);
                circleButton.setOnAction(this);
                circleButton.setUserData("Circle");

                Button rectangleButton = new Button();
                Image rectangleIcon = new Image(getClass().getResourceAsStream("/icons/rectangle.png"));
                ImageView rectangleIconView = new ImageView(rectangleIcon);
                rectangleIconView.setFitWidth(50);
                rectangleIconView.setFitHeight(50);
                rectangleButton.setGraphic(rectangleIconView);
                this.add(rectangleButton, 0, 1);
                rectangleButton.setOnAction(this);
                rectangleButton.setUserData("Rectangle");

                Button squareButton = new Button();
                Image squareIcon = new Image(getClass().getResourceAsStream("/icons/square.png"));
                ImageView squareIconView = new ImageView(squareIcon);
                squareIconView.setFitWidth(50);
                squareIconView.setFitHeight(50);
                squareButton.setGraphic(squareIconView);
                this.add(squareButton, 0, 2);
                squareButton.setOnAction(this);
                squareButton.setUserData("Square");

                Button squiggleButton = new Button();
                Image squiggleIcon = new Image(getClass().getResourceAsStream("/icons/squiggle.png"));
                ImageView squiggleIconView = new ImageView(squiggleIcon);
                squiggleIconView.setFitWidth(50);
                squiggleIconView.setFitHeight(50);
                squiggleButton.setGraphic(squiggleIconView);
                this.add(squiggleButton, 0, 3);
                squiggleButton.setOnAction(this);
                squiggleButton.setUserData("Squiggle");

                Button polylineButton = new Button();
                Image polylineIcon = new Image(getClass().getResourceAsStream("/icons/polyline.png"));
                ImageView polylineIconView = new ImageView(polylineIcon);
                polylineIconView.setFitWidth(50);
                polylineIconView.setFitHeight(50);
                polylineButton.setGraphic(polylineIconView);
                this.add(polylineButton, 0, 4);
                polylineButton.setOnAction(this);
                polylineButton.setUserData("Polyline");

//                String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", "Polyline" };
//
//                int row = 0;
//                for (String label : buttonLabels) {
//                        Button button = new Button(label);
//                        button.setMinWidth(100);
//                        this.add(button, 0, row);
//                        row++;
//                        button.setOnAction(this);
//                }
        }

        @Override
        public void handle(ActionEvent event) {
                Button button = (Button) event.getSource();
                String command = (String) button.getUserData();
                view.setMode(command);
                System.out.println(command);
        }
}


