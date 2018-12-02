package Models.Exceptions;

public class QueueUnderflowException extends RuntimeException{
    public QueueUnderflowException(){
        super("Queue has no elements to dequeue.");
    }
}
