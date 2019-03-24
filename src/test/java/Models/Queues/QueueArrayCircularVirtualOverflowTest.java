package Models.Queues;

import Models.Exceptions.QueueOverflowException;
import Models.Exceptions.QueueUnderflowException;
import Models.Node;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

class QueueArrayCircularVirtualOverflowTest {

    QueueArray queue;

    @BeforeEach
    void setUp() {
        queue = new QueueArrayCircularVirtualOverflow(100);
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
        queue.enqueue(new Node("1"));
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
        queue.enqueue(new Node("a"));
        queue.dequeue();
        queue.enqueue(new Node("a"));
        queue.enqueue(new Node("b"));
        assert queue.getTailNode().getData().equals("b");
    }

    /**
     *
     */
    @Test
    void enqueueLastElementAnddequeue() {
        for (int i = 1; i <= 100; i++) {
            queue.enqueue(new Node(Integer.toString(i)));
        }
        // trwei ena element
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(new Node("a"));
        assert queue.getTailNode().getData().equals("a");
        System.out.println(queue.getTail());
        assert queue.getTail() == 0;
    }

    @Test
    void handleVirtualOverflowAndReturnToRegular() {
        queue = new QueueArrayCircularVirtualOverflow(3);
        queue.enqueue(new Node("a"));
        queue.enqueue(new Node("b"));
        queue.enqueue(new Node("c"));
        queue.dequeue();
        queue.enqueue(new Node("d"));
        queue.dequeue();
        queue.enqueue(new Node("e"));
        queue.dequeue();
        queue.enqueue(new Node("f"));
        assert queue.getTailNode().getData().equals("f");
        System.out.println(queue.getTail());
        assert queue.getTail() == 2;
    }

    @Test
    void handle() {
        for (int i = 1; i <= 100; i++) {
            queue.enqueue(new Node(Integer.toString(i)));
        }
        queue.dequeue();
        queue.handle(queue.getArray());
        assert queue.tail == -1;
    }
}