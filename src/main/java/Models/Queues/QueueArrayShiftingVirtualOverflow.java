package Models.Queues;

import Models.Interfaces.IVirtualOverflow;
import Models.Node;

public class QueueArrayShiftingVirtualOverflow extends QueueArray implements Queue<Node>, IVirtualOverflow {

    /**
     * Crete a new empty QueueArrayShiftingVirtualOverflow object by providing the length of the queue.
     *
     * @param size
     */
    public QueueArrayShiftingVirtualOverflow(int size) {
        super(size);
    }

    /**
     * Method that shifts the elements until the head is in the first element of the list.
     * It puts the next element's value to the current position and sets the next element's value to null.
     *
     * @param array
     */
    @Override
    public void handle(Node[] array) {
        int shiftingIndex = head; // number of elements that the queue should be shifted
        for (int i = 0; i <= tail - head; i++) {
            // If it is the last element this swap should not happen, as it is empty due to the shift.
            array[i] = array[i + shiftingIndex];
            array[i + shiftingIndex] = null;
        }
        // The new tail will be the difference between the last tail and the last head.
        tail = tail - head;
        head = 0;
    }
}
