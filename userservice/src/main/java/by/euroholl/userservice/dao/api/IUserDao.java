package by.euroholl.userservice.dao.api;

import by.euroholl.userservice.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserDao extends JpaRepository<User, UUID> {

}
