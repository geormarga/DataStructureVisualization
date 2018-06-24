package Models.Interfaces;

import Models.Queues.Queue;

public interface IVirtualOverflow {
    void handle(Queue queue);
}
