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
    public void handle(Node[] array) {
        // Determine how much the difference between the head and the tail is. (tail - head)
        // Determine how much the difference between the first element and the head is. head
        // Shift all the elements to the first place and fill the others with null.
        // The head is going to be set to the front of the queue and the tail to the front + the difference the head from the tail has.
        //int diff = head - tail;
        for (int i = 0; i < array.length; i++) {
            if (i <= (tail - head) && ((head + i) < array.length)) {
                array[i] = array[head + i];
                array[head + i] = null;
            } else {
                array[i] = null;
            }
        }
        tail = array.length - head;
        head = 0;
    }
}
