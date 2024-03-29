package Controllers.Queues;

import CustomElements.ModalStageController;
import CustomElements.QueueNodeElement;
import CustomElements.StackNodeElement;
import Helpers.Utils;
import Interfaces.ISelection;
import Models.Exceptions.QueueOverflowException;
import Models.Exceptions.QueueUnderflowException;
import Models.Node;
import Models.Queues.QueueLinkedList;
import Models.Stacks.StackLinkedList;
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

public class QueueLinkedListController implements ISelection {

    @FXML
    private ObservableList<QueueNodeElement> visibleList = FXCollections.observableArrayList();
    @FXML
    TilePane tilePane;
    @FXML
    Button enqueueButton, dequeueButton, clearButton, backButton;
    @FXML
    TextField nodeInputTextfield;
    @FXML
    Label nodeErrorLabel, nodeLabel, infoLabel, valueLabel;
    @FXML
    HBox infoGroup;
    private QueueLinkedList queueLinkedList = new QueueLinkedList();

    @Override
    public void initialize() {
        infoGroup.setVisible(false);
        localize();
        setEventListeners();
    }

    @Override
    public void localize() {
        enqueueButton.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_ENQUEUE"), Utils.localeProperty()));
        dequeueButton.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_DEQUEUE"), Utils.localeProperty()));
        clearButton.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_CLEAR"), Utils.localeProperty()));
        nodeErrorLabel.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_QUEUE_NODE_DATA_VALIDATION"), Utils.localeProperty()));
        nodeLabel.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_QUEUE_NODE"), Utils.localeProperty()));
    }

    @Override
    public void setEventListeners() {
        backButton.setOnAction(e -> switchScene("/Views/Navigation/SelectQueueView.fxml"));

        enqueueButton.setOnAction(e -> clickOnEnqueueButton());

        dequeueButton.setOnAction(e -> clickOnDequeueButton());

        clearButton.setOnAction(e -> clickOnClearButton());

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

    private void clickOnEnqueueButton() {
        try {
            if (queueLinkedList == null) {
                queueLinkedList = new QueueLinkedList();
                visibleList = FXCollections.observableArrayList();
                tilePane.getChildren().addAll(visibleList);
            }
            clearValidationMessages();
            String input = nodeInputTextfield.getText();
            if (!input.isEmpty()) {
                queueLinkedList.enqueue(new Node(input));
                visibleList.add(new QueueNodeElement(input, ""));
                tilePane.getChildren().clear();
                tilePane.getChildren().addAll(visibleList);
                setEnqueuedValue(input);
            } else {
                setValidationText(nodeErrorLabel);
            }
            nodeInputTextfield.clear();
        } catch (QueueOverflowException ex) {
            new ModalStageController((Stage) tilePane.getScene().getWindow(), ex.getMessage());
        }
    }

    private void clickOnDequeueButton() {
        Node dequeuedValue;
        try {
            clearValidationMessages();
            dequeuedValue = queueLinkedList.dequeue();
            visibleList.remove(visibleList.get(0));
            tilePane.getChildren().clear();
            tilePane.getChildren().addAll(visibleList);
            setDequeuedValue(dequeuedValue.getData());
        } catch (QueueUnderflowException ex) {
            new ModalStageController((Stage) tilePane.getScene().getWindow(), ex.getMessage());
        }
    }

    /**
     * Method that creates a new Queue with the exact same size the previous one had.
     * <p>
     * Which is actually remove values from nodes
     * If there was no previous node there is nothing to clear.
     */
    private void clickOnClearButton() {
        clearValidationMessages();
        infoGroup.setVisible(false);
        queueLinkedList = new QueueLinkedList();
        visibleList.clear();
        updateNodes(visibleList);
    }

    private void updateNodes(ObservableList<QueueNodeElement> nodes) {
        tilePane.getChildren().clear();
        tilePane.getChildren().addAll(nodes);
    }
    /**
     * Method that removes any existing validation message in the view, by resetting the style classes.
     */
    private void clearValidationMessages() {
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

    private void setEnqueuedValue(String enqueuedValue) {
        infoGroup.setVisible(true);
        infoLabel.setText(Utils.i18n("TRANSLATE_ENQUEUED_VALUE"));
        valueLabel.setText(enqueuedValue);
    }

    private void setDequeuedValue(String dequeuedValue) {
        infoGroup.setVisible(true);
        infoLabel.setText(Utils.i18n("TRANSLATE_DEQUEUED_VALUE"));
        valueLabel.setText(dequeuedValue);
    }
}