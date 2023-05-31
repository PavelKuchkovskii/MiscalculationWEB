package by.euroholl.userservice.service;

import by.euroholl.userservice.config.exception.api.auth.BadCredentialsException;
import by.euroholl.userservice.config.exception.api.auth.EmailNotConfirmException;
import by.euroholl.userservice.config.exception.api.auth.NotActivatedException;
import by.euroholl.userservice.config.exception.api.auth.UserDeactivatedException;
import by.euroholl.userservice.dao.api.IUserDao;
import by.euroholl.userservice.dao.entity.User;
import by.euroholl.userservice.dao.entity.enums.EUserStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    private final IUserDao dao;

    public AuthService(IUserDao dao) {
        this.dao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = dao.findByEmail(username);
        if (user.isEmpty()) {
            throw new BadCredentialsException("Invalid username or password");
        }

        //Validate user for status
        validate(user.get());

        return new org.springframework.security.core.userdetails.User(
                user.get().getEmail(),
                user.get().getPassword(),
                Collections.emptyList()
        );
    }

    public void validate(User user) {
        if(user.getStatus() == EUserStatus.WAITING_VERIFICATION || user.getStatus() == EUserStatus.WAITING_CONFIRM) {
            throw new EmailNotConfirmException("Check you email and tap to link to confirm registration");
        }
        else if(user.getStatus() == EUserStatus.WAITING_ACTIVATION) {
            throw new NotActivatedException("Your account has not been activated. Contact the administrator");
        }
        else if(user.getStatus() == EUserStatus.DEACTIVATED) {
            throw new UserDeactivatedException("Your account has been deactivated. Contact the administrator");
        }
    }
 }
