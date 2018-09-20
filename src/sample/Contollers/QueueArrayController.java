package sample.Contollers;

public class QueueArrayController extends BaseViewController {

    @Override
    public void initialize() {
        super.initialize();
    }

    /*
    @FXML
    private ObservableList<Label> list;
    @FXML
    private TilePane tilePane;
    private List<Timeline> jobs;

    @Override
    public void initialize() {
        super.initialize();
        jobs = new ArrayList<>();


        int size = 800*800;
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

    int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    Timeline createJob(Label label) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(randomWithRange(2000, 3000)), ae -> label.setText(Integer.toString(randomWithRange(0, 1)))));
        timeline.setCycleCount(Animation.INDEFINITE);
        return timeline;
    }
    */
}
