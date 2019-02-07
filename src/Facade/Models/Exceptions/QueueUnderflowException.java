package Models.Exceptions;

public class QueueUnderflowException extends RuntimeException {
    public QueueUnderflowException() {
        super(Facade.Utils.i18n("TRANSLATE_EX_QUEUE_UNDER"));
    }
}
