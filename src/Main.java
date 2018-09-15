import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("sample/resources/Home.fxml"));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            //primaryStage.setFullScreen(true);
            primaryStage.setTitle("Data Structure Visualization");
            primaryStage.setScene(new Scene(root, 800, 800));

            primaryStage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}