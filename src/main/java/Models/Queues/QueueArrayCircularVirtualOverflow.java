package Models.Queues;

import Models.Interfaces.IVirtualOverflow;
import Models.Node;

public class QueueArrayCircularVirtualOverflow extends QueueArray implements Queue<Node>, IVirtualOverflow {

    /**
     * Create a new empty QueueArrayCircularVirtualOverflow object by providing the length of the queue.
     *
     * @param size
     */
    public QueueArrayCircularVirtualOverflow(int size) {
        super(size);
    }

    /**
     * Method that sets the tail equal to zero if there is a VirtualOverflowException, so that the queue acts like a circular queue.
     *
     * @param array
     */
    @Override
    public void handle(Node[] array) {
        tail = (tail == size - 1) ? -1 : tail;
    }
}
