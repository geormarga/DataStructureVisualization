package Models.Exceptions;

public class QueueUnderflowException extends Exception{
    public QueueUnderflowException(){
        super("Queue has no elements to dequeue.");
    }
}
