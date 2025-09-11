package com.travelbooking.Services;

import com.travelbooking.DTO.RoleDTO;
import com.travelbooking.Entities.Role;
import com.travelbooking.Repositories.RoleRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService extends BaseService<Role, Long, RoleRepository> {

    public RoleService(RoleRepository repository) {
        super(repository);
    }

    private RoleDTO toDTO(Role entity) {
        return new RoleDTO(
                entity.getId(),
                entity.getName()
        );
    }

    private Role toEntity(RoleDTO dto) {
        Role entity = new Role();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setActive(true);
        return entity;
    }

    public RoleDTO findDTOById(Long id) throws Exception {
        Optional<Role> entity = repository.findById(id);
        if (!entity.isPresent()) {
            throw new Exception("Role not found with id: " + id);
        }
        return toDTO(entity.get());
    }

    public RoleDTO createFromDTO(RoleDTO dto) {
        Role entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public RoleDTO updateFromDTO(RoleDTO dto) throws Exception {
        if (!repository.existsById(dto.getId())) {
            throw new Exception("Role not found with id: " + dto.getId());
        }
        Role entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public Set<RoleDTO> findAllDTO() {
        List<Role> roles = repository.findByActiveTrue();
        return roles.stream()
                .map(this::toDTO)
                .collect(Collectors.toSet());
    }

    public RoleDTO findByName(String name) throws Exception {
        return repository.findByName(name)
                .map(this::toDTO)
                .orElseThrow(() -> new Exception("Role not found with name: " + name));
    }
}
