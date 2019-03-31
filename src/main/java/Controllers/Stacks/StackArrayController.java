package Controllers.Stacks;

import CustomElements.ModalStageController;
import CustomElements.StackNodeElement;
import Helpers.Utils;
import Interfaces.ISelection;
import Models.Exceptions.StackOverflowException;
import Models.Exceptions.StackUnderflowException;
import Models.Node;
import Models.Stacks.StackArray;
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
import java.util.Collections;
import java.util.List;


public class StackArrayController implements ISelection {

    @FXML
    TilePane tilePane;
    @FXML
    Button pushButton, popButton, createButton, clearButton, backButton;
    @FXML
    TextField nodeInputTextfield, lengthTextfield;
    @FXML
    Label sizeErrorLabel, nodeErrorLabel, sizeLabel, nodeLabel, infoLabel, valueLabel;
    @FXML
    VBox actionGroup;
    @FXML
    HBox infoGroup;
    @FXML
    private ObservableList<StackNodeElement> visibleList = FXCollections.observableArrayList();

    private StackArray stackArray;

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
        clearButton.setVisible(false);
        actionGroup.setVisible(false);
        infoGroup.setVisible(false);
        localize();
        setEventListeners();
    }

    @Override
    public void localize() {
        pushButton.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_PUSH"), Utils.localeProperty()));
        popButton.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_POP"), Utils.localeProperty()));
        clearButton.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_CLEAR"), Utils.localeProperty()));
        createButton.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_CREATE"), Utils.localeProperty()));
        sizeErrorLabel.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_STACK_SIZE_VALIDATION"), Utils.localeProperty()));
        nodeErrorLabel.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_STACK_NODE_DATA_VALIDATION"), Utils.localeProperty()));
        sizeLabel.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_STACK_SIZE"), Utils.localeProperty()));
        nodeLabel.textProperty().bind(Bindings.createStringBinding(() -> Utils.i18n("TRANSLATE_STACK_NODE"), Utils.localeProperty()));
    }

    @Override
    public void setEventListeners() {
        pushButton.setOnAction(e -> clickOnPushButton());

        popButton.setOnAction(e -> clickOnPopButton());

        clearButton.setOnAction(e -> clickOnClearButton());

        createButton.setOnAction(e -> clickOnCreateButton());

        backButton.setOnAction(e -> switchScene("/Views/Navigation/SelectStackView.fxml"));

        lengthTextfield.textProperty().addListener((observable, oldValue, newValue) -> {
            lengthTextfield.setText(checkForTextfieldLimit(oldValue, newValue, 2));
            clearValidationMessages();
        });

        nodeInputTextfield.textProperty().addListener((observable, oldValue, newValue) -> {
            nodeInputTextfield.setText(checkForTextfieldLimit(oldValue, newValue, 9));
            clearValidationMessages();
        });
    }

    private void clickOnCreateButton() {
        clearValidationMessages();
        int size;
        String input = lengthTextfield.getText();
        //Checks if the textfield's value is a positive integer.
        if (input.matches("[1-9]\\d*")) {
            size = Integer.parseInt(input);
            visibleList = createNodes(size);
            Collections.reverse(visibleList);
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
            Collections.reverse(visibleList);
            updateNodeElements();
        }
    }

    private void clickOnPushButton() {
        try {
            clearValidationMessages();
            String input = nodeInputTextfield.getText();
            if (!input.isEmpty()) {
                stackArray.push(new Node(input));
                updateNodeElements();
                setPushedValue(input);
            } else {
                setValidationText(nodeErrorLabel);
            }
            nodeInputTextfield.clear();
        } catch (StackOverflowException ex) {
            new ModalStageController((Stage) tilePane.getScene().getWindow(), ex.getMessage());
        }
    }

    private void clickOnPopButton() {
        Node poppedValue;
        try {
            clearValidationMessages();
            poppedValue = stackArray.pop();
            updateNodeElements();
            setPoppedValue(poppedValue.getData());
        } catch (StackUnderflowException ex) {
            new ModalStageController((Stage) tilePane.getScene().getWindow(), ex.getMessage());
        }
    }

    /**
     * Method that creates a new stack array and based on that returns a node-element list.
     *
     * @param size The stack's size
     * @return The Nodelements created in correspondence with stack array's  status.
     */
    private ObservableList<StackNodeElement> createNodes(int size) {
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
    private void updateNodeElements() {
        Collections.reverse(visibleList);
        List<Node> nodeList = stackArray.displayAllAsList();
        int size = nodeList.size();
        StackNodeElement displayNode;
        Node node;

        for (int i = 0; i < size; i++) {
            displayNode = visibleList.get(i);
            node = nodeList.get(i);
            displayNode.setNodeData(node == null ? "" : node.getData());
            visibleList.get(i).setTracker(i == stackArray.getTop());
        }
        Collections.reverse(visibleList);
        updateNodes(visibleList);
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

    private void updateNodes(ObservableList<StackNodeElement> nodes) {
        tilePane.getChildren().clear();
        tilePane.getChildren().addAll(nodes);
    }

    private void setPushedValue(String enqueuedValue) {
        infoGroup.setVisible(true);
        infoLabel.setText(Utils.i18n("TRANSLATE_PUSHED_VALUE"));
        valueLabel.setText(enqueuedValue);
    }

    private void setPoppedValue(String dequeuedValue) {
        infoGroup.setVisible(true);
        infoLabel.setText(Utils.i18n("TRANSLATE_POPPED_VALUE"));
        valueLabel.setText(dequeuedValue);
    }
}