package sample.Contollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class StackController extends BaseViewController {

    @FXML
    public Button arrayButton, linkedListButton, backButton;
    @FXML
    private Pane pane;

    @Override
    public void initialize() {
        super.initialize();
        pane.toBack();
        arrayButton.setOnAction(e -> switchScene("../resources/Stack/StackArrayImplementation.fxml"));
        linkedListButton.setOnAction(e -> switchScene("../resources/Stack/StackLinkedListImplementation.fxml"));
        backButton.setOnAction(e -> switchScene("../resources/Home.fxml"));
    }
}
