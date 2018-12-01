import Facade.CustomElements.ErrorPopup;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getResource("/Facade/Views/BaseView.fxml"));
            Parent root = loader.load();
            //primaryStage.initStyle(StageStyle.UNDECORATED);
            //primaryStage.setFullScreen(true);
            primaryStage.setTitle("Data Structure Visualization");
            primaryStage.setScene(new Scene(root, 800, 800));
            primaryStage.show();
            new ErrorPopup(primaryStage, "This is a test text.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}