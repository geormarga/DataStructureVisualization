

package Controllers.Queues;

import Controllers.CustomElements.QueueNodeElement;
import Facade.Interfaces.ISelection;
import Models.Node;
import Models.Queues.QueueArray;
import Models.Queues.QueueArrayShiftingVirtualOverflow;
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

public class QueueArrayShiftingController implements ISelection {

    @FXML
    TilePane tilePane;
    @FXML
    Button enqueueButton, dequeueButton, createButton, clearButton;
    @FXML
    TextField nodeInputTextfield, lengthTextfield;
    @FXML
    Label sizeErrorLabel, nodeErrorLabel;
    @FXML
    VBox actionGroup;
    @FXML
    private ObservableList<QueueNodeElement> visibleList = FXCollections.observableArrayList();

    private QueueArrayShiftingVirtualOverflow queueArray;

    @Override
    public void switchScene(String resource) {

    }

    @Override
    public void initialize() {
        enqueueButton.setOnAction(e -> clickOnEnqueueButton());
        dequeueButton.setOnAction(e -> clickOnDequeueButton());
        clearButton.setOnAction(e -> clickOnClearButton());
        createButton.setOnAction(e -> clickOnCreateButton());
        clearButton.setVisible(false);
        actionGroup.setVisible(false);
        lengthTextfield.textProperty().addListener((observable, oldValue, newValue) -> lengthTextfield.setText(checkForTextfieldLimit(oldValue, newValue, 2)));
        nodeInputTextfield.textProperty().addListener((observable, oldValue, newValue) -> nodeInputTextfield.setText(checkForTextfieldLimit(oldValue, newValue, 9)));
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
     * Method that creates a new queue with the exact same size the previous one had.
     * <p>
     * Which is actually remove values from nodes
     * If there was no previous node there is nothing to clear.
     */
    private void clickOnClearButton() {
        clearValidationMessages();
        int size;
        //Checks if there is an existing queue.
        if (queueArray != null) {
            size = queueArray.getSize();
            visibleList = createNodes(size);
            updateNodeElements();
        }
    }

    private void clickOnEnqueueButton() {
        try {
            clearValidationMessages();
            String text = nodeInputTextfield.getText();
            boolean textIsEmpty = text.equals("");
            if (!textIsEmpty) {
                queueArray.enqueue(new Node(text));
                updateNodeElements();
            } else {
                setValidationText(nodeErrorLabel);
            }
            nodeInputTextfield.clear();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void clickOnDequeueButton() {
        try {
            clearValidationMessages();
            queueArray.dequeue();
            updateNodeElements();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Method that creates a new queue array and based on that returns a node-element list.
     *
     * @param size The queue's size
     * @return The Nodelements created in correspondence with queue array's  status.
     */
    private ObservableList<QueueNodeElement> createNodes(int size) {
        queueArray = new QueueArrayShiftingVirtualOverflow(size);
        ObservableList<QueueNodeElement> returnList = FXCollections.observableArrayList();
        for (int i = 0; i < size; i++) {
            returnList.add(new QueueNodeElement("", Integer.toString(i)));
        }
        return returnList;
    }

    private ObservableList<QueueNodeElement> createNodes(QueueArray queueArray) {
        List<Node> nodeList = queueArray.displayAllAsList();
        int size = nodeList.size();
        Node node;

        ObservableList<QueueNodeElement> returnList = FXCollections.observableArrayList();
        for (int i = 0; i < size; i++) {
            node = nodeList.get(i);
            returnList.add(new QueueNodeElement(node == null ? "" : node.getData(), Integer.toString(i)));
        }
        return returnList;
    }

    /**
     * Method that updates the list of node-elements displayed according to the latest status of the queue Array.
     */
    private void updateNodeElements() {
        List<Node> nodeList = queueArray.displayAllAsList();
        int size = nodeList.size();
        QueueNodeElement displayNode;
        Node node;

        visibleList.removeAll();
        visibleList = createNodes(queueArray);
//        for (int i = 0; i < size; i++) {
//            displayNode = visibleList.get(i);
//            node = nodeList.get(i);
//            displayNode.setNodeData(node == null ? "" : node.getData());
//            visibleList.get(i).setTracker(i == queueArray.getTail(), i == queueArray.getHead());
//        }
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

    private String checkForTextfieldLimit(String oldValue, String newValue, int limit) {
        return (newValue.length()) <= limit ? newValue : oldValue;
    }

}