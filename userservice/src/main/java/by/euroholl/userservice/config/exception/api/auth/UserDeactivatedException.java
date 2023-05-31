package by.euroholl.userservice.config.exception.api.auth;

public class UserDeactivatedException extends RuntimeException {

    public UserDeactivatedException (String message) {
        super(message);
    }
}
