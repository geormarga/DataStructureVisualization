package Models.Exceptions;

public class VirtualOverflowException extends Exception {
    VirtualOverflowException() {
        super("Queue has reached its maximum capacity.");
    }
}
