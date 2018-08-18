import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    private static Stage primaryStageObj;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStageObj = primaryStage;

        try {
            Parent root = FXMLLoader.load(getClass().getResource("sample/resources/Home.fxml"));
            //primaryStage.initStyle(StageStyle.UNDECORATED);
            //primaryStage.setFullScreen(true);
            primaryStage.setTitle("Data Structure Visualization");
            primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public Stage getPrimaryStageObj(){
        return primaryStageObj;
    }
}