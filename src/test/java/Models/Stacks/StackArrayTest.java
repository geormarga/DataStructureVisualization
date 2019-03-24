package Models.Stacks;

import Models.Exceptions.StackOverflowException;
import Models.Exceptions.StackUnderflowException;
import Models.Node;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

class StackArrayTest {

    StackArray stack;

    @BeforeEach
    void setUp() {
        stack = new StackArray(100);
    }

    @AfterEach
    void tearDown() {
        stack = null;
    }

    /**
     *
     */
    @Test
    void pushElement() {
        stack.push(new Node("1"));
        stack.push(new Node("2"));
        stack.push(new Node("3"));
        assert stack.getTopNode().getData() == "3";
    }

    /**
     *
     */
    @Test
    void popElement() {
        stack.push(new Node("5"));
        stack.push(new Node("2"));
        stack.push(new Node("3"));
        stack.push(new Node("4"));
        stack.pop();
        stack.pop();
        stack.pop();
        assert stack.getTopNode().getData() == "5";
    }

    /**
     *
     */
    @Test
    void pushElementToFullStack() {
        Executable closureContainingCodeToTest = () ->
        {
            for (int i = 1; i <= 101; i++) {
                stack.push(new Node(Integer.toString(i)));
            }
        };

        assertThrows(StackOverflowException.class, closureContainingCodeToTest);
        //Check how to assert that an exception is thrown
    }

    /**
     *
     */
    @Test
    void popElementFromEmptyStack() {
        Executable closureContainingCodeToTest = () ->
        {
            stack.push(new Node("This will be deleted."));
            stack.pop();
            stack.pop();

        };
        assertThrows(StackUnderflowException.class, closureContainingCodeToTest);
    }

    /**
     *
     */
    @Test
    void popLastElement() {
        stack.push(new Node("1"));
        stack.pop();
        assert stack.getTopNode() == null;
    }

    /**
     *
     */
    @Test
    void pushLastElement() {
        for (int i = 1; i <= 100; i++) {
            stack.push(new Node(Integer.toString(i)));
        }
        assert stack.getTopNode().getData().equals("100");
    }

    /**
     *
     */
    @Test
    void popLastElementAndPush() {
        stack.push(new Node("211"));
        stack.pop();
        stack.push(new Node("1"));
        stack.push(new Node("2"));
        assert stack.getTopNode().getData().equals("2");
    }

    /**
     *
     */
    @Test
    void pushLastElementAndPop() {
        for (int i = 1; i <= 100; i++) {
            stack.push(new Node(Integer.toString(i)));
        }
        stack.pop();
        assert stack.getTopNode().getData().equals("99");
    }
}