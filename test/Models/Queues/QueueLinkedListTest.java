package Models.Queues;

import Models.Exceptions.QueueUnderflowException;
import Models.Node;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

class QueueLinkedListTest {

    QueueLinkedList queue;

    @BeforeEach
    void setUp() {
        queue = new QueueLinkedList();
    }

    @AfterEach
    void tearDown() {
        queue = null;
    }

    @Test
    void getHead() {
        Node node = new Node("1");
        queue.enqueue(node);
        assert queue.getHead() == node;
    }

    @Test
    void getTail() {
        Node node = new Node("1");
        queue.enqueue(node);
        assert queue.getTail() == node;

    }

    /**
     *
     */
    @Test
    void enqueueElement() {
        queue.enqueue(new Node("1"));
        queue.enqueue(new Node("2"));
        queue.enqueue(new Node("3"));
        assert queue.getTail().getData() == "3";
    }

    /**
     *
     */
    @Test
    void dequeueElement() {
        queue.enqueue(new Node("10"));
        queue.enqueue(new Node("20"));
        queue.enqueue(new Node("30"));
        queue.enqueue(new Node("40"));
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assert queue.getTail().getData().equals("40");
    }

    /**
     *
     */
    @Test
    void dequeueElementFromEmptyStack() {
        Executable closureContainingCodeToTest = () ->
        {
            queue.enqueue(new Node("This will be deleted."));
            queue.dequeue();
            queue.dequeue();
        };
        assertThrows(QueueUnderflowException.class, closureContainingCodeToTest);
    }

    /**
     *
     */
    @Test
    void dequeueLastElement() {
        queue.enqueue(new Node("1"));
        queue.dequeue();
        assert queue.getTail() == null;
    }

    /**
     *
     */
    @Test
    void dequeueLastElementAndEnqueue() {
        queue.enqueue(new Node("211"));
        queue.dequeue();
        queue.enqueue(new Node("1"));
        queue.enqueue(new Node("2"));
        assert queue.getTail().getData().equals("2");
    }

}