package Models.Stacks;

import Models.Exceptions.StackOverflowException;
import Models.Exceptions.StackUnderflowException;
import Models.Interfaces.IVisible;
import Models.Node;

import java.util.Arrays;
import java.util.List;

public class StackArray implements Stack<Node>, IVisible<Node> {

    private Node[] array;

    private int size, top = -1, bottom = 0;

    public StackArray(int size) {
        this.size = size;
        array = new Node[size];
    }

    public int getBottom() {
        return bottom;
    }

    public int getTop() {
        return top;
    }

    public int getLength() {
        return array.length;
    }

    public int getSize(){
        return size;
    }

    public boolean isTop(Node node){
        return array[top] == node;
    }

    public boolean isBottom(Node node){
        return array[top] == node;
    }

    public Node getBottomNode() {
        return array[bottom];
    }

    public Node getTopNode() {

        if (top < 0) {
            return null;
        } else {
            return array[top];
        }
    }

    boolean containsAny(Node[] array, Object obj) {
        boolean result = false;
        for (int i = 0; i < array.length; i++) {
            result = result | (array[i] == obj);
        }
        return result;
    }

    @Override
    public void push(Node node) {
        if (!containsAny(array, null)) {
            throw new StackOverflowException();
        } else {
            array[++top] = node;
        }
    }

    @Override
    public Node pop() {
        Node returnValue = null;
        if (top == -1) {
            throw new StackUnderflowException();
        } else {
            returnValue = array[top];
            array[top] = null;
            top--;
        }
        return returnValue;
    }

    @Override
    public List<Node> displayAllAsList() {
        return Arrays.asList(array);
    }
}
