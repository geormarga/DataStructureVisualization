package Controllers.Navigation;

import Facade.Interfaces.ISelection;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class VideoController implements ISelection {

    private ObservableList<Label> list;
    @FXML
    private TilePane tilePane;
    private List<Animation> jobs;

    @Override
    public void initialize() {
        jobs = new ArrayList<>();

        list = FXCollections.observableArrayList();

        int size = 800 * 600;
        int quantity = size / 32;
        for (int i = 0; i < quantity; i++) {
            list.add(new Label(Integer.toString(randomWithRange(0, 1))));
        }

        tilePane.getChildren().addAll(list);

        list.forEach(item -> {
            jobs.add(createJob(item));
        });

        jobs.forEach(timeline -> timeline.play());
    }

    @Override
    public void localize(){

    }

    @Override
    public void setEventListeners(){

    }

    @Override
    public void switchScene(String takis) {

    }

    int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    boolean randomBool(int value) {
        if (value == 1) return true;
        return false;
    }

    int toggle(int number) {
        return number == 0 ? 1 : 0;
    }

    Animation createJob(Label label) {
        int durationTime = randomWithRange(7000, 15000);

        FadeTransition fadeOut = new FadeTransition(Duration.millis(durationTime), label);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(evt -> {
            label.visibleProperty().set(randomBool((int) (Math.random()*20)));
            label.setText(Integer.toString(toggle(Integer.parseInt(label.getText()))));
        });

        FadeTransition fadeIn = new FadeTransition(Duration.millis(durationTime), label);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        //fadeIn.setOnFinished(evt -> label.setText(Integer.toString(toggle(1))));

        SequentialTransition trans = new SequentialTransition();
        trans.getChildren().addAll(fadeOut, fadeIn);


        trans.setCycleCount(Animation.INDEFINITE);
        return trans;
    }
}
