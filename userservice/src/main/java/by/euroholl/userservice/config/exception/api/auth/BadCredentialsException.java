package by.euroholl.userservice.config.exception.api.auth;

public class BadCredentialsException extends RuntimeException {

    public BadCredentialsException(String message) {
        super(message);
    }
}