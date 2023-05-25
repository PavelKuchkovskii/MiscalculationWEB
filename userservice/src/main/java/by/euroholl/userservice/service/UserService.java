package by.euroholl.userservice.service;

import by.euroholl.userservice.dao.api.IUserDao;
import by.euroholl.userservice.dao.entity.User;
import by.euroholl.userservice.dao.entity.builder.UserBuilder;
import by.euroholl.userservice.dao.entity.enums.EUserRole;
import by.euroholl.userservice.dao.entity.enums.EUserStatus;
import by.euroholl.userservice.service.dto.UserCreateDTO;
import by.euroholl.userservice.service.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final IUserDao dao;
    private final ModelMapper mapper;
    private final PasswordEncoder encoder;

    public UserService(IUserDao dao, ModelMapper mapper, PasswordEncoder encoder) {
        this.dao = dao;
        this.mapper = mapper;
        this.encoder = encoder;
    }
    @Transactional
    public UserDTO create(UserCreateDTO dto) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUuid(UUID.randomUUID());
        userDTO.setDtCreate(LocalDateTime.now());
        userDTO.setDtUpdate(userDTO.getDtCreate());
        userDTO.setMail(dto.getMail());
        userDTO.setName(dto.getName());
        userDTO.setSurname(dto.getSurname());
        userDTO.setPassword(encoder.encode(dto.getPassword()));
        userDTO.setRole(EUserRole.USER);
        userDTO.setStatus(EUserStatus.WAITING_ACTIVATION);

        if(validate(userDTO)) {
            User user = mapToEntity(userDTO);
            dao.save(user);
        }

        return this.read(userDTO.getUuid());
    }

    /*public UserDTO get(Authentication auth) {
        Optional<User> user = dao.findByNick( ((UserToJwt) auth.getPrincipal()).getNick());

        if(user.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return this.mapToDTO(user.get());
    }*/

    public UserDTO read(UUID uuid) {
        Optional<User> user = dao.findById(uuid);

        if(user.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return this.mapToDTO(user.get());
    }

    public boolean validate(UserDTO dto) {
        if (dto.getUuid() == null) {
            throw new IllegalArgumentException("Uuid cannot be null");
        }
        else if(dto.getDtCreate() == null) {
            throw new IllegalArgumentException("Create date cannot be null");
        }
        else if(dto.getDtUpdate() == null) {
            throw new IllegalArgumentException("Update date cannot be null");
        }
        else if(dto.getRole() == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        else if(dto.getStatus() == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        return true;
    }

    public UserDTO mapToDTO(User user) {
        return mapper.map(user, UserDTO.class);
    }

    public User mapToEntity(UserDTO userDTO) {
        return UserBuilder
                .create()
                .setUuid(userDTO.getUuid())
                .setDtCreate(userDTO.getDtCreate())
                .setDtUpdate(userDTO.getDtUpdate())
                .setMail(userDTO.getMail())
                .setName(userDTO.getName())
                .setSurname(userDTO.getSurname())
                .setPassword(userDTO.getPassword())
                .setRole(userDTO.getRole())
                .setStatus(userDTO.getStatus())
                .build();
    }
}