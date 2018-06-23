public class QueueModel {
    private Node[] array;

    /**
     * Method that adds another node at the end of the queue.
     * @param node The element that is going to be added to the end of the queue.
     */
    public void enque(Node node) {
        array[array.length + 1] = node;
    }

    /**
     * Method that removes the first node from the start of the queue.
     */
    public void deque() {
        array[0] = new EmptyNode();
    }

}
