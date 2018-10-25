package Models.Stacks;

import Models.Exceptions.StackUnderflowException;
import Models.Interfaces.IVisible;
import Models.Node;

import java.util.List;

public class StackLinkedList implements Stack<Node>, IVisible<Node> {

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
            throw new StackUnderflowException();
        } else {
            top = top.getNext();
        }
        return top;
    }

//    private void linkedListAsList(List<Node> list,Node top){
//        new
//    }
    @Override
    public List<Node> displayAllAsList() {
        return null;
    }
}
