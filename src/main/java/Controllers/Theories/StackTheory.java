package Controllers.Theories;

import CustomElements.PdfModel;
import Interfaces.ISelection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StackTheory implements ISelection {

    @FXML
    private Button backButton;

    @FXML
    private Pagination pagination;

    private PdfModel model;

    @Override
    public void initialize() {
        model = new PdfModel(getClass().getResourceAsStream("/PDF/theory.pdf"));
        pagination.setPageCount(model.numPages());
        pagination.setPageFactory(index -> new ImageView(model.getImage(index)));
        localize();
        setEventListeners();
    }

    @Override
    public void localize() {

    }

    @Override
    public void setEventListeners() {
        backButton.setOnAction(e -> {
            switchScene("/Views/Navigation/SelectTheoryView.fxml");
            model.close();
        });
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