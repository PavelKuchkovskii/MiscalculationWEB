package org.euroholl.miscalculation.dao.entity;

import org.euroholl.miscalculation.dao.entity.api.IUser;
import org.euroholl.miscalculation.dao.entity.enums.EUserRole;
import org.euroholl.miscalculation.dao.entity.enums.EUserStatus;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

public class User implements IUser {

    @Id
    private UUID uuid;
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;
    @Column(name = "mail")
    private String mail;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private EUserRole role;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EUserStatus status;


    @Override
    public UUID getUuid() {
        return this.uuid;
    }

    @Override
    public LocalDateTime getDtCreate() {
        return this.dtCreate;
    }

    @Override
    public LocalDateTime getDtUpdate() {
        return this.dtUpdate;
    }

    @Override
    public String getMail() {
        return this.mail;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public EUserRole getRole() {
        return this.role;
    }

    @Override
    public EUserStatus getStatus() {
        return this.status;
    }
}
