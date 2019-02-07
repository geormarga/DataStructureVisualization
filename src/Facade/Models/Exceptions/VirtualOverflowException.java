package Models.Exceptions;

public class VirtualOverflowException extends RuntimeException {
    public VirtualOverflowException() {
        super(Facade.Utils.i18n("TRANSLATE_EX_QUEUE_VIRTUAL_OVER"));
    }
}
