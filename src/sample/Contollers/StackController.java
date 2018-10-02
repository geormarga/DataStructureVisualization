package sample.Contollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class StackController implements ISelection {

    @FXML
    VBox buttonContainer;
    @FXML
    private Button arrayButton, linkedListButton, backButton;

    @Override
    public void initialize() {
        buttonContainer.toFront();
        arrayButton.setOnAction(e -> switchScene("../resources/Stack/StackArrayImplementation.fxml"));
        linkedListButton.setOnAction(e -> switchScene("../resources/Stack/StackLinkedListImplementation.fxml"));
        backButton.setOnAction(e -> switchScene("../resources/Home.fxml"));
    }

    @Override
    public void switchScene(String resource) {
        buttonContainer = (VBox) arrayButton.getParent();
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
