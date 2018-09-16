package sample.Contollers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class QueueArrayController extends BaseViewController {

    @FXML
    private ObservableList<Label> list;
    private List<Timeline> jobs;

    @Override
    public void initialize() {
        super.initialize();
        jobs = new ArrayList<>();

        for (int i=0;i<50;i++)
        {
            list.add(new Label("21"));
        }


        list.forEach(item -> {
            jobs.add(createJob(item));
        });

        jobs.forEach(timeline -> timeline.play());
    }

    int randomWithRange(int min, int max) {
        System.out.println("Executed!");
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    Timeline createJob(Label label) {
        System.out.println(" X: " + label.getLayoutX() + " Y: " + label.getLayoutY());
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(randomWithRange(0, 2000)), ae -> label.setText(Integer.toString(randomWithRange(0, 1)))));
        timeline.setCycleCount(Animation.INDEFINITE);
        return timeline;
    }
}
