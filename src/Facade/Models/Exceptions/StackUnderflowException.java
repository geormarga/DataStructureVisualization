package Models.Exceptions;

public class StackUnderflowException extends RuntimeException {
    public StackUnderflowException() {
        super(Facade.Utils.i18n("TRANSLATE_EX_STACK_UNDER"));
    }
}
