package Models.Exceptions;

public class StackUnderflowException extends Exception {
    public StackUnderflowException() {
        super("Stack has no elements to pop.");
    }
}
