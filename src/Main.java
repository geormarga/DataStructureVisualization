import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class Main extends Application implements EventHandler<ActionEvent> {

    Stage window;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Data Structure Visualization");
        window.setFullScreen(true);

        Scene scene = new StackScene(new VBox(),600,600, window);
        window.setScene(scene);

        window.show();
    }

    @Override
    public void handle(ActionEvent event) {

    }
}
