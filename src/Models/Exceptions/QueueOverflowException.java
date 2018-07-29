package Models.Exceptions;

public class QueueOverflowException extends Exception{
    public QueueOverflowException(){
        super("Queue has reached its maximum capacity.");
    }
}
