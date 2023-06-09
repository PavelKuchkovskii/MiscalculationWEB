package by.euroholl.userservice.controller;

import by.euroholl.userservice.service.UserService;
import by.euroholl.userservice.service.dto.UserCreateByAdminDTO;
import by.euroholl.userservice.service.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class AdminController {

    private final UserService service;

    public AdminController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserDTO> doPost(@Valid @RequestBody UserCreateByAdminDTO dto) {

        UserDTO created = this.service.create(dto);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
