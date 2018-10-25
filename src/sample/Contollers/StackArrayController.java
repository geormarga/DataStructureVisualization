package sample.Contollers;


import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class StackArrayController implements ISelection {

    @FXML
    VBox buttonContainer;

    @Override
    public void switchScene(String resource) {

    }

    @Override
    public void initialize() {
        System.out.println("Heyyy");
        System.out.println("Heyyy");
    }
}