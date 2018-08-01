package Models.Stacks;

import Models.Node;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackLinkedListTest {

    StackLinkedList stackLinkedList;

    @BeforeEach
    void setUp() {
        stackLinkedList = new StackLinkedList();
    }

    @AfterEach
    void tearDown() {
        stackLinkedList = null;
    }

    @Test
    void pushElement() {
        stackLinkedList.push(new Node("1"));
        stackLinkedList.push(new Node("2"));
        stackLinkedList.push(new Node("3"));
        assert stackLinkedList.getTop().getData() == "3";
    }

    @Test
    void popElement() {
        stackLinkedList.push(new Node("1"));
        stackLinkedList.push(new Node("2"));
        stackLinkedList.push(new Node("3"));
        stackLinkedList.push(new Node("4"));
        stackLinkedList.pop();
        stackLinkedList.pop();
        stackLinkedList.pop();
        assert stackLinkedList.getTop().getData() == "1";
    }


    @Test
    void popElementFromEmptyStack() {
        stackLinkedList.push(new Node("1"));
        stackLinkedList.push(new Node("2"));
        stackLinkedList.pop();
        stackLinkedList.pop();
        stackLinkedList.pop();
    }

    @Test
    void popLastElement() {
        stackLinkedList.push(new Node("1"));
        stackLinkedList.pop();
        assert stackLinkedList.getTop() == null;
    }

    @Test
    void popLastElementAndPush() {
        stackLinkedList.push(new Node("1"));
        stackLinkedList.pop();
        stackLinkedList.push(new Node("1"));
        stackLinkedList.push(new Node("2"));
        assert stackLinkedList.getTop().getData() == "2";
    }
}