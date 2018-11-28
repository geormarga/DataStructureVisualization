package Models.Queues;

import Models.Exceptions.QueueOverflowException;
import Models.Exceptions.QueueUnderflowException;
import Models.Node;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

class QueueArrayShiftingVirtualOverflowTest {

    QueueArray queue;

    @BeforeEach
    void setUp() {
        queue = new QueueArrayShiftingVirtualOverflow(100);
    }

    @AfterEach
    void tearDown() {
        queue = null;
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
        queue.enqueue(new Node("23"));
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
        Executable closureContainingCodeToTest = () -> {
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
        for (int i = 1; i <= 100; i++) {
            queue.enqueue(new Node(Integer.toString(i)));
        }
        queue.dequeue();
        queue.enqueue(new Node(Integer.toString(101)));
        assert queue.getTailNode().getData().equals("101");
    }

    /**
     *
     */
    @Test
    void dequeueElementFromEmptyStack() {
        Executable closureContainingCodeToTest = () -> {
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
        queue.enqueue(new Node("3"));
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

    @Test
    void handle() {
        for (int i = 1; i <= 100; i++) {
            queue.enqueue(new Node(Integer.toString(i)));
        }
        queue.dequeue();
        queue.handle(queue.getArray());
        assert queue.tail == 98;
    }
}