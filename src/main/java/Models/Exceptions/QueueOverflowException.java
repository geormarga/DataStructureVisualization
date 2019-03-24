package Models.Exceptions;

import Helpers.Utils;

public class QueueOverflowException extends RuntimeException {
    public QueueOverflowException() {
        super(Utils.i18n("TRANSLATE_EX_QUEUE_OVER"));
    }
}