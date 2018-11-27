package Facade.Controllers.Navigation;

import Facade.Interfaces.ISelection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TheoryController implements ISelection {

    @FXML
    private Button queueButton, stackButton, backButton;

    @Override
    public void initialize() {
        queueButton.setOnAction(e -> switchScene("../../Views/Theory/QueueTheory.fxml"));
        stackButton.setOnAction(e -> switchScene("../../Views/Theory/StackTheory.fxml"));
        backButton.setOnAction(e -> switchScene("../../Views/Navigation/Home.fxml"));
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