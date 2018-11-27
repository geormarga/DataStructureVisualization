package Facade.Controllers.Navigation;

import Facade.Utils;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import Facade.Interfaces.ISelection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import static Facade.Utils.i18n;

public class HomeController implements ISelection {

    @FXML
    private Button theoryButton, typeButton;
    @FXML
    private ToggleGroup myToggleGroup;
    @FXML
    private ToggleButton en, gr;

    @Override
    public void initialize() {
        en.setOnAction(e -> {
            Utils.setLocale(new Locale("en"));
        });

        gr.setOnAction(e -> {
            Utils.setLocale(new Locale("gr"));
        });

        theoryButton.setOnAction(e -> switchScene("../../Views/Navigation/TheorySelection.fxml"));
        typeButton.setOnAction(e -> switchScene("../../Views/Navigation/TypeSelection.fxml"));
        theoryButton.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_THEORY"), Utils.localeProperty()));
        typeButton.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_IMPLEMENTATION"), Utils.localeProperty()));
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
