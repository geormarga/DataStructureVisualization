package sample.Contollers;

import Models.Node;
import Models.Stacks.StackArray;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class StackArrayController implements ISelection {

    @FXML
    VBox buttonContainer;

    ObservableList<NodeElement> visibleList = FXCollections.observableArrayList();
    @FXML
    TilePane tilePane;
    StackArray stackArray;

    @Override
    public void switchScene(String resource) {

    }

    @Override
    public void initialize() {
        int size = 45;
        stackArray = new StackArray(size);
        Node node;
        for (int i = 0; i < size; i++) {
            node = new Node("");
            stackArray.push(node);
            if (i == 3) {
                visibleList.add(new NodeElement(true, false, node.getData(), Integer.toString(i)));
            } else if (i == size - 1) {
                visibleList.add(new NodeElement(false, true, node.getData(), Integer.toString(i)));
            } else if (i == 5) {
                visibleList.add(new NodeElement(true, true, node.getData(), Integer.toString(i)));
            } else {
                visibleList.add(new NodeElement(false, false, node.getData(), Integer.toString(i)));
            }
        }
        tilePane.getChildren().addAll(visibleList);
    }
}
