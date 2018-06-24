package Models.Queues;

import Models.Node;

public class Queue {

    private Node[] array;

    private int length;

    private Node head, tail;

    public int getLength() {
        return array.length;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }


    /**
     * Queue's default constructor
     */
    public Queue() {}

    /**
     * Crete a new empty QueueArray by providing the length of the queue.
     *
     * @param length
     */
    public Queue(int length) {
        array = new Node[length];
    }

    /**
     * Create a new QueueArray by providing an array of Node objects.
     *
     * @param array
     */
    public Queue(Node[] array) {
        this.array = array;
    }


    public Node[] getArray() {
        return array;
    }

    public void setArray(Node[] array) {
        this.array = array;
    }

    /**
     * Method that adds another node at the end of the queue.
     *
     * @param node The element that is going to be added to the end of the queue.
     */
    public void enque(Node node) {
        // Catch the first element that gets inserted.
        if (head == null) {
            array[0] = node;
            head = node;
            tail = node;
        } else {
            int index = java.util.Arrays.asList(array).indexOf(tail);
            array[++index] = node;
            tail = node;
        }
    }

    /**
     * Method that removes the first node from the start of the queue.
     */
    public Node deque() {
        int headIndex = java.util.Arrays.asList(array).indexOf(head);
        int tailIndex = java.util.Arrays.asList(array).indexOf(tail);
        if (array[headIndex] == array[tailIndex]) {

        } else {
            array[headIndex] = null;
            head = array[++headIndex];
        }

        return array[0];
    }
}
