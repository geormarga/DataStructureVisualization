package Models.Exceptions;

public class QueueUnderflowException extends Exception{
    QueueUnderflowException(){
        super("Queue has no elements to dequeue.");
    }

}
