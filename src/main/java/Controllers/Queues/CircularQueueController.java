package Controllers.Queues;

import Models.Queues.QueueArray;
import Models.Queues.QueueArrayCircularVirtualOverflow;

public class CircularQueueController extends QueueArrayController {

    @Override
    public QueueArray getNewQueueArray(int size) {
        return new QueueArrayCircularVirtualOverflow(size);
    }
}