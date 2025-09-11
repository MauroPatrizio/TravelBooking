package com.travelbooking.Services;

import com.travelbooking.DTO.ScoreDTO;
import com.travelbooking.Entities.Product;
import com.travelbooking.Entities.Reservation;
import com.travelbooking.Entities.Score;
import com.travelbooking.Repositories.ReservationRepository;
import com.travelbooking.Repositories.ScoreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ScoreService extends BaseService<Score, Long, ScoreRepository> {

    private final ReservationRepository reservationRepository;

    public ScoreService(ScoreRepository repository, ReservationRepository reservationRepository) {
        super(repository);
        this.reservationRepository = reservationRepository;
    }

    // 🔹 Conversion helpers
    private ScoreDTO toDTO(Score entity) {
        return new ScoreDTO(
                entity.getId(),
                entity.getScore(),
                entity.getProduct(),
                entity.getUser()
        );
    }

    private Score toEntity(ScoreDTO dto) {
        Score entity = new Score();
        entity.setId(dto.getId());
        entity.setScore(dto.getScore());
        entity.setProduct(dto.getProduct());
        entity.setUser(dto.getUser());
        entity.setActive(true);
        return entity;
    }

    // 🔹 CRUD with validation
    public ScoreDTO findDTOById(Long id) throws Exception {
        Optional<Score> entity = repository.findById(id);
        if (!entity.isPresent()) {
            throw new Exception("Score not found with id: " + id);
        }
        return toDTO(entity.get());
    }

    public ScoreDTO createFromDTO(ScoreDTO dto) {
        validateUserReservation(dto);
        Score entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public ScoreDTO updateFromDTO(ScoreDTO dto) {
        if (!repository.existsById(dto.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Score not found with id: " + dto.getId());
        }
        validateUserReservation(dto);
        Score entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public Set<ScoreDTO> findAllDTO() {
        return repository.findByActiveTrue()
                .stream()
                .map(score -> {
                    if (score.getScore() == null) score.setScore(0.0);
                    return toDTO(score);
                })
                .collect(Collectors.toSet());
    }

    private void validateUserReservation(ScoreDTO dto) {
        List<Reservation> reservations = reservationRepository.findByUserId(dto.getUser().getId());

        boolean hasReservation = reservations.stream()
                .map(Reservation::getProduct)
                .map(Product::getId)
                .anyMatch(productId -> productId.equals(dto.getProduct().getId()));

        if (!hasReservation) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "A score can only be created if the user has a reservation for the selected product"
            );
        }
    }
}
