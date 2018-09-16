package sample.Contollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StackController extends BaseViewController {

    @FXML
    public Button arrayButton, linkedListButton, backButton;

    @Override
    public void initialize() {
        super.initialize();
        arrayButton.setOnAction(e -> switchScene("../resources/Stack/StackArrayImplementation.fxml"));
        linkedListButton.setOnAction(e -> switchScene("../resources/Stack/StackLinkedListImplementation.fxml"));
        backButton.setOnAction(e -> switchScene("../resources/Home.fxml"));
    }
}
