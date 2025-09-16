package com.travelbooking.Controllers;

import com.travelbooking.DTO.FavoriteDTO;
import com.travelbooking.Entities.Favorite;
import com.travelbooking.Repositories.FavoriteRepository;
import com.travelbooking.Services.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/favorites")
public class FavoriteController extends BaseController<Favorite, Long, FavoriteRepository, FavoriteService> {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService service) {
        super(service);
        this.favoriteService = service;
    }

    @GetMapping("/dto")
    public ResponseEntity<Set<FavoriteDTO>> findAllDTO() {
        try {
            return ResponseEntity.ok(favoriteService.findAllDTO());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<FavoriteDTO> findDTOById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(favoriteService.findDTOById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/dto")
    public ResponseEntity<FavoriteDTO> createFromDTO(@RequestBody FavoriteDTO dto) {
        try {
            return ResponseEntity.ok(favoriteService.createFromDTO(dto));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/dto")
    public ResponseEntity<FavoriteDTO> updateFromDTO(@RequestBody FavoriteDTO dto) {
        try {
            return ResponseEntity.ok(favoriteService.updateFromDTO(dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Set<FavoriteDTO>> findByUserId(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(favoriteService.findByUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{productId}/{userId}")
    public ResponseEntity<Void> deleteByProductId(@PathVariable Long productId, @PathVariable Long userId) {
        try {
            favoriteService.deleteByProductId(productId, userId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
