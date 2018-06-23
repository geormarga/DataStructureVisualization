import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ImplementationScene extends Scene {

    Button arrayButton, linkedListButton;
    Stage window;


    ImplementationScene(Parent root, int width, int height, Stage stage){
        super(root, width, height);
        window = stage;
        StackPane layout = (StackPane) getRoot();

        arrayButton = new Button();
        arrayButton.setText("Array Implementation");

        linkedListButton = new Button();
        linkedListButton.setText("Linked List Implementation");

        VBox vBox = new VBox(arrayButton,linkedListButton);
        vBox.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(vBox);
        layout.setAlignment(vBox,Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

    }
}
