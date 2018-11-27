package Facade.CustomElements;

import Facade.resources.Arrow;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class NodeElement extends VBox {


    private Label tracker, node, index;
    private Pane arrow;

    public NodeElement() {
        super();
        tracker = new Label("top bottom");
        node = new Label("default");
        arrow = new Pane();
        arrow.getChildren().add(new Arrow(this.getLayoutX(), this.getLayoutY()));
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

    public NodeElement(boolean isFirst, boolean isLast, String nodeText, String indexText) {
        super();
        String trackerText;
        if (isFirst && isLast) {
            trackerText = "top bottom";
        } else if (isFirst) {
            trackerText = "top";
        } else if (isLast) {
            trackerText = "bottom";
        } else {
            trackerText = "";
        }
        tracker = new Label(trackerText);
        node = new Label(nodeText);
        arrow = new Pane();
        arrow.getChildren().add(new Arrow(this.getLayoutX(), this.getLayoutY()));
        index = new Label(indexText);

        tracker.getStyleClass().add("tracker-text");
        arrow.getStyleClass().add("arrow");
        node.getStyleClass().add("node");
        index.getStyleClass().add("index-text");

        if (!isFirst && !isLast) {
            arrow.setVisible(false);
        }

        this.getChildren().add(tracker);
        this.getChildren().add(arrow);
        this.getChildren().add(node);
        this.getChildren().add(index);

        tracker.prefWidthProperty().bind(this.widthProperty());
        arrow.prefWidthProperty().bind(this.widthProperty());
        node.prefWidthProperty().bind(this.widthProperty());
        index.prefWidthProperty().bind(this.widthProperty());
    }
}