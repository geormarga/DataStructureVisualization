package Facade.CustomElements;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class StackNodeElement extends HBox {


    private Label tracker, node, index;
    private Pane arrow;

    public StackNodeElement() {
        super();
        tracker = new Label("top bottom");
        node = new Label("default");
        arrow = new Pane();
        arrow.getChildren().add(new HorizontalArrow(this.getLayoutX(), this.getLayoutY()));
        index = new Label("0");
        //get parent's initial x and y
        tracker.getStyleClass().add("tracker-text");
        arrow.getStyleClass().add("arrow");
        node.getStyleClass().add("node");
        index.getStyleClass().add("index-text");

        this.getChildren().add(tracker);
        this.getChildren().add(arrow);
        this.getChildren().add(node);
        this.getChildren().add(index);
    }

    public StackNodeElement(String nodeText, String indexText) {
        super();
        tracker = new Label("");
        node = new Label(nodeText);
        arrow = new Pane();
        arrow.getChildren().add(new HorizontalArrow(this.getLayoutX(), this.getLayoutY()));
        index = new Label(indexText);

        tracker.getStyleClass().add("tracker-text");
        arrow.getStyleClass().add("arrow");
        node.getStyleClass().add("node");
        index.getStyleClass().add("index-text");

        this.getChildren().add(tracker);
        this.getChildren().add(arrow);
        arrow.setVisible(false);
        this.getChildren().add(node);
        this.getChildren().add(index);

        tracker.prefWidthProperty().bind(this.widthProperty());
        arrow.prefWidthProperty().bind(this.widthProperty());
        node.prefWidthProperty().bind(this.widthProperty());
        index.prefWidthProperty().bind(this.widthProperty());
    }

    public void setTracker(boolean isFirst, boolean isLast) {
        this.tracker.getText();
        if (isFirst && isLast) {
            this.tracker.setText("top bottom");
            this.arrow.setVisible(true);
        } else if (isFirst) {
            this.tracker.setText("top");
            this.arrow.setVisible(true);
        } else if (isLast) {
            this.tracker.setText("bottom");
            this.arrow.setVisible(true);
        } else {
            this.tracker.setText("");
            this.arrow.setVisible(false);
        }
    }

    public void setNodeData(String text) {
        this.node.setText(text);
    }
}