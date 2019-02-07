package Models.Exceptions;

public class QueueOverflowException extends RuntimeException {
    public QueueOverflowException() {
        super(Facade.Utils.i18n("TRANSLATE_EX_QUEUE_OVER"));
    }
}