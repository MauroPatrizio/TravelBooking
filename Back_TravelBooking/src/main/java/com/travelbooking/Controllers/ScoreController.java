package com.travelbooking.Controllers;

import com.travelbooking.DTO.ScoreDTO;
import com.travelbooking.Entities.Score;
import com.travelbooking.Repositories.ScoreRepository;
import com.travelbooking.Services.ScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/scores")
public class ScoreController extends BaseController<Score, Long, ScoreRepository, ScoreService> {

    private final ScoreService scoreService;

    public ScoreController(ScoreService service) {
        super(service);
        this.scoreService = service;
    }

    @GetMapping("/dto")
    public ResponseEntity<Set<ScoreDTO>> findAllDTO() {
        try {
            return ResponseEntity.ok(scoreService.findAllDTO());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<ScoreDTO> findDTOById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(scoreService.findDTOById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/dto")
    public ResponseEntity<ScoreDTO> createFromDTO(@RequestBody ScoreDTO dto) {
        try {
            return ResponseEntity.ok(scoreService.createFromDTO(dto));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/dto")
    public ResponseEntity<ScoreDTO> updateFromDTO(@RequestBody ScoreDTO dto) {
        try {
            return ResponseEntity.ok(scoreService.updateFromDTO(dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
