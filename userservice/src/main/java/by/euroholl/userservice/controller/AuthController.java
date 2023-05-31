package by.euroholl.userservice.controller;

import by.euroholl.userservice.config.api.Message;
import by.euroholl.userservice.service.dto.UserLoginDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<Message> doPost(@Valid @RequestBody UserLoginDTO dto) {

        // Создание аутентификационного объекта
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                dto.getEmail(), dto.getPassword());

        // Аутентификация
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // Установка аутентификации в контексте безопасности
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Message message = new Message("info", "This is JWT Token");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/row")
    public ResponseEntity<Message> doGet() {

        Message message = new Message("info", "попали");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }




}