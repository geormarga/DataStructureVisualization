package sample.Contollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ImplementationController {

    @FXML
    public Button queueButton, stackButton;
    @FXML
    VBox parentVbox;

    public void initialize() {
        queueButton.setOnAction(e -> switchScene("../resources/QueueSelector.fxml"));
        stackButton.setOnAction(e -> switchScene("../resources/StackSelector.fxml"));
    }

    public void switchScene(String resource) {
        Stage stage = (Stage) parentVbox.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource(resource));
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
