package sample.Contollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TypeController implements ISelection {

    @FXML
    private Button queueButton, stackButton;

    @Override
    public void initialize() {
        queueButton.setOnAction(e -> switchScene("../resources/Navigation/QueueSelector.fxml"));
        stackButton.setOnAction(e -> switchScene("../resources/Navigation/StackSelector.fxml"));
    }

    @Override
    public void switchScene(String resource) {
        Parent uiView = (Parent) queueButton.getScene().lookup("#viewContainer");
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
