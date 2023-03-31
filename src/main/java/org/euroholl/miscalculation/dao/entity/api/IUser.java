package org.euroholl.miscalculation.dao.entity.api;

import org.euroholl.miscalculation.dao.entity.enums.EUserRole;
import org.euroholl.miscalculation.dao.entity.enums.EUserStatus;

public interface IUser extends IEssence {

    String getMail();
    String getFirstName();
    String getLastName();
    String getPassword();
    EUserRole getRole();
    EUserStatus getStatus();
}
