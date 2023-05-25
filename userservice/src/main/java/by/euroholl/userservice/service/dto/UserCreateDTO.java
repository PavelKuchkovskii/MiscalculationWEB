package by.euroholl.userservice.service.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class UserCreateDTO {

    @Email(message = "Mail must be valid")
    private String mail;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Surname cannot be blank")
    private String surname;
    private String password;
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}