package Models.Queues;

import Models.Exceptions.QueueOverflowException;
import Models.Exceptions.QueueUnderflowException;
import Models.Exceptions.VirtualOverflowException;
import Models.Interfaces.IVirtualOverflow;
import Models.Interfaces.IVisible;
import Models.Node;

import java.util.Arrays;
import java.util.List;

public class QueueArray implements Queue<Node>, IVisible<Node>, IVirtualOverflow {

    protected Node[] array;

    protected int size, head, tail;

    public int getLength() {
        return array.length;
    }

    public Node getHeadNode() {
        return array[head];
    }

    public Node getTailNode() {
        return array[tail];
    }

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }

    public int getSize() {
        return size;
    }

    /**
     * Crete a new empty QueueArray by providing the length of the queue.
     *
     * @param size
     */
    public QueueArray(int size) {
        this.size = size;
        array = new Node[size];
    }

    public Node[] getArray() {
        return array;
    }

    /**
     * Method that determines whether of not an element is present in a queue.
     *
     * @param array The queue.
     * @param obj   The node
     * @return True if the element is present and false if it's not.
     */
    boolean containsAny(Node[] array, Object obj) {
        boolean result = false;
        for (int i = 0; i < array.length; i++) {
            result = result | (array[i] == obj);
        }
        return result;
    }

    /**
     * Method that checks whether all of the elements in a queue are null.
     *
     * @param array The queue.
     * @return True if all the elements are null, false otherwise.
     */
    boolean isEmpty(Node[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method that inserts a node to the end of the list and sets the tail to the last inserted node.
     *
     * @return The node that was enqueued.
     * @throws QueueOverflowException If the queue is full then it throws a QueueOverflowException
     */
    @Override
    public void enqueue(Node node) throws QueueOverflowException, VirtualOverflowException {
        try {
            // The first block will only executed the first time around.
            if (array[head] == null) {
                // Adds the node to the array.
                // Sets the head and tail to the last inserted node.
                array[head] = node;
                array[tail] = node;
                //if any of the array elements are null and the tail is at the last element
            } else if (!containsAny(array, null)) {
                throw new QueueOverflowException();
            } else if (array[tail] == array[size - 1]) {
                throw new VirtualOverflowException();
            } else {
                array[++tail] = node;
            }
        } catch (VirtualOverflowException ex) {
            handle(array);
            array[++tail] = node;
        }
    }

    /**
     * Method that removes a node from the front of the queue and sets the head to the next node.
     *
     * @return The node that was dequeued.
     * @throws QueueUnderflowException If the queue has no more elements to dequeue then it throws a QueueUnderflowException
     */
    @Override
    public Node dequeue() throws QueueUnderflowException {
        Node result = array[head];
        if (isEmpty(array)) {
            throw new QueueUnderflowException();
        } else {
            array[head++] = null;
        }

        if (isEmpty(array)) {
            head = tail = 0;
        }
        return result;
    }

    /**
     * Method that throws an exception in case of a virtual overflow.
     *
     * @param nodes The queue array.
     * @throws VirtualOverflowException
     */
    @Override
    public void handle(Node[] nodes) throws VirtualOverflowException {
        throw new VirtualOverflowException();
    }

    @Override
    public List<Node> displayAllAsList() {
        return Arrays.asList(array);
    }
}
