import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StackScene extends Scene {

    Button stackButton, queueButton;
    Stage window;
    Scene implementationTypeScene;


    StackScene(Parent root, int width, int height, Stage stage){
        super(root, width, height);
        window = stage;
        VBox layout = (VBox) getRoot();

        queueButton = new Button();
        queueButton.setText("Queue");

        stackButton = new Button();
        stackButton.setText("Stack");



        layout.getChildren().addAll(queueButton,stackButton);
        layout.setAlignment(Pos.CENTER);
        implementationTypeScene = new ImplementationScene(new StackPane(),600,600, window);

        layout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        queueButton.setOnAction(event -> {
            window.setScene(implementationTypeScene);
            window.setFullScreen(true);
        });

        stackButton.setOnAction(event -> {
            window.setScene(implementationTypeScene);
            window.setFullScreen(true);
        });

    }
}
