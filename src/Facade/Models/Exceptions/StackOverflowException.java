package Models.Exceptions;

public class StackOverflowException extends RuntimeException {
    public StackOverflowException() {
        super("Stack reached its maximum capacity.");
    }
}
