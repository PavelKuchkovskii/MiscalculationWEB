package by.euroholl.userservice.security.jwt.exception;

import by.euroholl.userservice.security.jwt.exception.api.CustomJwtTokenException;

public class InvalidJwtTokenException extends CustomJwtTokenException {
    public InvalidJwtTokenException(String message) {
        super(message);
    }
}
