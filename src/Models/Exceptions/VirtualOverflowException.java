package Models.Exceptions;

public class VirtualOverflowException extends RuntimeException {
    public VirtualOverflowException() {
        super("Queue has reached its maximum capacity.");
    }
}
