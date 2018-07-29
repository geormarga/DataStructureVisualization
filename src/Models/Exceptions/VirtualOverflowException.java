package Models.Exceptions;

public class VirtualOverflowException extends Exception {
    public VirtualOverflowException() {
        super("Queue has reached its maximum capacity.");
    }
}
