package sample.Contollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HomeController implements ISelection{

    @FXML
    VBox buttonContainer;
    @FXML
    private Button queueButton, stackButton;

    @Override
    public void initialize() {
        queueButton.setOnAction(e -> switchScene("../resources/QueueSelector.fxml"));
        stackButton.setOnAction(e -> switchScene("../resources/StackSelector.fxml"));
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
