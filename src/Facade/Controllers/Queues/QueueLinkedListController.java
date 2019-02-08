package Controllers.Queues;

import CustomElements.ModalStageController;
import CustomElements.QueueNodeElement;
import Facade.Interfaces.ISelection;
import Models.Exceptions.QueueOverflowException;
import Models.Exceptions.QueueUnderflowException;
import Models.Node;
import Models.Queues.QueueLinkedList;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    Label nodeErrorLabel, nodeLabel;
    private QueueLinkedList queueLinkedList;

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

    @Override
    public void initialize() {
        enqueueButton.setOnAction(e -> clickOnEnqueueButton());
        dequeueButton.setOnAction(e -> clickOnDequeueButton());
        clearButton.setOnAction(e -> clickOnClearButton());

        backButton.setOnAction(e -> switchScene("/Views/Navigation/SelectQueueView.fxml"));

        enqueueButton.textProperty().bind(Bindings.createStringBinding(() -> Facade.Utils.i18n("TRANSLATE_ENQUEUE"), Facade.Utils.localeProperty()));
        dequeueButton.textProperty().bind(Bindings.createStringBinding(() -> Facade.Utils.i18n("TRANSLATE_DEQUEUE"), Facade.Utils.localeProperty()));
        clearButton.textProperty().bind(Bindings.createStringBinding(() -> Facade.Utils.i18n("TRANSLATE_CLEAR"), Facade.Utils.localeProperty()));
        nodeErrorLabel.textProperty().bind(Bindings.createStringBinding(() -> Facade.Utils.i18n("TRANSLATE_QUEUE_NODE_DATA_VALIDATION"), Facade.Utils.localeProperty()));
        nodeLabel.textProperty().bind(Bindings.createStringBinding(() -> Facade.Utils.i18n("TRANSLATE_QUEUE_NODE"), Facade.Utils.localeProperty()));
    }

    private void clickOnEnqueueButton() {
        try {
            if (queueLinkedList == null) {
                queueLinkedList = new QueueLinkedList();
                visibleList = FXCollections.observableArrayList();
                tilePane.getChildren().addAll(visibleList);
            }
            clearValidationMessages();
            String text = nodeInputTextfield.getText();
            boolean textIsEmpty = text.equals("");
            if (!textIsEmpty) {
                queueLinkedList.enqueue(new Node(text));
                visibleList.add(new QueueNodeElement(text, ""));
                tilePane.getChildren().clear();
                tilePane.getChildren().addAll(visibleList);
            } else {
                setValidationText(nodeErrorLabel);
            }
            nodeInputTextfield.clear();
        } catch (QueueOverflowException ex) {
            new ModalStageController((Stage) tilePane.getScene().getWindow(), ex.getMessage());
        }
    }

    private void clickOnDequeueButton() {
        try {
            clearValidationMessages();
            queueLinkedList.dequeue();
            visibleList.remove(visibleList.get(0));
            tilePane.getChildren().clear();
            tilePane.getChildren().addAll(visibleList);
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
        queueLinkedList = null;
        visibleList.removeAll();
        tilePane.getChildren().clear();
        tilePane.getChildren().addAll(visibleList);
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
}