package Models.Queues;

import Models.Exceptions.VirtualOverflowException;
import Models.Interfaces.IVirtualOverflow;
import Models.Node;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class QueueArrayCircularVirtualOverflow implements Queue<Node>, IVirtualOverflow {


    private Node[] array;

    private int size, head, tail;

    public int getLength() {
        return array.length;
    }

    public Node getHead() {
        return array[head];
    }

    public Node getTail() {
        return array[tail];
    }

    /**
     * Crete a new empty QueueArray by providing the length of the queue.
     *
     * @param size
     */
    public QueueArrayCircularVirtualOverflow(int size) {
        this.size = size;
        array = new Node[size];
    }

    /**
     * Create a new QueueArray by providing an array of Node objects.
     *
     * @param array
     */
    public QueueArrayCircularVirtualOverflow(Node[] array) {
        this.array = array;
    }

    public Node[] getArray() {
        return array;
    }

    public void setArray(Node[] array) {
        this.array = array;
    }

    boolean containsAny(Node[] array, Object obj) {
        boolean result = false;
        for (int i = 0; i < array.length; i++) {
            result = result | (array[i] == obj);
        }
        return result;
    }

    @Override
    public void enqueue(Node node) {
        try {
            // The first block will only executed the first time around.
            if (array[head] == null) {
                // Adds the node to the array.
                // Sets the head and tail to the last inserted node.
                array[head] = node;
                array[tail] = node;
                //if the any of the array elements are null and the tail is at the last element
            } else if (containsAny(array, null) && array[tail] == array[size - 1]) {
                throw new VirtualOverflowException();
            } else {
                array[++tail] = node;
            }
        } catch (VirtualOverflowException vEx) {
            handle(array);
        }

    }

    @Override
    public Node dequeue() {
        Node result = array[head];
        if (array[head] == array[tail]) {

        } else {
            array[head] = null;
            head++;
        }

        return result;
    }

    @Override
    public void handle(Node[] array) {

    }
}
