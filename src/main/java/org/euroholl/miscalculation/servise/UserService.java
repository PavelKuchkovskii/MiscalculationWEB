package org.euroholl.miscalculation.servise;

import org.euroholl.miscalculation.dao.entity.User;
import org.euroholl.miscalculation.servise.api.IUserService;
import org.euroholl.miscalculation.servise.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class UserService implements IUserService {


    @Override
    public UserDTO create(UserDTO dto) {
        dto.setUuid(UUID.randomUUID());
        dto.setDtCreate(LocalDateTime.now());
        dto.setDtUpdate(dto.getDtCreate());

        if(validate(dto)) {

            Product product = mapToEntity(dto);
            dao.save(product);

            //Create audit
            AuditDTO audit = new AuditDTO();
            UserToJwt user = (UserToJwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            audit.setUser(user);
            audit.setText("Create Product");
            audit.setType(EEssenceType.PRODUCT);
            audit.setId(dto.getUuid().toString());

            auditService.createAudit(audit);
        }

        return dto;
    }

    @Override
    public UserDTO read(UUID uuid) {
        return null;
    }

    @Override
    public Page<UserDTO> get(int page, int itemsPerPage) {
        return null;
    }

    @Override
    public UserDTO update(UUID uuid, LocalDateTime dtUpdate, UserDTO dto) {
        return null;
    }

    @Override
    public void delete(UUID uuid, LocalDateTime dtUpdate) {

    }

    @Override
    public boolean validate(UserDTO dto) {
        return false;
    }

    @Override
    public UserDTO mapToDTO(User user) {
        return null;
    }

    @Override
    public User mapToEntity(UserDTO dto) {
        return null;
    }
}
