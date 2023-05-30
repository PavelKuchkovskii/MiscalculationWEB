package by.euroholl.userservice.controller;

import by.euroholl.userservice.controller.api.RegistrationMessage;
import by.euroholl.userservice.service.UserService;
import by.euroholl.userservice.service.dto.UserCreateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    //private final ConfirmRegistrationService confirmService;

    public UserController(UserService service) {
        this.service = service;
    }

   /* @GetMapping("/me")
    public ResponseEntity<UserDTO> doGet() {
        UserDTO user = this.service.get(SecurityContextHolder.getContext().getAuthentication());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }*/

    /*@GetMapping("/registration/confirm/{token}")
    public ResponseEntity<RegistrationMessage> doConfirm(@PathVariable("token") String token) {

        confirmService.confirmRegistration(token);

        RegistrationMessage message = new RegistrationMessage("info", "User activated, login using credentials");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }*/

    @PostMapping("/registration")
    @CrossOrigin(origins = "*")
    public ResponseEntity<RegistrationMessage> doPost(@Valid @RequestBody UserCreateDTO dto) {

        service.create(dto);

        RegistrationMessage message = new RegistrationMessage("info", "To complete registration, please follow the link sent to your email");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}