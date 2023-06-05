package by.euroholl.userservice.controller;

import by.euroholl.userservice.config.api.Message;
import by.euroholl.userservice.service.dto.UserLoginDTO;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretPayload;
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
    private final SecretManagerServiceClient secretManagerServiceClient;

    public AuthController(AuthenticationManager authenticationManager, SecretManagerServiceClient secretManagerServiceClient) {
        this.authenticationManager = authenticationManager;
        this.secretManagerServiceClient = secretManagerServiceClient;
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

        //В этом месте нужно сгенерировать токен для пользователя
        String token = "This is JWT Token";

        Message message = new Message("info", token);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/row")
    public ResponseEntity<Message> doGet() {


        SecretPayload payload = secretManagerServiceClient.accessSecretVersion("projects/333503614145/secrets/JWT_SECRET/versions/latest").getPayload();

        Message message = new Message("info", payload.getData().toStringUtf8());

        return new ResponseEntity<>(message, HttpStatus.OK);
    }




}