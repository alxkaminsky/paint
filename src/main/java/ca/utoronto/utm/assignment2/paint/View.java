package ca.utoronto.utm.assignment2.paint;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The View component for the Paint application, responsible to build the entire user interface. This includes the
 * menu bar and the draw setting panel.
 */
public class View implements EventHandler<ActionEvent>  {

        private PaintModel paintModel;
        private PaintPanel paintPanel;
        private DrawSettingsPanel drawSettingsPanel;
        private Canvas canvas;

    /**
     * Construct the full UI for the application and display it in the given stage
     * @param model the PaintModel containing all the drawing states
     * @param stage the main javafx application window
     */
    public View(PaintModel model, Stage stage) {
            this.paintModel = model;

            paintPanel = new PaintPanel(this.paintModel);
            drawSettingsPanel = new DrawSettingsPanel(model);

            canvas = new Canvas(800, 600);

            VBox vBox = new VBox();
            vBox.getChildren().addAll(createMenuBar(), drawSettingsPanel);

            BorderPane root = new BorderPane();
            root.setTop(vBox);
            root.setCenter(this.paintPanel);
            Scene scene = new Scene(root, 1000, 700);

            // These two lines binds the canvas size to the window. So the canvas will change the size dynamically
            // as the user change the size of the application window.
            paintPanel.widthProperty().bind(root.widthProperty());
            // To account for toolbar
            paintPanel.heightProperty().bind(root.heightProperty().subtract(vBox.heightProperty()));

            stage.setScene(scene);
            stage.setTitle("Paint");
            stage.show();

            //God awful architecture MUST be fixed immediately
            scene.setOnKeyPressed(e -> {
                switch (e.getCode()) {
                    case Z:
                        if (e.isControlDown()) {
                            paintModel.deleteMostRecentShape();
                        }
                        break;
                    case C:
                        if (e.isControlDown()) {
                            paintModel.copy();
                        }
                        break;
                    case V:
                        if (e.isControlDown()) {
                            paintModel.paste();
                        }
                        break;
                    case X:
                        if (e.isControlDown()) {
                            paintModel.copy();
                            paintModel.deleteSelected();
                        }
                        break;
                    case BACK_SPACE:
                        paintModel.deleteSelected();
                        break;
                }
            });
        }

        private MenuBar createMenuBar() {

                MenuBar menuBar = new MenuBar();
                Menu menu;
                MenuItem menuItem;

                // A menu for File

                menu = new Menu("File");

                menuItem = new MenuItem("New");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Open");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Save");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menu.getItems().add(new SeparatorMenuItem());

                menuItem = new MenuItem("Exit");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuBar.getMenus().add(menu);
                menuItem = new MenuItem("Cut");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Copy");
                menuItem.setOnAction(e -> {
                    paintModel.copy();
                });
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Paste");
                menuItem.setOnAction(e -> {
                    paintModel.paste();
                });
                menu.getItems().add(menuItem);

                menu.getItems().add(new SeparatorMenuItem());
                menuItem = new MenuItem("Undo");
                menuItem.setOnAction(e -> {
                    paintModel.deleteMostRecentShape();
                });
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Redo");
                menuItem.setOnAction(e -> {
                    paintModel.deleteAllShapes();
                });
                menu.getItems().add(menuItem);

                menuBar.getMenus().add(menu);

                return menuBar;
        }

    /**
     * Handle menu actions.
     * @param event
     */
    @Override
        public void handle(ActionEvent event) {

                String command = ((MenuItem) event.getSource()).getText();
                System.out.println(command);

                if (command.equals("Exit")) {
                        Platform.exit();
                }
        }
}
