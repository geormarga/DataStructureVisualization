package Models.Queues;

import Models.Exceptions.QueueOverflowException;
import Models.Exceptions.QueueUnderflowException;

public interface Queue<T>{

    /**
     * Method that adds another element at the end of the queue.
     *
     * @param t The element that is added to the end of the queue.
     */
    void enqueue(T t) throws QueueOverflowException;

    /**
     * Method that removes the first element from the start of the queue.
     *
     * @return The element that is removed from the start of the queue.
     */
    T dequeue() throws QueueUnderflowException;
}
