package Models.Exceptions;

import Helpers.Utils;

public class QueueUnderflowException extends RuntimeException {
    public QueueUnderflowException() {
        super(Utils.i18n("TRANSLATE_EX_QUEUE_UNDER"));
    }
}
