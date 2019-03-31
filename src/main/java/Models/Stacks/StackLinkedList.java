package Models.Stacks;

import Models.Exceptions.StackUnderflowException;
import Models.Interfaces.IVisible;
import Models.Node;

import java.util.List;

public class StackLinkedList implements Stack<Node>{

    private Node top;

    Node getTop() {
        return this.top;
    }

    @Override
    public void push(Node node) {
        if (top == null) {
            top = node;
            top.setNext(null);
        } else {
            node.setNext(top);
            top = node;
        }
    }

    @Override
    public Node pop() {
        Node returnValue;
        if (top == null) {
            throw new StackUnderflowException();
        } else {
            returnValue = top;
            top = top.getNext();
        }
        return returnValue;
    }
}
