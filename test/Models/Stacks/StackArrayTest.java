package Models.Stacks;

import Models.Node;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackArrayTest {

    StackArray stackArray;

    @BeforeEach
    void setUp() {
        stackArray = new StackArray(100);
    }

    @AfterEach
    void tearDown() {
        stackArray = null;
    }

    /**
     *
     */
    @Test
    void pushElement() {
        stackArray.push(new Node("1"));
        stackArray.push(new Node("2"));
        stackArray.push(new Node("3"));
        assert stackArray.getTop().getData() == "3";
    }

    /**
     *
     */
    @Test
    void popElement() {
        stackArray.push(new Node("5"));
        stackArray.push(new Node("2"));
        stackArray.push(new Node("3"));
        stackArray.push(new Node("4"));
        stackArray.pop();
        stackArray.pop();
        stackArray.pop();
        assert stackArray.getTop().getData() == "5";
    }

    /**
     *
     */
    @Test
    void pushElementToFullStack() {
        for (int i = 1; i <= 101; i++) {
            stackArray.push(new Node(Integer.toString(i)));
        }

        //Check how to assert that an exception is thrown
        assert true == true;
    }

    /**
     *
     */
    @Test
    void popElementFromEmptyStack() {
        stackArray.push(new Node("15"));
        stackArray.push(new Node("2"));
        stackArray.pop();
        stackArray.pop();
        stackArray.pop();
    }

    /**
     *
     */
    @Test
    void popLastElement() {
        stackArray.push(new Node("1"));
        stackArray.pop();
        assert stackArray.getTop() == null;
    }

    /**
     *
     */
    @Test
    void pushLastElement() {
        for (int i = 1; i <= 100; i++) {
            stackArray.push(new Node(Integer.toString(i)));
        }
        assert stackArray.getTop().getData().equals("100");
    }

    /**
     *
     */
    @Test
    void popLastElementAndPush() {
        stackArray.push(new Node("211"));
        stackArray.pop();
        stackArray.push(new Node("1"));
        stackArray.push(new Node("2"));
        assert stackArray.getTop().getData().equals("2");
    }

    /**
     *
     */
    @Test
    void pushLastElementAndPop() {
        for (int i = 1; i <= 100; i++) {
            stackArray.push(new Node(Integer.toString(i)));
        }
        stackArray.pop();
        assert stackArray.getTop().getData().equals("99");
    }
}