package sample.Contollers;


import Models.Node;
import Models.Stacks.StackArray;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.util.List;

public class StackArrayController implements ISelection {

    @FXML
    VBox buttonContainer,parent1;
    @FXML
    ObservableList<NodeElement> visibleList = FXCollections.observableArrayList();
    @FXML
    TilePane tilePane;
    StackArray stackArray;

    @Override
    public void switchScene(String resource) {

    }

    @Override
    public void initialize() {
        stackArray = new StackArray(7);
        stackArray.push(new Node("takis"));
        stackArray.push(new Node("takis"));
        stackArray.push(new Node("takis"));
        stackArray.push(new Node("takis"));
        stackArray.push(new Node("takis"));
        stackArray.push(new Node("takis"));
        stackArray.push(new Node("takis"));
        List<Node> nodes = stackArray.displayAllAsList();
        nodes.forEach(x -> visibleList.add(new NodeElement(true,true,x.getData(),"1")));
        tilePane.getChildren().addAll(visibleList);
        System.out.println("Heyyy");
    }
}
