package by.euroholl.userservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AuthController {

    /*private final AuthService service;
    private final PasswordEncoder encoder;

    public AuthController(AuthService service, PasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public ResponseEntity<String> doPost(@Valid @RequestBody UserLoginDTO dto) throws JsonProcessingException {

        UserDTO user = this.service.loadByMail(dto.getMail());

        if(!encoder.matches(dto.getPassword(), user.getPassword())){
            throw new BadCredentialsException("Bad credentials");
        }

        if(!user.getStatus().equals(EUserStatus.ACTIVATED)) {
            throw new UserNotActivatedException("User not activated");
        }

        return new ResponseEntity<>(JwtTokenUtil.generateAccessToken(user), HttpStatus.OK);
    }*/


}