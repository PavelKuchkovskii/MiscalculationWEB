package by.euroholl.userservice.security.jwt.exception;

import by.euroholl.userservice.security.jwt.exception.api.CustomJwtTokenException;

public class ExpiredJwtTokenException extends CustomJwtTokenException {
    public ExpiredJwtTokenException(String message) {
        super(message);
    }
}
