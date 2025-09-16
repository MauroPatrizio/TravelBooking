package com.travelbooking.Controllers;

import com.travelbooking.DTO.RoleDTO;
import com.travelbooking.Entities.Role;
import com.travelbooking.Repositories.RoleRepository;
import com.travelbooking.Services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/roles")
public class RoleController extends BaseController<Role, Long, RoleRepository, RoleService> {

    private final RoleService roleService;

    public RoleController(RoleService service) {
        super(service);
        this.roleService = service;
    }

    @GetMapping("/dto")
    public ResponseEntity<Set<RoleDTO>> findAllDTO() {
        try {
            return ResponseEntity.ok(roleService.findAllDTO());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<RoleDTO> findDTOById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(roleService.findDTOById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/dto")
    public ResponseEntity<RoleDTO> createFromDTO(@RequestBody RoleDTO dto) {
        try {
            return ResponseEntity.ok(roleService.createFromDTO(dto));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/dto")
    public ResponseEntity<RoleDTO> updateFromDTO(@RequestBody RoleDTO dto) {
        try {
            return ResponseEntity.ok(roleService.updateFromDTO(dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
