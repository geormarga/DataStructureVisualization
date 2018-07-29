package Models.Exceptions;

public class StackOverflowException extends Exception {
    StackOverflowException() {
        super("Stack reached its maximum capacity.");
    }
}
