package sample.Contollers;

import Models.Queues.Queue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class QueueController implements ISelection {

    @FXML
    VBox buttonContainer;
    @FXML
    private Button arrayButton, linkedListButton, arrayShiftingButton, arrayCircularButton, backButton;

    @Override
    public void initialize() {
        arrayButton.setOnAction(e -> switchScene("../resources/Queue/QueueArrayImplementation.fxml"));
        linkedListButton.setOnAction(e -> switchScene("../resources/Queue/QueueLinkedListImplementation.fxml"));
        arrayShiftingButton.setOnAction(e -> switchScene("../resources/Queue/QueueArrayShiftingVirtualOverflowImplementation.fxml"));
        arrayCircularButton.setOnAction(e -> switchScene("../resources/Queue/QueueArrayCircularVirtualOverflowImplementation.fxml"));
        backButton.setOnAction(e -> switchScene("../resources/Home.fxml"));
    }

    @Override
    public void switchScene(String resource) {
        buttonContainer.getChildren().clear();
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getResource(resource));
            buttonContainer.getChildren().add(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
