package Models.Exceptions;

import Helpers.Utils;

public class StackOverflowException extends RuntimeException {
    public StackOverflowException() {
        super(Utils.i18n("TRANSLATE_EX_STACK_OVER"));
    }
}
