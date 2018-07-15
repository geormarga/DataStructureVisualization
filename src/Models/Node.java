package Models;

public class Node extends Object {

    private String data;
    private Node next;

    public Node(String data) {
        this.data = data;
    }

    public void setNext(Node node) {
        this.next = node;
    }
}
