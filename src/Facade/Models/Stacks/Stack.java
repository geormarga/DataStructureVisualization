package Models.Stacks;

import Models.Exceptions.StackOverflowException;
import Models.Exceptions.StackUnderflowException;

public interface Stack<T> {

    /**
     * Method that adds another element at the end of the stack.
     *
     * @param t The element that is going to be added to the end of the stack.
     */
    void push(T t) throws StackOverflowException;

    /**
     * Method that removes the last element from the end of the stack.
     */
    T pop() throws StackUnderflowException;
}
