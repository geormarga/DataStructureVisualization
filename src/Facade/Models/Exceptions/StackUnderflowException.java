package Models.Exceptions;

public class StackUnderflowException extends RuntimeException {
    public StackUnderflowException() {
        super("Stack has no elements to pop.");
    }
}
