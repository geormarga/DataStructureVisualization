package Controllers.Navigation;

import Facade.Interfaces.ISelection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.nio.file.Paths;

public class StackTheory implements ISelection {

    @FXML
    private Button backButton;

    @FXML
    private Pagination pagination;

    private PdfModel model;

    @Override
    public void initialize() {
        model = new PdfModel(Paths.get(getClass().getResource("/theory.pdf").getPath()));
        pagination.setPageCount(model.numPages());
        pagination.setPageFactory(index -> new ImageView(model.getImage(index)));

        backButton.setOnAction(e -> switchScene("/Views/Navigation/TheorySelection.fxml"));
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