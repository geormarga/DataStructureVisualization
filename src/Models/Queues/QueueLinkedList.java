package Models.Queues;

import Models.Node;

public class QueueLinkedList implements Queue<Node> {

    Node head, current;

    @Override
    public void enqueue(Node node) {
        if (head == null) {
            head = node;
        }
        current.setNext(node);
    }

    @Override
    public Node dequeue() {
        Node returnValue = head;
        if (head == null){
            //underflow exception
        }
        head = head.getNext();
        return returnValue;
    }
}
