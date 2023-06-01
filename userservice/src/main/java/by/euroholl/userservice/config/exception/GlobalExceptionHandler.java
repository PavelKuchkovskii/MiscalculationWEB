package by.euroholl.userservice.config.exception;


import by.euroholl.userservice.config.api.Message;
import by.euroholl.userservice.config.exception.api.registration.api.RegistrationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AuthenticationException.class, RegistrationException.class})
    protected ResponseEntity<Object> handleAuthException(RuntimeException ex) {
        Message error = new Message("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

}
