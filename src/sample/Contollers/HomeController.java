package sample.Contollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HomeController implements ISelection {

    @FXML
    private Button theoryButton, typeButton;

    @Override
    public void initialize() {
        theoryButton.setOnAction(e -> switchScene("../resources/Navigation/TheorySelection.fxml"));
        typeButton.setOnAction(e -> switchScene("../resources/Navigation/TypeSelection.fxml"));
    }

    @Override
    public void switchScene(String resource) {
        Parent uiView = (Parent) theoryButton.getScene().lookup("#viewContainer");
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
