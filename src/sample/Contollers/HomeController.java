package sample.Contollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController extends BaseViewController {

    @FXML
    private Button queueButton, stackButton;

    @Override
    public void initialize() {
        super.initialize();
        queueButton.setOnAction(e -> switchScene("../resources/QueueSelector.fxml"));
        stackButton.setOnAction(e -> switchScene("../resources/StackSelector.fxml"));
    }
}
