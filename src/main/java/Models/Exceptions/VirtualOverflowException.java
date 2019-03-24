package Models.Exceptions;

import Helpers.Utils;

public class VirtualOverflowException extends RuntimeException {
    public VirtualOverflowException() {
        super(Utils.i18n("TRANSLATE_EX_QUEUE_VIRTUAL_OVER"));
    }
}
