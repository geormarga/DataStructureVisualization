package sample.Contollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ImplementationController extends BaseViewController {

    @FXML
    public Button queueButton, stackButton;


    public void initialize() {
        queueButton.setOnAction(e -> switchScene("../resources/QueueSelector.fxml"));
        stackButton.setOnAction(e -> switchScene("../resources/StackSelector.fxml"));
    }
}
