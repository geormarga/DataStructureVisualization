package Models.Exceptions;

public class StackOverflowException extends RuntimeException {
    public StackOverflowException() {
        super(Facade.Utils.i18n("TRANSLATE_EX_STACK_OVER"));
    }
}
