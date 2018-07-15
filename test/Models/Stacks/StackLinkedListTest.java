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
        assert stackLinkedList.top.getData() == "3";
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
        assert stackLinkedList.top.getData() == "1";
    }

    @Test
    void pushElementToFullStack() {

    }

    @Test
    void popElementFromEmptyStack() {

    }

    @Test
    void popLastElement() {

    }

    @Test
    void pushLastElement() {
         // This isn't needed here but it should be used in StackArray
    }
}