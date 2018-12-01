package Facade.CustomElements;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ErrorPopup {
    private Stage dialog;
    @FXML
    private Button dismissButton;
    @FXML
    private Label infoText;
    private Parent root;
    private Delta dragDelta;


    public ErrorPopup(Stage primaryStage, String text) {
        dragDelta = new Delta();
        dialog = new Stage(StageStyle.TRANSPARENT);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(primaryStage);
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getResource("/Facade/Views/ErrorPopup.fxml"));
            root = loader.load();

            dialog.setScene(new Scene(root, Color.TRANSPARENT));
            infoText.setText(text);

            initialize();
            dialog.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void initialize() {
        dismissButton.setOnAction(e -> {
            dialog.getOwner().getScene().getRoot().setEffect(null);
            dialog.close();
        });

        root.setOnMousePressed(mouseEvent -> {
            dragDelta.x = dialog.getX() - mouseEvent.getScreenX();
            dragDelta.y = dialog.getY() - mouseEvent.getScreenY();
        });

        root.setOnMouseDragged(mouseEvent -> {
            dialog.setX(mouseEvent.getScreenX() + dragDelta.x);
            dialog.setY(mouseEvent.getScreenY() + dragDelta.y);
        });
    }

    class Delta {
        double x, y;
    }
}