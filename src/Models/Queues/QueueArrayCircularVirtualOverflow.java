package Models.Queues;

import Models.Interfaces.IVirtualOverflow;
import Models.Node;

public class QueueArrayCircularVirtualOverflow extends QueueArray implements Queue<Node>, IVirtualOverflow {

    /**
     * Crete a new empty QueueArrayCircularVirtualOverflow object by providing the length of the queue.
     *
     * @param size
     */
    public QueueArrayCircularVirtualOverflow(int size) {
        this.size = size;
        array = new Node[size];
    }

    /**
     * Create a new QueueArrayCircularVirtualOverflow object by providing an array of Node objects.
     *
     * @param array
     */
    public QueueArrayCircularVirtualOverflow(Node[] array) {
        this.array = array;
    }

    /**
     * Method that sets the tail equal to zero if there is a VirtualOverflowException, so that the queue acts like a circular queue.
     *
     * @param array
     */
    @Override
    public void handle(Node[] array) {
        tail = 0;
    }
}
