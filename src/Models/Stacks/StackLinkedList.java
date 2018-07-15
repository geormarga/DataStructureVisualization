package Models.Stacks;

import Models.Node;

public class StackLinkedList implements Stack<Node> {

    Node top;

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
            //underflow exception
        }else{
            top = top.getNext();
        }
        return top;
    }
}
