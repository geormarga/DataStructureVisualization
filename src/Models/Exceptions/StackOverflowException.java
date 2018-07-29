package Models.Exceptions;

public class StackOverflowException extends Exception {
    public StackOverflowException() {
        super("Stack reached its maximum capacity.");
    }
}
