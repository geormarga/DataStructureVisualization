package Models.Queues;

import Models.Exceptions.QueueOverflowException;
import Models.Exceptions.VirtualOverflowException;
import Models.Interfaces.IVirtualOverflow;
import Models.Node;

public class QueueArrayShiftingVirtualOverflow extends QueueArray implements Queue<Node>, IVirtualOverflow {

    /**
     * Crete a new empty QueueArrayShiftingVirtualOverflow object by providing the length of the queue.
     *
     * @param size
     */
    public QueueArrayShiftingVirtualOverflow(int size) {
        this.size = size;
        array = new Node[size];
    }

    /**
     * Create a new QueueArrayShiftingVirtualOverflow object by providing an array of Node objects.
     *
     * @param array
     */
    public QueueArrayShiftingVirtualOverflow(Node[] array) {
        this.array = array;
    }


    @Override
    public void enqueue(Node node) throws QueueOverflowException {
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
            handle(array);
            array[++tail] = node;
        } else {
            array[++tail] = node;
        }
    }

    /**
     * Method that shifts the elements until the head is in the first element of the list.
     * It puts the next element's value to the current position and sets the next element's value to null.
     *
     * @param array
     */
    @Override
    public void handle(Node[] array) {
        for (int i = 0; i < array.length; i++) {
            // If it is the last element this swap should not happen, as it is empty due to the shift.
            if ((i - 1) == array.length) {
                array[i] = array[i + 1];
                array[i + 1] = null;
            }
        }
        // The new tail will be the difference between the last tail and the last head.
        tail = tail - head;
        head = 0;
    }
}
