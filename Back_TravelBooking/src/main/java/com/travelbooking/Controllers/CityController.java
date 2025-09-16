package com.travelbooking.Controllers;

import com.travelbooking.DTO.CityDTO;
import com.travelbooking.Entities.City;
import com.travelbooking.Repositories.CityRepository;
import com.travelbooking.Services.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/cities")
public class CityController extends BaseController<City, Long, CityRepository, CityService> {

    private final CityService cityService;

    public CityController(CityService service) {
        super(service);
        this.cityService = service;
    }

    @GetMapping("/dto")
    public ResponseEntity<Set<CityDTO>> findAllDTO() {
        try {
            return ResponseEntity.ok(cityService.findAllDTO());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<CityDTO> findDTOById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(cityService.findDTOById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/dto")
    public ResponseEntity<CityDTO> createFromDTO(@RequestBody CityDTO dto) {
        try {
            return ResponseEntity.ok(cityService.createFromDTO(dto));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/dto")
    public ResponseEntity<CityDTO> updateFromDTO(@RequestBody CityDTO dto) {
        try {
            return ResponseEntity.ok(cityService.updateFromDTO(dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
