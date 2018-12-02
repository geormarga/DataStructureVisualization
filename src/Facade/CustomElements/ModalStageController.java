package CustomElements;

import Controllers.ErrorPopup;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ModalStageController extends Stage {
    private Delta dragDelta = new Delta();

    public ModalStageController(Stage primaryStage, String text) {
        super();
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getResource("/Views/ErrorPopup.fxml"));
            Parent root = loader.load();
            this.setScene(new Scene(root, Color.TRANSPARENT));

            this.initStyle(StageStyle.TRANSPARENT);
            this.initModality(Modality.WINDOW_MODAL);
            this.initOwner(primaryStage);

            ((ErrorPopup) loader.getController()).setInformationText(text);

            root.setOnMouseDragged(mouseEvent -> {
                this.setX(mouseEvent.getScreenX() + dragDelta.x);
                this.setY(mouseEvent.getScreenY() + dragDelta.y);
            });

            root.setOnMousePressed(mouseEvent -> {
                dragDelta.x = this.getX() - mouseEvent.getScreenX();
                dragDelta.y = this.getY() - mouseEvent.getScreenY();
            });
            this.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    class Delta {
        double x, y;
    }
}
