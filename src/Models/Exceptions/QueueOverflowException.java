package Models.Exceptions;

public class QueueOverflowException extends RuntimeException{
    public QueueOverflowException(){
        super("Queue has reached its maximum capacity.");
    }
}
