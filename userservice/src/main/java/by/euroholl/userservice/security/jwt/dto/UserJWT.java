package by.euroholl.userservice.security.jwt.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class UserJWT {
    private String email;
    private List<GrantedAuthority> authorities;

    public UserJWT(String email, List<GrantedAuthority> authorities) {
        this.email = email;
        this.authorities = authorities;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
