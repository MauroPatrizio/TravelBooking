package com.travelbooking.Services;

import com.travelbooking.DTO.UsuarioDTO;
import com.travelbooking.Entities.User;
import com.travelbooking.Repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService extends BaseService<User, Long, UserRepository> {

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
    }

    // 🔹 Conversion helpers
    private UsuarioDTO toDTO(User entity) {
        return new UsuarioDTO(
                entity.getId(),
                entity.getName(),
                entity.getSurname(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getPhone(),
                entity.getRoles(),
                entity.getScore()
        );
    }

    private User toEntity(UsuarioDTO dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword()); // se encodea en create/update
        entity.setPhone(dto.getPhone());
        entity.setRoles(dto.getRoles());
        entity.setScore(dto.getScore());
        entity.setActive(true);
        return entity;
    }

    // 🔹 CRUD con DTOs
    public UsuarioDTO findDTOById(Long id) throws Exception {
        Optional<User> entity = repository.findById(id);
        if (!entity.isPresent()) {
            throw new Exception("User not found with id: " + id);
        }
        return toDTO(entity.get());
    }

    public UsuarioDTO createFromDTO(UsuarioDTO dto) {
        User entity = toEntity(dto);
        entity.setPassword(passwordEncoder.encode(dto.getPassword())); // encode password
        return toDTO(repository.save(entity));
    }

    public UsuarioDTO updateFromDTO(UsuarioDTO dto) throws Exception {
        if (!repository.existsById(dto.getId())) {
            throw new Exception("User not found with id: " + dto.getId());
        }
        User entity = toEntity(dto);
        entity.setPassword(passwordEncoder.encode(dto.getPassword())); // re-encode password
        return toDTO(repository.save(entity));
    }

    public Set<UsuarioDTO> findAllDTO() {
        Set<User> users = repository.findByActiveTrue();
        return users.stream()
                .map(this::toDTO)
                .collect(Collectors.toSet());
    }

    public UsuarioDTO findByEmail(String email) throws Exception {
        Optional<User> user = repository.findByEmail(email);
        if (!user.isPresent()) {
            throw new Exception("User not found with email: " + email);
        }
        return toDTO(user.get());
    }
}
