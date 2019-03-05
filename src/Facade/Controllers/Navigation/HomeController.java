package Controllers.Navigation;

import Facade.Interfaces.ISelection;
import Facade.Utils;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Locale;

public class HomeController implements ISelection {

    @FXML
    private Button theoryButton, typeButton, videoButton;
    @FXML
    private ImageView localeEn, localeGr;
    @FXML
    private ToggleGroup myToggleGroup;
    @FXML
    private ToggleButton en, gr;

    @Override
    public void initialize() {
        localize();
        setEventListeners();
    }

    @Override
    public void localize() {
        theoryButton.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_THEORY"), Utils.localeProperty()));
        typeButton.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_IMPLEMENTATION"), Utils.localeProperty()));
    }

    private void getActiveLocaleImage(Locale locale) {
        if (locale == Locale.ENGLISH) {
            localeEn.setImage(new Image("en_colored.png"));
            localeGr.setImage(new Image("gr.png"));
        } else {
            localeEn.setImage(new Image("en.png"));
            localeGr.setImage(new Image("gr_colored.png"));
        }
    }

    @Override
    public void setEventListeners() {
        getActiveLocaleImage(Utils.getLocale());
        en.setOnAction(e -> {
            Utils.setLocale(Locale.ENGLISH);
            getActiveLocaleImage(Utils.getLocale());
        });

        gr.setOnAction(e -> {
            Utils.setLocale(new Locale("gr"));
            getActiveLocaleImage(Utils.getLocale());
        });

        theoryButton.setOnAction(e -> switchScene("/Views/Navigation/SelectTheoryView.fxml"));
        typeButton.setOnAction(e -> switchScene("/Views/Navigation/SelectLinearListView.fxml"));
        videoButton.setOnAction(e -> switchScene("/Views/VideoContent.fxml"));
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
