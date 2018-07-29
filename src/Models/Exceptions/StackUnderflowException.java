package Models.Exceptions;

public class StackUnderflowException extends Exception {
    StackUnderflowException() {
        super("Stack has no elements to pop.");
    }
}
