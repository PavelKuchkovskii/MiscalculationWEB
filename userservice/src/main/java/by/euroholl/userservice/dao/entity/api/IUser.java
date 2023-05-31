package by.euroholl.userservice.dao.entity.api;

import by.euroholl.userservice.dao.entity.enums.EUserRole;
import by.euroholl.userservice.dao.entity.enums.EUserStatus;

public interface IUser extends IEssence {

    String getEmail();
    String getName();
    String getSurname();
    String getPassword();
    EUserRole getRole();
    EUserStatus getStatus();
}
