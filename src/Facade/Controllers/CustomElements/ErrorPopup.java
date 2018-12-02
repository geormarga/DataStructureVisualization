package Controllers.CustomElements;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorPopup {

    @FXML
    private Label informationText;
    @FXML
    private Button dismissButton;

    public void initialize() {
        dismissButton.setOnAction(e -> {
            Stage dialog = (Stage) dismissButton.getScene().getWindow();
            dialog.getOwner().getScene().getRoot().setEffect(null);
            dialog.close();
        });
    }

    public void setInformationText(String text) {
        this.informationText.setText(text);
    }
}