package by.euroholl.userservice.config.exception.api;


public class AlreadyActivatedException extends RuntimeException {
    public AlreadyActivatedException(String message) {
        super(message);
    }
}