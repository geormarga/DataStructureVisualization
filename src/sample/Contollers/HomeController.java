package sample.Contollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    Group parentGroup;
    @FXML
    public Button btnMaximize, btnMinimize, btnClose;
    @FXML
    HBox toolbar;
    @FXML
    VBox vboxContainer;
    @FXML
    public Button queueButton, stackButton;

    public void initialize() {
        queueButton.setOnAction(e -> switchScene("../resources/QueueSelector.fxml"));
        stackButton.setOnAction(e -> switchScene("../resources/StackSelector.fxml"));
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
    }

    public void switchScene(String resource) {
        Stage stage = (Stage) parentGroup.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource(resource));
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
