package sample.Contollers;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

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

        btnClose.setOnAction(e -> System.exit(0));

        btnMinimize.setOnAction(event -> {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setIconified(true);
        });

        btnMaximize.setOnAction(event -> {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Screen screen = Screen.getPrimary();
            stage.setWidth(screen.getBounds().getWidth());
            stage.setHeight(screen.getBounds().getHeight());
            stage.show();
        });

        parent.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        parent.setOnMouseDragged(event -> {
            Stage stage = (Stage) ((Group) event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        viewContainerController.initialize();
    }
}
