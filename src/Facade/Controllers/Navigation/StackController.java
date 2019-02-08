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

public class StackController implements ISelection {

    @FXML
    private Button arrayButton, linkedListButton, backButton;

    @Override
    public void initialize() {
        localize();
        setEventListeners();
    }

    @Override
    public void localize() {
        arrayButton.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_STACK_ARRAY"), Utils.localeProperty()));
        linkedListButton.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_LINKED_LIST"), Utils.localeProperty()));
    }

    @Override
    public void setEventListeners() {
        arrayButton.setOnAction(e -> switchScene("/Views/Stack/StackArrayView.fxml"));
        linkedListButton.setOnAction(e -> switchScene("/Views/Stack/StackLinkedListView.fxml"));
        backButton.setOnAction(e -> switchScene("/Views/Navigation/SelectLinearListView.fxml"));
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