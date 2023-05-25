package by.euroholl.userservice.service.dto;

import by.euroholl.userservice.dao.entity.enums.EUserRole;
import by.euroholl.userservice.dao.entity.enums.EUserStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserDTO {

    private UUID uuid;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String mail;
    private String name;
    private String surname;
    private String password;
    private EUserRole role;
    private EUserStatus status;

    public UserDTO() {
    }

    public UserDTO(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String mail, String name, String surname, String password, EUserRole role, EUserStatus status) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.mail = mail;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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