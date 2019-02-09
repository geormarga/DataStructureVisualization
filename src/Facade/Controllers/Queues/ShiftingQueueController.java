package Controllers.Queues;

import Models.Queues.QueueArray;
import Models.Queues.QueueArrayShiftingVirtualOverflow;

public class ShiftingQueueController extends QueueArrayController {

    @Override
    public QueueArray getNewQueueArray(int size) {
        return new QueueArrayShiftingVirtualOverflow(size);
    }
}