package Models.Exceptions;

import Helpers.Utils;

public class StackUnderflowException extends RuntimeException {
    public StackUnderflowException() {
        super(Utils.i18n("TRANSLATE_EX_STACK_UNDER"));
    }
}
