package sample.Contollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class StackController {

    @FXML
    Group parentGroup;
    @FXML
    public Button arrayButton, linkedListButton, backButton;
    @FXML
    VBox parentVbox;
    @FXML
    public Button btnMaximize, btnMinimize, btnClose;

    public void initialize() {
        arrayButton.setOnAction(e -> switchScene("../resources/Stack/StackArrayImplementation.fxml"));
        linkedListButton.setOnAction(e -> switchScene("../resources/Stack/StackLinkedListImplementation.fxml"));
        backButton.setOnAction(e -> switchScene("../resources/Home.fxml"));
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
