package com.travelbooking.Controllers;

import com.travelbooking.DTO.ImageDTO;
import com.travelbooking.Entities.Image;
import com.travelbooking.Repositories.ImageRepository;
import com.travelbooking.Services.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/images")
public class ImageController extends BaseController<Image, Long, ImageRepository, ImageService> {

    private final ImageService imageService;

    public ImageController(ImageService service) {
        super(service);
        this.imageService = service;
    }

    @GetMapping("/dto")
    public ResponseEntity<Set<ImageDTO>> findAllDTO() {
        try {
            return ResponseEntity.ok(imageService.findAllDTO());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<ImageDTO> findDTOById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(imageService.findDTOById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/dto")
    public ResponseEntity<ImageDTO> createFromDTO(@RequestBody ImageDTO dto) {
        try {
            return ResponseEntity.ok(imageService.createFromDTO(dto));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/dto")
    public ResponseEntity<ImageDTO> updateFromDTO(@RequestBody ImageDTO dto) {
        try {
            return ResponseEntity.ok(imageService.updateFromDTO(dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
