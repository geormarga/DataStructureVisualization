package sample.Contollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class HomeController extends BaseViewController {

    @FXML
    public Button queueButton, stackButton;
    @FXML
    private Pane pane;

    @Override
    public void initialize() {
        super.initialize();
        pane.toBack();
        queueButton.setOnAction(e -> switchScene("../resources/QueueSelector.fxml"));
        stackButton.setOnAction(e -> switchScene("../resources/StackSelector.fxml"));
        btnClose.setOnAction(e -> System.exit(0));
    }
}
