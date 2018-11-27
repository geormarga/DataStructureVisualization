package Facade.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import Facade.Interfaces.ISelection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class HomeController implements ISelection {

    @FXML
    private Button theoryButton, typeButton;
    @FXML
    private ToggleGroup myToggleGroup;
    @FXML
    private ToggleButton en, gr;

    @Override
    public void initialize() {
        en.setOnAction(e -> getPropertiesFile((ToggleButton) e.getSource()));
        gr.setOnAction(e -> getPropertiesFile((ToggleButton) e.getSource()));
        theoryButton.setOnAction(e -> switchScene("../Views/Navigation/TheorySelection.fxml"));
        typeButton.setOnAction(e -> switchScene("../Views/Navigation/TypeSelection.fxml"));
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

    public void getPropertiesFile(ToggleButton toggle) {
        String localisationSrc = "src/sample/resources/application_en.properties";

        if (toggle.getId().equals("en")) {
            localisationSrc = "src/sample/resources/application_en.properties";
        }

        if (toggle.getId().equals("gr")) {
            localisationSrc = "src/sample/resources/application_gr.properties";
        }
        try {
            Properties prop = new Properties();
            InputStreamReader input;
            input = new InputStreamReader(new FileInputStream(localisationSrc), "UTF8");
            prop.load(input);
            System.out.println(prop.getProperty("TRANSLATE_THEORY"));
            System.out.println(toggle.getScene().lookup("text"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
