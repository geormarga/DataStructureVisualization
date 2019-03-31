package Controllers.Queues;

import CustomElements.ModalStageController;
import CustomElements.QueueNodeElement;
import Helpers.Utils;
import Interfaces.ISelection;
import Models.Exceptions.QueueOverflowException;
import Models.Exceptions.QueueUnderflowException;
import Models.Exceptions.VirtualOverflowException;
import Models.Node;
import Models.Queues.QueueArray;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.List;

public class QueueArrayController implements ISelection {

    @FXML
    TilePane tilePane;
    @FXML
    Button enqueueButton, dequeueButton, createButton, clearButton, backButton;
    @FXML
    TextField nodeInputTextfield, lengthTextfield;
    @FXML
    Label sizeErrorLabel, nodeErrorLabel, sizeLabel, nodeLabel, infoLabel, valueLabel;
    @FXML
    VBox actionGroup;
    @FXML
    HBox infoGroup;
    @FXML
    private ObservableList<QueueNodeElement> visibleList = FXCollections.observableArrayList();

    private QueueArray queueArray;

    public QueueArrayController() {
    }

    public QueueArray getNewQueueArray(int size) {
        return new QueueArray(size);
    }

    @Override
    public void initialize() {
        clearButton.setVisible(false);
        actionGroup.setVisible(false);
        infoGroup.setVisible(false);
        localize();
        setEventListeners();
    }

    @Override
    public void localize() {
        enqueueButton.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_ENQUEUE"), Utils.localeProperty()));
        dequeueButton.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_DEQUEUE"), Utils.localeProperty()));
        clearButton.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_CLEAR"), Utils.localeProperty()));
        createButton.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_CREATE"), Utils.localeProperty()));
        sizeErrorLabel.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_QUEUE_SIZE_VALIDATION"), Utils.localeProperty()));
        nodeErrorLabel.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_QUEUE_NODE_DATA_VALIDATION"), Utils.localeProperty()));
        sizeLabel.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_QUEUE_SIZE"), Utils.localeProperty()));
        nodeLabel.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_QUEUE_NODE"), Utils.localeProperty()));
    }

    @Override
    public void setEventListeners() {
        enqueueButton.setOnAction(e -> clickOnEnqueueButton());

        dequeueButton.setOnAction(e -> clickOnDequeueButton());

        clearButton.setOnAction(e -> clickOnClearButton());

        createButton.setOnAction(e -> clickOnCreateButton());

        backButton.setOnAction(e -> switchScene("/Views/Navigation/SelectQueueView.fxml"));

        lengthTextfield.textProperty().addListener((observable, oldValue, newValue) -> {
            lengthTextfield.setText(checkForTextfieldLimit(oldValue, newValue, 2));
            clearValidationMessages();
        });

        nodeInputTextfield.textProperty().addListener((observable, oldValue, newValue) -> {
            nodeInputTextfield.setText(checkForTextfieldLimit(oldValue, newValue, 9));
            clearValidationMessages();
        });
    }

    @Override
    public void switchScene(String resource) {
        Parent uiView = (Parent) backButton.getScene().lookup("#viewContainer");
        FXMLLoader loader = new FXMLLoader();
        VBox parent = (VBox) uiView.getParent();
        try {
            loader.setLocation(getClass().getResource(resource));
            parent.getChildren().remove(uiView);
            parent.getChildren().add(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private void clickOnCreateButton() {
        clearValidationMessages();
        infoGroup.setVisible(false);
        int size;
        String input = lengthTextfield.getText();
        //Checks if the textfield's value is a positive integer.
        if (input.matches("[1-9]\\d*")) {
            size = Integer.parseInt(input);
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
        infoGroup.setVisible(false);
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
            String input = nodeInputTextfield.getText();
            if (!input.isEmpty()) {
                queueArray.enqueue(new Node(input));
                updateNodeElements();
                setEnqueuedValue(input);
            } else {
                setValidationText(nodeErrorLabel);
            }
            nodeInputTextfield.clear();
        } catch (QueueOverflowException ex) {
            new ModalStageController((Stage) tilePane.getScene().getWindow(), ex.getMessage());
        } catch (VirtualOverflowException ex) {
            new ModalStageController((Stage) tilePane.getScene().getWindow(), ex.getMessage());
        }
    }

    private void clickOnDequeueButton() {
        Node dequeuedValue;
        try {
            clearValidationMessages();
            dequeuedValue = queueArray.dequeue();
            updateNodeElements();
            setDequeuedValue(dequeuedValue.getData());
        } catch (QueueUnderflowException ex) {
            new ModalStageController((Stage) tilePane.getScene().getWindow(), ex.getMessage());
        }
    }

    /**
     * Method that creates a new queue array and based on that returns a node-element list.
     *
     * @param size The queue's size
     * @return The Nodelements created in correspondence with queue array's  status.
     */
    private ObservableList<QueueNodeElement> createNodes(int size) {
        queueArray = getNewQueueArray(size);
        ObservableList<QueueNodeElement> returnList = FXCollections.observableArrayList();
        for (int i = 0; i < size; i++) {
            returnList.add(new QueueNodeElement("", Integer.toString(i)));
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

        for (int i = 0; i < size; i++) {
            displayNode = visibleList.get(i);
            node = nodeList.get(i);
            displayNode.setNodeData(node == null ? "" : node.getData());
            visibleList.get(i).setTracker(i == queueArray.getTail(), i == queueArray.getHead());
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

    private String checkForTextfieldLimit(String oldValue, String newValue, int limit) {
        return (newValue.length()) <= limit ? newValue : oldValue;
    }

    private void setEnqueuedValue(String enqueuedValue){
        infoGroup.setVisible(true);
        infoLabel.setText(Utils.i18n("TRANSLATE_ENQUEUED_VALUE"));
        valueLabel.setText(enqueuedValue);
    }

    private void setDequeuedValue(String dequeuedValue){
        infoGroup.setVisible(true);
        infoLabel.setText(Utils.i18n("TRANSLATE_DEQUEUED_VALUE"));
        valueLabel.setText(dequeuedValue);
    }
}
