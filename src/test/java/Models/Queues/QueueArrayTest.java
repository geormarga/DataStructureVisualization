package Models.Queues;

import Models.Exceptions.QueueOverflowException;
import Models.Exceptions.QueueUnderflowException;
import Models.Exceptions.VirtualOverflowException;
import Models.Node;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

class QueueArrayTest {

    QueueArray queue;

    @BeforeEach
    void setUp() {
        queue = new QueueArray(100);
    }

    @AfterEach
    void tearDown() {
        queue = null;
    }

    @Test
    void getLength() {
        assert queue.getLength() == 100;
    }

    @Test
    void getHead() {
        Node node = new Node("1");
        queue.enqueue(node);
        assert queue.getHeadNode() == node;
    }

    @Test
    void getTail() {
        Node node = new Node("1");
        queue.enqueue(node);
        assert queue.getTailNode() == node;

    }

    @Test
    void getArray() {
    }

    @Test
    void setArray() {
    }

    @Test
    void containsAny() {
    }

    /**
     *
     */
    @Test
    void enqueueElement() {
        queue.enqueue(new Node("1"));
        queue.enqueue(new Node("2"));
        queue.enqueue(new Node("3"));
        assert queue.getTailNode().getData() == "3";
    }

    /**
     *
     */
    @Test
    void dequeueElement() {
        queue.enqueue(new Node("5"));
        queue.enqueue(new Node("2"));
        queue.enqueue(new Node("3"));
        queue.enqueue(new Node("4"));
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assert queue.getTailNode().getData().equals("4");
    }

    /**
     *
     */
    @Test
    void enqueueElementToFullStack() {
        Executable closureContainingCodeToTest = () ->
        {
            for (int i = 1; i <= 101; i++) {
                queue.enqueue(new Node(Integer.toString(i)));
            }
        };

        assertThrows(QueueOverflowException.class, closureContainingCodeToTest);
    }

    /**
     *
     */
    @Test
    void enqueueElementToFullStackWithEmptySlots() {
        Executable closureContainingCodeToTest = () ->
        {
            for (int i = 1; i <= 100; i++) {
                queue.enqueue(new Node(Integer.toString(i)));
            }
            queue.dequeue();
            queue.enqueue(new Node(Integer.toString(101)));
        };

        assertThrows(VirtualOverflowException.class, closureContainingCodeToTest);
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
        assert queue.getTailNode() == null;
    }

    /**
     *
     */
    @Test
    void enqueueLastElement() {
        for (int i = 1; i <= 100; i++) {
            queue.enqueue(new Node(Integer.toString(i)));
        }
        assert queue.getTailNode().getData().equals("100");
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
        assert queue.getTailNode().getData().equals("2");
    }

    /**
     *
     */
    @Test
    void enqueueLastElementAnddequeue() {
        for (int i = 1; i <= 100; i++) {
            queue.enqueue(new Node(Integer.toString(i)));
        }
        queue.dequeue();
        assert queue.getTailNode().getData().equals("100");
    }

}