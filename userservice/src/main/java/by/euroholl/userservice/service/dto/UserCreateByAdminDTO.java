package by.euroholl.userservice.service.dto;

import by.euroholl.userservice.dao.entity.enums.EUserRole;
import by.euroholl.userservice.dao.entity.enums.EUserStatus;

import javax.validation.constraints.*;

public class UserCreateByAdminDTO {
    @Email(message = "Mail must be valid")
    private String email;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Surname cannot be blank")
    private String surname;
    private String password;
    @NotNull(message = "Invalid role")
    private EUserRole role;
    @NotNull(message = "Invalid status")
    private EUserStatus status;

    public UserCreateByAdminDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EUserRole getRole() {
        return role;
    }

    public void setRole(EUserRole role) {
        this.role = role;
    }

    public EUserStatus getStatus() {
        return status;
    }

    public void setStatus(EUserStatus status) {
        this.status = status;
    }
}
