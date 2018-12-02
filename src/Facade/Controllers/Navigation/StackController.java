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
        arrayButton.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_STACK_ARRAY"), Utils.localeProperty()));
        linkedListButton.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_LINKED_LIST"), Utils.localeProperty()));

        arrayButton.setOnAction(e -> switchScene("/Views/Stack/StackArrayImplementation.fxml"));
        linkedListButton.setOnAction(e -> switchScene("/Views/Stack/StackLinkedListImplementation.fxml"));
        backButton.setOnAction(e -> switchScene("/Views/Navigation/TypeSelection.fxml"));
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