package Models.Queues;

import Models.Exceptions.QueueUnderflowException;
import Models.Node;

public class QueueLinkedList implements Queue<Node> {


    private Node head, tail;

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    @Override
    public void enqueue(Node node) {
        if (head == null) {
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
    }

    @Override
    public Node dequeue() {
        if (head == null) {
            throw new QueueUnderflowException();
        }
        head = head.getNext();
        if(head == null){
            tail = null;
        }
        return head;
    }
}
