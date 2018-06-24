package Models.Stacks;

import Models.EmptyNode;
import Models.Node;

public class StackModel {

    private Node[] array;

    private Integer length;


    public Integer getLength() {
        return array.length;
    }

    /**
     * Method that adds another node at the end of the stack.
     *
     * @param node The element that is going to be added to the end of the stack.
     */
    public void push(Node node) {
        array[array.length + 1] = node;
    }

    /**
     * Method that removes the last node from the end of the stack.
     */
    public void pop() {
        array[0] = new EmptyNode();
    }
}
