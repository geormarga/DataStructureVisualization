package CustomElements;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class StackNodeElement extends HBox {


    private Label tracker = new Label("top"), node, index;
    private Pane arrow = new Pane();

    public StackNodeElement(String nodeText, String indexText) {
        super();
        node = new Label(nodeText);
        index = new Label(indexText);
        //get parent's initial x and y
        arrow.getChildren().add(new HorizontalArrow(this.getLayoutX(), this.getLayoutY()));

        tracker.getStyleClass().add("tracker-text");
        arrow.getStyleClass().add("h-arrow");
        node.getStyleClass().add("node");
        index.getStyleClass().add("index-text");

        this.getChildren().add(tracker);
        this.getChildren().add(arrow);
        this.getChildren().add(node);
        this.getChildren().add(index);

        this.tracker.setVisible(false);
        this.arrow.setVisible(false);

        tracker.prefHeightProperty().bind(this.heightProperty());
        arrow.prefHeightProperty().bind(this.heightProperty());
        node.prefHeightProperty().bind(this.heightProperty());
        index.prefHeightProperty().bind(this.heightProperty());
    }

    public void setTracker(boolean isFirst) {
        this.tracker.getText();
        if (isFirst) {
            this.tracker.setVisible(true);
            this.arrow.setVisible(true);
        } else {
            this.tracker.setVisible(false);
            this.arrow.setVisible(false);
        }
    }

    public void setNodeData(String text) {
        this.node.setText(text);
    }
}