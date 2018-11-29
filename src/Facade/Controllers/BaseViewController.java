package Facade.Controllers;

import Facade.Interfaces.ISelection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class BaseViewController {
    @FXML
    Group parent;
    @FXML
    public Button btnMaximize, btnMinimize, btnClose;
    @FXML
    private Pane pane;
    @FXML
    HBox toolbar;
    @FXML
    VBox vboxContainer;
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Parent buttonContainer, viewContainer;

    @FXML
    private ISelection viewContainerController;

    public void initialize() {

        pane.toBack();

        btnClose.setOnAction(e -> closeWindow());

        btnMinimize.setOnAction(event -> minimizeWindow(event));

        btnMaximize.setOnAction(event -> maximizeWindow(event));

        parent.setOnMousePressed(event -> grabWindow(event));

        parent.setOnMouseDragged(event -> moveWindow(event));

        viewContainerController.initialize();
    }

    public void closeWindow() {
        System.exit(0);
    }

    public void minimizeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    public void maximizeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Screen screen = Screen.getPrimary();
        stage.setWidth(screen.getBounds().getWidth());
        stage.setHeight(screen.getBounds().getHeight());
        stage.show();
    }

    public void grabWindow(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    public void moveWindow(MouseEvent event) {
        Stage stage = (Stage) ((Group) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }
}