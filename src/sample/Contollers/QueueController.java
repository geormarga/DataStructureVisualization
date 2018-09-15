package sample.Contollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class QueueController extends BaseViewController {


    @FXML
    public Button arrayButton, linkedListButton, arrayShiftingButton, arrayCircularButton, backButton;

    public void initialize() {
        super.initialize();
        arrayButton.setOnAction(e -> switchScene("../resources/Queue/QueueArrayImplementation.fxml"));
        linkedListButton.setOnAction(e -> switchScene("../resources/Queue/QueueLinkedListImplementation.fxml"));
        arrayShiftingButton.setOnAction(e -> switchScene("../resources/Queue/QueueArrayShiftingVirtualOverflowImplementation.fxml"));
        arrayCircularButton.setOnAction(e -> switchScene("../resources/Queue/QueueArrayCircularVirtualOverflowImplementation.fxml"));
        backButton.setOnAction(e -> switchScene("../resources/Home.fxml"));
    }
}
