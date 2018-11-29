package Facade.Controllers;

import Facade.CustomElements.StackNodeElement;
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

public class StackLinkedListController implements ISelection {

    @FXML
    private ObservableList<StackNodeElement> visibleList = FXCollections.observableArrayList();
    @FXML
    TilePane tilePane;
    @FXML
    Button pushButton, popButton, createButton, clearButton;
    @FXML
    TextField nodeInputTextfield, lengthTextfield;
    @FXML
    Label sizeErrorLabel, nodeErrorLabel;
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
    }

    private void clickOnPushButton() {
        clearValidationMessages();
        String text = nodeInputTextfield.getText();
        boolean textIsEmpty = text.equals("");
        if (!textIsEmpty) {
            addNodeToList(visibleList, text);
            updateNodeElements();
        } else {
            setValidationText(nodeErrorLabel);
        }
        nodeInputTextfield.clear();
    }

    private void clickOnPopButton() {
        clearValidationMessages();
        removeNode();
        updateNodeElements();
    }

    private void clickOnClearButton() {
        clearValidationMessages();
        visibleList = clearNodes();
        updateNodeElements();
    }

    private void clickOnCreateButton() {
        clearValidationMessages();
        int size;
        //Checks if the textfield's value is a positive integer.
        if (lengthTextfield.getText().matches("\\d+")) {
            size = Integer.parseInt(lengthTextfield.getText());
            tilePane.getChildren().clear();
            visibleList = createNodes(size);
            tilePane.getChildren().addAll(visibleList);
            updateNodeElements();
        } else {
            setValidationText(sizeErrorLabel);
        }
    }


    //Which is actually add value to node
    private void addNodeToList(ObservableList<StackNodeElement> nodeElements, String text) {
        try {
            stackArray.push(new Node(text));
            for (int i = 0; i < stackArray.getSize(); i++) {
                if (i == stackArray.getTop() + 1) {
                    nodeElements.get(i).setNodeData(text);
                }
//                nodeElements.add(new QueueNodeElement(text, Integer.toString(nodeElements.size())));
            }


//            tilePane.getChildren().clear();
//            tilePane.getChildren().addAll(nodeElements);
            updateNodeElements();
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }
    }

    //Which is actually remove value from node
    private Node removeNode() {
        Node node = stackArray.pop();
        return node;
    }

    /**
     * Method that creates a new stack with the exact same size the previous one had.
     * <p>
     * Which is actually remove values from nodes
     * If there was no previous node there is nothing to clear.
     */
    public ObservableList<StackNodeElement> clearNodes() {
        int size = stackArray.getSize();
        return createNodes(size);
    }

    /**
     * Method that creates a new stack array and based on that returns a node-element list.
     *
     * @param size The stack's size
     * @return The Nodelements created in correspondence with stack array's  status.
     */
    public ObservableList<StackNodeElement> createNodes(int size) {
        stackArray = new StackArray(size);
        ObservableList<StackNodeElement> returnList = FXCollections.observableArrayList();
        for (int i = 0; i < size; i++) {
            returnList.add(new StackNodeElement("", Integer.toString(i)));
        }
        return returnList;
    }

    /**
     * Method that updates the list of node-elements displayed according to the latest status of the stack Array.
     */
    public void updateNodeElements() {
        int size = stackArray.getSize();
        for (int i = 0; i < size; i++) {
            visibleList.get(i).setTracker(i == stackArray.getTop(), i == stackArray.getBottom());
        }
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