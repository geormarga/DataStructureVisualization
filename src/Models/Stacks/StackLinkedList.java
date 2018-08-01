package Models.Stacks;

import Models.Node;

public class StackLinkedList implements Stack<Node> {

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
        if (top == null) {
            //throw new StackUnderflowException()
        } else {
            top = top.getNext();
        }
        return top;
    }
}
