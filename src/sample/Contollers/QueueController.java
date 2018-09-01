package sample.Contollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class QueueController {

    @FXML
    public Button arrayButton, linkedListButton, arrayShiftingButton, arrayCircularButton, backButton;
    @FXML
    VBox parentVbox;

    public void initialize() {
        arrayButton.setOnAction(e -> switchScene("../resources/Queue/QueueArrayImplementation.fxml"));
        linkedListButton.setOnAction(e -> switchScene("../resources/Queue/QueueLinkedListImplementation.fxml"));
        arrayShiftingButton.setOnAction(e -> switchScene("../resources/Queue/QueueArrayShiftingVirtualOverflowImplementation.fxml"));
        arrayCircularButton.setOnAction(e -> switchScene("../resources/Queue/QueueArrayCircularVirtualOverflowImplementation.fxml"));
        backButton.setOnAction(e -> switchScene("../resources/Home.fxml"));
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
