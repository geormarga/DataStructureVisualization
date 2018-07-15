package Models.Stacks;

import Models.EmptyNode;
import Models.Exceptions.QueueOverflowException;
import Models.Exceptions.StackOverflowException;
import Models.Node;

public class StackArray implements Stack<Node> {

    private Node[] array;

    private int size, top, bottom;

    public int getLength() {
        return array.length;
    }

    public Node getBottom() {
        return array[bottom];
    }

    public Node getTop() {
        return array[top];
    }

    @Override
    public void push(Node node) {

        try {
            if () {
                throw new StackOverflowException();
            } else {
                array[array.length + 1] = node;
            }
        } catch (StackOverflowException oEx) {

        }
    }

    @Override
    public Node pop() {
        Node returnValue = array[0];
        array[0] = null;
        return returnValue;
    }
}
