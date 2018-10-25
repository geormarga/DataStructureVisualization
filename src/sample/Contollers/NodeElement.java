package sample.Contollers;

import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class NodeElement extends VBox {

    @FXML
    Label tracker, node, index;
    @FXML
    Pane arrow;

    public NodeElement(@NamedArg("isFirst") boolean isFirst, @NamedArg("isLast") boolean isLast, @NamedArg("nodeText") String nodeText, @NamedArg("indexText") String indexText) {
        super();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../resources/NodeElement.fxml"));
        try {
            Parent root = fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
