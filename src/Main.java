import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application implements EventHandler<ActionEvent> {

    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = new Parent(){};
        try{
            root = FXMLLoader.load(getClass().getResource("sample/Home.fxml"));
        }catch (Exception ex){}
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Data Structure Visualization");
        primaryStage.setScene(new Scene(root,primaryStage.getWidth(),primaryStage.getHeight()));
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {

    }
}