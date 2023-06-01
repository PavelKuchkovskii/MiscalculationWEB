package by.euroholl.userservice.security.auth.api;

import by.euroholl.userservice.dao.entity.enums.EUserStatus;
import org.springframework.security.core.userdetails.UserDetails;

public interface ICustomUserDetails extends UserDetails {

    EUserStatus getStatus();
}
