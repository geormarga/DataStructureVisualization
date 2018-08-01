package Models.Stacks;

import Models.Exceptions.StackUnderflowException;
import Models.Node;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class StackLinkedListTest {

    StackLinkedList stack;

    @BeforeEach
    void setUp() {
        stack = new StackLinkedList();
    }

    @AfterEach
    void tearDown() {
        stack = null;
    }

    @Test
    void pushElement() {
        stack.push(new Node("1"));
        stack.push(new Node("2"));
        stack.push(new Node("3"));
        assert stack.getTop().getData() == "3";
    }

    @Test
    void popElement() {
        stack.push(new Node("1"));
        stack.push(new Node("2"));
        stack.push(new Node("3"));
        stack.push(new Node("4"));
        stack.pop();
        stack.pop();
        stack.pop();
        assert stack.getTop().getData() == "1";
    }


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

    @Test
    void popLastElement() {
        stack.push(new Node("1"));
        stack.pop();
        assert stack.getTop() == null;
    }

    @Test
    void popLastElementAndPush() {
        stack.push(new Node("1"));
        stack.pop();
        stack.push(new Node("1"));
        stack.push(new Node("2"));
        assert stack.getTop().getData() == "2";
    }
}