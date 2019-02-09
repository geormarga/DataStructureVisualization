package Controllers.Stacks;

import CustomElements.ModalStageController;
import CustomElements.StackNodeElement;
import Facade.Interfaces.ISelection;
import Models.Exceptions.StackOverflowException;
import Models.Exceptions.StackUnderflowException;
import Models.Node;
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

public class StackLinkedListController implements ISelection {

    @FXML
    private ObservableList<StackNodeElement> visibleList = FXCollections.observableArrayList();
    @FXML
    TilePane tilePane;
    @FXML
    Button pushButton, popButton, clearButton, backButton;
    @FXML
    TextField nodeInputTextfield;
    @FXML
    Label nodeErrorLabel, nodeLabel, infoLabel, valueLabel;
    @FXML
    HBox infoGroup;
    private StackLinkedList stackLinkedList = new StackLinkedList();

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
        infoGroup.setVisible(false);
        localize();
        setEventListeners();
    }

    @Override
    public void localize() {
        pushButton.textProperty().bind(Bindings.createStringBinding(() -> Facade.Utils.i18n("TRANSLATE_PUSH"), Facade.Utils.localeProperty()));
        popButton.textProperty().bind(Bindings.createStringBinding(() -> Facade.Utils.i18n("TRANSLATE_POP"), Facade.Utils.localeProperty()));
        clearButton.textProperty().bind(Bindings.createStringBinding(() -> Facade.Utils.i18n("TRANSLATE_CLEAR"), Facade.Utils.localeProperty()));
        nodeErrorLabel.textProperty().bind(Bindings.createStringBinding(() -> Facade.Utils.i18n("TRANSLATE_STACK_NODE_DATA_VALIDATION"), Facade.Utils.localeProperty()));
        nodeLabel.textProperty().bind(Bindings.createStringBinding(() -> Facade.Utils.i18n("TRANSLATE_STACK_NODE"), Facade.Utils.localeProperty()));
    }

    @Override
    public void setEventListeners() {
        pushButton.setOnAction(e -> clickOnPushButton());
        popButton.setOnAction(e -> clickOnPopButton());
        clearButton.setOnAction(e -> clickOnClearButton());
        backButton.setOnAction(e -> switchScene("/Views/Navigation/SelectStackView.fxml"));

        nodeInputTextfield.textProperty().addListener((observable, oldValue, newValue) -> {
            nodeInputTextfield.setText(checkForTextfieldLimit(oldValue, newValue, 9));
            clearValidationMessages();
        });
    }

    private void clickOnPushButton() {
        try {
            String input = nodeInputTextfield.getText();
            if (!input.isEmpty()) {
                clearValidationText(nodeErrorLabel);
                stackLinkedList.push(new Node(input));
                visibleList.add(new StackNodeElement(input, ""));
                updateTop(visibleList);
                setPushedValue(input);
                nodeInputTextfield.clear();
            } else {
                setValidationText(nodeErrorLabel);
            }

        } catch (StackOverflowException ex) {
            new ModalStageController((Stage) tilePane.getScene().getWindow(), ex.getMessage());
        }
    }

    private void clickOnPopButton() {
        Node poppedValue;
        try {
            clearValidationMessages();
            poppedValue = stackLinkedList.pop();
            visibleList.remove(visibleList.size() - 1);
            updateTop(visibleList);
            setPoppedValue(poppedValue.getData());
        } catch (StackUnderflowException ex) {
            new ModalStageController((Stage) tilePane.getScene().getWindow(), ex.getMessage());
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
        stackLinkedList = new StackLinkedList();
        visibleList.clear();
        updateNodes(visibleList);
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

    private void updateNodes(ObservableList<StackNodeElement> nodes) {
        tilePane.getChildren().clear();
        tilePane.getChildren().addAll(nodes);
    }

    private void updateTop(ObservableList<StackNodeElement> nodes) {
        if (nodes.size() > 0) {
            nodes.get(nodes.size() - 1).setTracker(true);
        }
        if (nodes.size() - 1 > 0) {
            nodes.get(nodes.size() - 2).setTracker(false);
        }
        updateNodes(visibleList);
    }

    private void setPushedValue(String enqueuedValue) {
        infoGroup.setVisible(true);
        infoLabel.setText("Pushed value:   ");
        valueLabel.setText(enqueuedValue);
    }

    private void setPoppedValue(String dequeuedValue) {
        infoGroup.setVisible(true);
        infoLabel.setText("Popped value:   ");
        valueLabel.setText(dequeuedValue);
    }
}