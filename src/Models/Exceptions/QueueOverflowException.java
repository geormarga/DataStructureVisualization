package Models.Exceptions;

public class QueueOverflowException extends Exception{
    QueueOverflowException(){
        super("Queue has reached its maximum capacity.");
    }
}
