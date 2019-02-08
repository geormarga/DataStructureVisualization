package Controllers.Navigation;

import Facade.Interfaces.ISelection;
import Facade.Utils;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TypeController implements ISelection {

    @FXML
    private Button queueButton, stackButton, backButton;

    @Override
    public void initialize() {
        localize();
        setEventListeners();
    }

    @Override
    public void localize(){
        stackButton.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_STACK"), Utils.localeProperty()));
        queueButton.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_QUEUE"), Utils.localeProperty()));
    }

    @Override
    public void setEventListeners(){
        stackButton.setOnAction(e -> switchScene("/Views/Navigation/SelectStackView.fxml"));
        queueButton.setOnAction(e -> switchScene("/Views/Navigation/SelectQueueView.fxml"));
        backButton.setOnAction(e -> switchScene("/Views/Navigation/Home.fxml"));
    }

    @Override
    public void switchScene(String resource) {
        Parent uiView = (Parent) backButton.getScene().lookup("#viewContainer");
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