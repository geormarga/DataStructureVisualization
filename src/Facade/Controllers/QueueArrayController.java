package Facade.Controllers;

import Facade.CustomElements.NodeElement;
import Facade.Interfaces.ISelection;
import Models.Node;
import Models.Queues.QueueArray;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

public class QueueArrayController implements ISelection {

    @FXML
    private ObservableList<NodeElement> visibleList = FXCollections.observableArrayList();
    @FXML
    TilePane tilePane;
    @FXML
    Button pushButton, popButton, createButton, clearButton;
    @FXML
    TextField nodeInputTextfield, lengthTextfield;
    @FXML
    Label sizeErrorLabel, nodeErrorLabel;
    private QueueArray queueArray;

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
    private void addNodeToList(ObservableList<NodeElement> nodeElements, String text) {
        try {
            queueArray.enqueue(new Node(text));
            for (int i = 0; i < queueArray.getSize(); i++) {
                if (i == queueArray.getTop() + 1) {
                    nodeElements.get(i).setNodeData(text);
                }
            }
            updateNodeElements();
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }
    }

    //Which is actually remove value from node
    private Node removeNode() {
        Node node = queueArray.dequeue();
        return node;
    }

    /**
     * Method that creates a new stack with the exact same size the previous one had.
     * <p>
     * Which is actually remove values from nodes
     * If there was no previous node there is nothing to clear.
     */
    public ObservableList<NodeElement> clearNodes() {
        int size = queueArray.getSize();
        return createNodes(size);
    }

    /**
     * Method that creates a new stack array and based on that returns a node-element list.
     *
     * @param size The stack's size
     * @return The Nodelements created in correspondence with stack array's  status.
     */
    public ObservableList<NodeElement> createNodes(int size) {
        queueArray = new QueueArray(size);
        ObservableList<NodeElement> returnList = FXCollections.observableArrayList();
        for (int i = 0; i < size; i++) {
            returnList.add(new NodeElement("", Integer.toString(i)));
        }
        return returnList;
    }

    /**
     * Method that updates the list of node-elements displayed according to the latest status of the stack Array.
     */
    public void updateNodeElements() {
        int size = queueArray.getSize();
        for (int i = 0; i < size; i++) {
            visibleList.get(i).setTracker(i == queueArray.getTop(), i == queueArray.getBottom());
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
    /*
    @FXML
    private ObservableList<Label> list;
    @FXML
    private TilePane tilePane;
    private List<Timeline> jobs;

    @Override
    public void initialize() {
        super.initialize();
        jobs = new ArrayList<>();


        int size = 800*800;
        int quantity = size / 32;
        for (int i = 0; i < quantity; i++) {
            list.add(new Label(Integer.toString(randomWithRange(0, 1))));
        }

        tilePane.getChildren().addAll(list);

        list.forEach(item -> {
            jobs.add(createJob(item));
        });

        jobs.forEach(timeline -> timeline.play());
    }

    int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    Timeline createJob(Label label) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(randomWithRange(2000, 3000)), ae -> label.setText(Integer.toString(randomWithRange(0, 1)))));
        timeline.setCycleCount(Animation.INDEFINITE);
        return timeline;
    }
    */
