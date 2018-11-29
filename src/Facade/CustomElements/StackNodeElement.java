package Facade.CustomElements;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class StackNodeElement extends HBox {


    private Label tracker, node, index;
    private Pane arrow;
    private VBox arrowGroup;

    public StackNodeElement() {
        super();
        tracker = new Label("top bottom");
        node = new Label("default");
        arrow = new Pane();
        arrowGroup = new VBox();
        arrow.getChildren().add(new HorizontalArrow(this.getLayoutX(), this.getLayoutY()));
        index = new Label("0");
        //get parent's initial x and y
        tracker.getStyleClass().add("tracker-text");
        arrow.getStyleClass().add("h-arrow");
        arrowGroup.getChildren().addAll(tracker, arrow);
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
        //get parent's initial x and y
        arrow.getChildren().add(new HorizontalArrow(this.getLayoutX(), this.getLayoutY()));
        arrowGroup = new VBox();
        index = new Label(indexText);


        tracker.getStyleClass().add("tracker-text");
        arrow.getStyleClass().add("h-arrow");
        node.getStyleClass().add("node");
        index.getStyleClass().add("index-text");

        arrowGroup.getChildren().addAll(tracker, arrow);

        this.getChildren().add(arrowGroup);
        arrowGroup.setVisible(false);
        this.getChildren().add(node);
        this.getChildren().add(index);

        arrowGroup.prefWidthProperty().bind(this.heightProperty());
        node.prefWidthProperty().bind(this.heightProperty());
        index.prefWidthProperty().bind(this.heightProperty());
    }

    public void setTracker(boolean isFirst, boolean isLast) {
        this.tracker.getText();
        if (isFirst && isLast) {
            this.tracker.setText("top bottom");
            this.arrowGroup.setVisible(true);
        } else if (isFirst) {
            this.tracker.setText("top");
            this.arrowGroup.setVisible(true);
        } else if (isLast) {
            this.tracker.setText("bottom");
            this.arrowGroup.setVisible(true);
        } else {
            this.tracker.setText("");
            this.arrowGroup.setVisible(false);
        }
    }

    public void setNodeData(String text) {
        this.node.setText(text);
    }
}