package Facade.Controllers;

import Facade.CustomElements.NodeElement;
import Facade.Interfaces.ISelection;
import Models.Node;
import Models.Stacks.StackArray;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.util.List;


public class StackArrayController implements ISelection {

    @FXML
    TilePane tilePane;
    @FXML
    Button pushButton, popButton, createButton, clearButton;
    @FXML
    TextField nodeInputTextfield, lengthTextfield;
    @FXML
    Label sizeErrorLabel, nodeErrorLabel;
    @FXML
    VBox actionGroup;
    @FXML
    private ObservableList<NodeElement> visibleList = FXCollections.observableArrayList();

    private StackArray stackArray;

    @Override
    public void switchScene(String resource) {

    }

    @Override
    public void initialize() {
        pushButton.setOnAction(e -> clickOnPushButton());
        popButton.setOnAction(e -> clickOnPopButton());
        clearButton.setOnAction(e -> clickOnClearButton());
        createButton.setOnAction(e -> clickOnCreateButton());
        clearButton.setVisible(false);
        actionGroup.setVisible(false);
    }


    private void clickOnCreateButton() {
        clearValidationMessages();
        int size;
        //Checks if the textfield's value is a positive integer.
        if (lengthTextfield.getText().matches("\\d+")) {
            size = Integer.parseInt(lengthTextfield.getText());
            visibleList = createNodes(size);
            updateNodeElements();
            clearButton.setVisible(true);
            actionGroup.setVisible(true);
        } else {
            setValidationText(sizeErrorLabel);
        }
    }

    /**
     * Method that creates a new stack with the exact same size the previous one had.
     * <p>
     * Which is actually remove values from nodes
     * If there was no previous node there is nothing to clear.
     */
    private void clickOnClearButton() {
        clearValidationMessages();
        int size;
        //Checks if there is an existing stack.
        if (stackArray != null) {
            size = stackArray.getSize();
            visibleList = createNodes(size);
            updateNodeElements();
        }
    }

    private void clickOnPushButton() {
        try {
            clearValidationMessages();
            String text = nodeInputTextfield.getText();
            boolean textIsEmpty = text.equals("");
            if (!textIsEmpty) {
                stackArray.push(new Node(text));
                updateNodeElements();
            } else {
                setValidationText(nodeErrorLabel);
            }
            nodeInputTextfield.clear();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void clickOnPopButton() {
        try {
            clearValidationMessages();
            stackArray.pop();
            updateNodeElements();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Method that creates a new stack array and based on that returns a node-element list.
     *
     * @param size The stack's size
     * @return The Nodelements created in correspondence with stack array's  status.
     */
    private ObservableList<NodeElement> createNodes(int size) {
        stackArray = new StackArray(size);
        ObservableList<NodeElement> returnList = FXCollections.observableArrayList();
        for (int i = 0; i < size; i++) {
            returnList.add(new NodeElement("", Integer.toString(i)));
        }
        return returnList;
    }

    /**
     * Method that updates the list of node-elements displayed according to the latest status of the stack Array.
     */
    private void updateNodeElements() {
        List<Node> nodeList = stackArray.displayAllAsList();
        int size = nodeList.size();
        NodeElement displayNode;
        Node node;

        for (int i = 0; i < size; i++) {
            displayNode = visibleList.get(i);
            displayNode.setNodeData("");
            visibleList.get(i).setTracker(false, false);
        }

        for (int i = 0; i < size; i++) {
            displayNode = visibleList.get(i);
            node = nodeList.get(i);
            displayNode.setNodeData(node == null ? "" : node.getData());
            visibleList.get(i).setTracker(i == stackArray.getTop(), i == stackArray.getBottom());
        }
        tilePane.getChildren().clear();
        tilePane.getChildren().addAll(visibleList);
    }

    /**
     * Method that removes any existing validation message in the view, by resetting the style classes.
     */
    private void clearValidationMessages() {
        clearValidationText(sizeErrorLabel);
        clearValidationText(nodeErrorLabel);
    }

    private void setValidationText(Parent parent) {
        parent.getStyleClass().clear();
        parent.getStyleClass().add("label-error");
    }

    private void clearValidationText(Parent parent) {
        parent.getStyleClass().clear();
        parent.getStyleClass().add("label-no-error");
    }
}