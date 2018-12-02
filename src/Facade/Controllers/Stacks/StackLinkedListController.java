package Controllers.Stacks;

import CustomElements.ModalStageController;
import CustomElements.StackNodeElement;
import Facade.Interfaces.ISelection;
import Models.Exceptions.StackOverflowException;
import Models.Exceptions.StackUnderflowException;
import Models.Node;
import Models.Stacks.StackLinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class StackLinkedListController implements ISelection {

    @FXML
    private ObservableList<StackNodeElement> visibleList = FXCollections.observableArrayList();
    @FXML
    TilePane tilePane;
    @FXML
    Button pushButton, popButton, clearButton;
    @FXML
    TextField nodeInputTextfield;
    @FXML
    Label nodeErrorLabel;
    private StackLinkedList stackLinkedList = new StackLinkedList();

    @Override
    public void switchScene(String resource) {

    }

    @Override
    public void initialize() {
        pushButton.setOnAction(e -> clickOnPushButton());
        popButton.setOnAction(e -> clickOnPopButton());
        clearButton.setOnAction(e -> clickOnClearButton());

        nodeInputTextfield.textProperty().addListener((observable, oldValue, newValue) -> nodeInputTextfield.setText(checkForTextfieldLimit(oldValue, newValue, 9)));
        //nodeInputTextfield.textProperty().addListener(e -> clearValidationText(nodeErrorLabel));
    }

    private void clickOnPushButton() {
        try {
            String text = nodeInputTextfield.getText();
            boolean textIsEmpty = text.equals("");
            if (!textIsEmpty) {
                clearValidationText(nodeErrorLabel);
                stackLinkedList.push(new Node(text));
                visibleList.add(new StackNodeElement(text, ""));
                updateTop(visibleList);
                nodeInputTextfield.clear();
            } else {
                setValidationText(nodeErrorLabel);
            }

        } catch (StackOverflowException ex) {
            new ModalStageController((Stage) tilePane.getScene().getWindow(),ex.getMessage());
        }
    }

    private void clickOnPopButton() {
        try {
            clearValidationMessages();
            stackLinkedList.pop();
            visibleList.remove(visibleList.size() - 1);
            updateTop(visibleList);
        } catch (StackUnderflowException ex) {
            new ModalStageController((Stage) tilePane.getScene().getWindow(),ex.getMessage());
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
}