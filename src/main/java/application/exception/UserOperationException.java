package application.exception;

public class UserOperationException extends RuntimeException{
    public UserOperationException(String message) {
        super(message);
    }
}
