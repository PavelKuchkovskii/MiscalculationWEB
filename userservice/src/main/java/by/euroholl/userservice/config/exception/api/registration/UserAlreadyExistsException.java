package by.euroholl.userservice.config.exception.api.registration;

import by.euroholl.userservice.config.exception.api.registration.api.RegistrationException;

public class UserAlreadyExistsException extends RegistrationException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }

}