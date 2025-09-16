package com.travelbooking.Controllers;

import com.travelbooking.DTO.ReservationDTO;
import com.travelbooking.Entities.Reservation;
import com.travelbooking.Repositories.ReservationRepository;
import com.travelbooking.Services.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/reservations")
public class ReservationController extends BaseController<Reservation, Long, ReservationRepository, ReservationService> {

    private final ReservationService reservationService;

    public ReservationController(ReservationService service) {
        super(service);
        this.reservationService = service;
    }

    @GetMapping("/dto")
    public ResponseEntity<Set<ReservationDTO>> findAllDTO() {
        try {
            return ResponseEntity.ok(reservationService.findAllDTO());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<ReservationDTO> findDTOById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reservationService.findDTOById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/dto")
    public ResponseEntity<ReservationDTO> createFromDTO(@RequestBody ReservationDTO dto) {
        try {
            return ResponseEntity.ok(reservationService.createFromDTO(dto));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/dto")
    public ResponseEntity<ReservationDTO> updateFromDTO(@RequestBody ReservationDTO dto) {
        try {
            return ResponseEntity.ok(reservationService.updateFromDTO(dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Set<ReservationDTO>> findByUserId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reservationService.findByUserId(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Set<ReservationDTO>> findByProductId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reservationService.findByProductId(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
