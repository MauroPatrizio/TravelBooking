package com.travelbooking.Services;

import com.travelbooking.DTO.ReservationDTO;
import com.travelbooking.Entities.Reservation;
import com.travelbooking.Repositories.ReservationRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReservationService extends BaseService<Reservation, Long, ReservationRepository> {

    public ReservationService(ReservationRepository repository) {
        super(repository);
    }

    private ReservationDTO toDTO(Reservation entity) {
        return new ReservationDTO(
                entity.getId(),
                entity.getCheckInTime(),
                entity.getCheckInDate(),
                entity.getCheckOutDate(),
                entity.getUser(),
                entity.getProduct(),
                entity.getReservationName(),
                entity.getReservationSurname(),
                entity.getReservationEmail(),
                entity.getReservationPhone(),
                entity.getComments(),
                entity.getVaccination()
        );
    }

    private Reservation toEntity(ReservationDTO dto) {
        Reservation entity = new Reservation();
        entity.setId(dto.getId());
        entity.setCheckInTime(dto.getCheckInTime());
        entity.setCheckInDate(dto.getCheckInDate());
        entity.setCheckOutDate(dto.getCheckOutDate());
        entity.setUser(dto.getUser());
        entity.setProduct(dto.getProduct());
        entity.setReservationName(dto.getReservationName());
        entity.setReservationSurname(dto.getReservationSurname());
        entity.setReservationEmail(dto.getReservationEmail());
        entity.setReservationPhone(dto.getReservationPhone());
        entity.setComments(dto.getComments());
        entity.setVaccination(dto.getVaccination());
        entity.setActive(true);
        return entity;
    }

    public ReservationDTO findDTOById(Long id) throws Exception {
        Optional<Reservation> entity = repository.findById(id);
        if (entity.isEmpty()) {
            throw new Exception("Reservation not found with id: " + id);
        }
        return toDTO(entity.get());
    }

    public ReservationDTO createFromDTO(ReservationDTO dto) {
        Reservation entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public ReservationDTO updateFromDTO(ReservationDTO dto) throws Exception {
        if (!repository.existsById(dto.getId())) {
            throw new Exception("Reservation not found with id: " + dto.getId());
        }
        Reservation entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public Set<ReservationDTO> findAllDTO() {
        return repository.findByActiveTrue()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toSet());
    }

    public Set<ReservationDTO> findByUserId(Long userId) {
        return repository.findByUserId(userId).stream()
                .map(this::toDTO)
                .collect(Collectors.toSet());
    }

    public Set<ReservationDTO> findByProductId(Long productId) {
        return repository.findByProductId(productId).stream()
                .map(this::toDTO)
                .collect(Collectors.toSet());
    }
}
