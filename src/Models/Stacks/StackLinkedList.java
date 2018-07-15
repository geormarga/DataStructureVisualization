package Models.Stacks;

import Models.Node;

public class StackLinkedList implements Stack<Node>{

    Node bottom, top;

    @Override
    public void push(Node node) {
        top.setNext(node);
    }

    @Override
    public Node pop() {
        Node returnValue = top;
        if (top == null){
            //underflow exception
        }
        top = null;
        return returnValue;
    }
}
