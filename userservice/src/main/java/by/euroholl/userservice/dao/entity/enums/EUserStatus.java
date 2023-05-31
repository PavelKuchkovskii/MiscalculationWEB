package by.euroholl.userservice.dao.entity.enums;

public enum EUserStatus {

    //Waiting to send message to email
    WAITING_VERIFICATION,

    //Waiting to confirm email
    WAITING_CONFIRM,

    //Waiting to confirm by ADMIN
    WAITING_ACTIVATION,

    //ACTIVATED
    ACTIVATED,

    //DEACTIVATED
    DEACTIVATED
}