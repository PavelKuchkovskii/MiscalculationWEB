package by.euroholl.userservice.config.exception.api.auth;

public class AlreadyActivatedException extends RuntimeException {
    public AlreadyActivatedException(String message) {
        super(message);
    }
}