package sample.Contollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class StackController implements ISelection {

    @FXML
    private Button arrayButton, linkedListButton, backButton;

    @Override
    public void initialize() {
        System.out.println("Heyyy!");
        arrayButton.setOnAction(e -> switchScene("../resources/Stack/StackArrayImplementation.fxml"));
        linkedListButton.setOnAction(e -> switchScene("../resources/Stack/StackLinkedListImplementation.fxml"));
        backButton.setOnAction(e -> switchScene("../resources/Navigation/Home.fxml"));
    }

    @Override
    public void switchScene(String resource) {
        Parent uiView = (Parent) arrayButton.getScene().lookup("#viewContainer");
        FXMLLoader loader = new FXMLLoader();
        VBox parent = (VBox) uiView.getParent();
        try {
            loader.setLocation(getClass().getResource(resource));
            parent.getChildren().remove(uiView);
            parent.getChildren().add(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
